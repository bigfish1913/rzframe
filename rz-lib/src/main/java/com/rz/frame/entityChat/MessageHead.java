package com.rz.frame.entityChat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageHead {
    private int headData = 0X76;//协议开始标志
    private int length;//包的长度
    private String token;
    private Date createDate;
    private String messageId;
    private MessageType messageType;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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

    @Override
    public String toString() {
        return "MessageHead{" + "headData=" + headData + ", length=" + length + ", token='" + token + '\'' + ", createDate=" + createDate + ", messageId='" + messageId + '\'' + ", messageType=" + messageType + '}';
    }
}