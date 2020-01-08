package com.rz.frame.entityChat;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;


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
    public Message(MessageHead head, byte[] content) {
        this.head = head;
        this.content = content;
    }

    // 协议头
    private MessageHead head;


    // 内容
    private byte[] content;

    public MessageHead getHead() {
        return head;
    }

    public void setHead(MessageHead head) {
        this.head = head;
    }

    public byte[] getContent() {
        return content;
    }

    public String getStrContent(){
        return new String(content);
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[head:" + head.toString() + "]" + "content:" + new String(content);
    }

    /**
     * 生成token   协议开始标志 +包长度+令牌生成时间+包内容+服务器与客户端约定的秘钥
     *
     * @return
     */
    public String buidToken() {
        //生成token
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(this.getHead().getCreateDate());// 这个就是把时间戳经过处理得到期望格式的时间

        StringBuilder allData = new StringBuilder(String.valueOf(this.getHead().getHeadData()));
        allData.append(this.getHead().getLength());
        allData.append(time);
//        allData.append(new String(this.getContent()));
        allData.append(this.getHead().getMessageId().trim());//秘钥
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
        if (!token.equals(this.getHead().getToken())) {
            return false;
        }
        //验证是否失效
        Long s = (System.currentTimeMillis() - getHead().getCreateDate().getTime()) / (1000 * 60);
        if (s > 60) {
            return false;
        }
        return true;
    }

}