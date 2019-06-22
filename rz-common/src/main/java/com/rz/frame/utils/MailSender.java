package com.rz.frame.utils;



import com.rz.frame.utils.enums.MailType;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;

import java.util.Map;
import java.util.Properties;

public class MailSender {
	private MailType mailType;
	private String userName;
	private String passWord;
	private Properties properties;
	
	
	public MailSender(MailType mailType, String userName, String passWord) {
		this.mailType = mailType;
		this.userName = userName;
		this.passWord = passWord;
		this.properties = getProperties();
	}
	
	
	public boolean sender(String recivers, String cc, String mailTitle, String mailContent, boolean isHtml, Map<String, byte[]> mapFile) throws MessagingException, IOException {
		Session session = Session.getInstance(properties);
		//2.通过session获取Transport对象（发送邮件的核心API）
		Transport ts = session.getTransport();
		//3.通过邮件用户名密码链接
		ts.connect(properties.getProperty("mail.host"), userName, this.passWord);
		//4.创建邮件
		MimeMessage mm = new MimeMessage(session);
		//设置发件人
		mm.setFrom(new InternetAddress(userName));
		Address[] address = new InternetAddress().parse(recivers);
		mm.setRecipients(Message.RecipientType.TO, address);
		
		//设置抄送人
		if (!StringUtils.isEmpty(cc)) {
			mm.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
		}
		mm.setSubject(mailTitle);
		if (!isHtml) {
			mailContent = String.format("<pre>%s</pre>", mailContent);
		}
		//		mm.setContent(mailContent, "text/html;charset=utf-8");
		// 创建多重消息
		Multipart multipart = new MimeMultipart();
		
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(mailContent, "text/html;charset=utf-8");
		multipart.addBodyPart(bodyPart);
		if (mapFile != null) {
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(mc);
			
			for (Map.Entry<String, byte[]> map : mapFile.entrySet()) {
				BodyPart messageBodyPart = new MimeBodyPart();
				InputStream inputStream = new ByteArrayInputStream(map.getValue());
				DataSource source = new ByteArrayDataSource(inputStream, "application/txt");
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(MimeUtility.encodeText(map.getKey()));
				multipart.addBodyPart(messageBodyPart);
			}
			
			mm.setContent(multipart);
		}
		//5.发送电子邮件
		ts.sendMessage(mm, mm.getAllRecipients());
		return true;
	}
	
	private Properties getProperties() {
		if (this.mailType.equals(MailType.m163)) {
			Properties prop = new Properties();
			prop.put("mail.host", "smtp.163.com");
			prop.put("mail.transport.protocol", "smtp");
			prop.put("mail.smtp.auth", true);
			return prop;
		}
		if (this.mailType.equals(MailType.qq)) {
			Properties prop = new Properties();
			prop.setProperty("mail.host", "smtp.qq.com");
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			prop.setProperty("mail.smtp.port", "465");
			prop.setProperty("mail.smtp.socketFactory.port", "465");
			return prop;
		}
		return null;
	}
	
}
