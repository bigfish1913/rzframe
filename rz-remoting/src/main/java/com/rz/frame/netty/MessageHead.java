package com.rz.frame.netty;

import java.time.LocalDateTime;
import java.util.Map;

import static com.rz.frame.netty.HeartConstant.RemotingHeader.DEFAULT_MAGIC_START_CODE;

public class MessageHead {
    private int headData = DEFAULT_MAGIC_START_CODE;//协议开始标志
    private int length;//包的长度
    private String token;
    private LocalDateTime createDate;
    private String messageId;
    private MessageType messageType;
    private ContentType  contentType;
   
//    private Map<String,String> messageProperties;

    public int getHeadData() {
        return headData;
    }

    public void setHeadData(int headData) {
        this.headData = headData;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    
    public ContentType getContentType() {
        return contentType;
    }
    
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
    
 
 
 
 
 @Override
    public String toString() {
        return "MessageHead{" + "headData=" + headData + ", length=" + length + ", token='" + token + '\'' + ", createDate=" + createDate + ", messageId='" + messageId + '\'' + ", messageType=" + messageType + '}';
    }
}