package com.rz.frame.utils;

import com.rz.frame.utils.enums.MailType;

import javax.mail.*;
import java.util.Properties;

public class MailReciver {
	private MailType mailType;
	private String userName;
	private String passWord;
	private Properties properties;
	
	public MailReciver(MailType mailType, String userName, String passWord) {
		this.mailType = mailType;
		this.userName = userName;
		this.passWord = passWord;
		this.properties = getProperties();
	}
	
	public boolean deleteMail() {
		return false;
	}
	
	public Message[] getMails() throws Exception{
		
		Session session = Session.getInstance(properties);
		
		Store store = session.getStore();
		// 3、连上邮件服务器
		store.connect(properties.getProperty("mail.host"), this.userName, this.passWord);
		// 4、获得邮箱内的邮件夹
		Folder folder = store.getFolder("inbox");
		folder.open(Folder.READ_ONLY);
		// 获得邮件夹Folder内的所有邮件Message对象
		Message[] messages = folder.getMessages();
		return messages;
		
	}
	private Properties getProperties() {
		if (this.mailType.equals(MailType.m163)) {
			Properties prop = new Properties();
			prop.setProperty("mail.store.protocol", "imap");
			prop.setProperty("mail.imap.host", "imap.163.com");
			prop.setProperty("mail.imap.port", "143");
			return prop;
		}
		if (this.mailType.equals(MailType.qq)) {
			Properties prop = new Properties();
			prop.setProperty("mail.store.protocol", "imap");
			prop.setProperty("mail.imap.host", "imap.qq.com");
			prop.setProperty("mail.imap.port", "993");
			return prop;
		}
		return null;
	}
	
}
