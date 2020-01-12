package com.rz.frame.netty;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


/**
 * 自定义协议 数据包格式
 * -----------------------------------
 * | 协议开始标志 | 包长度|令牌 (定长50个字节)|令牌生成时间(定长50个字节)| 包内容 |
 * -----------------------------------
 * 令牌生成规则
 * 协议开始标志 +包长度+令牌生成时间+包内容+服务器与客户端约定的秘钥
 *
 * @author Administrator
 */
public class Message {
	public Message(MessageHead header, byte[] content) {
		this.header = header;
		this.content = content;
	}
	// 协议头
	private MessageHead header;
	// 内容
	private byte[] content;
	
	public MessageHead getHeader() {
		return header;
	}
	
	public void setHeader(MessageHead header) {
		this.header = header;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public String getStrContent() {
		return new String(content);
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[header:" + header.toString() + "]" + "content:" + new String(content);
	}
	
	/**
	 * 生成token   协议开始标志 +包长度+令牌生成时间+包内容+服务器与客户端约定的秘钥
	 *
	 * @return
	 */
	public String buidToken() {
		//生成token
		
		
		StringBuilder allData = new StringBuilder(String.valueOf(this.getHeader().getHeadData()));
		allData.append(this.getHeader().getLength());
		allData.append(getHeader().getCreateDate().toString());
		//        allData.append(new String(this.getContent()));
		allData.append(this.getHeader().getMessageId().trim());//秘钥
		return Md5Utils.md5(allData.toString());
	}
	
	
	/**
	 * 验证是否认证通过
	 *
	 * @param token
	 * @return
	 */
	public boolean authorization(String token) {
		//表示参数被修改
		if (!token.equals(this.getHeader().getToken())) {
			return false;
		}
		//验证是否失效
		
		if (getHeader().getCreateDate().plusSeconds(60).isBefore(LocalDateTime.now())) {
			return false;
		}
		return true;
	}
	
}