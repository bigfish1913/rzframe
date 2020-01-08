package com.rz.frame.entityChat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.atomic.LongAdder;

public class MessageGenerater {
    private static final int[] codex = {2, 3, 5, 6, 8, 9, 19, 11, 12, 14, 15, 17, 18};
    private static final LongAdder messageOrder = new LongAdder();
    private static final String localAddress = NetworkUtils.getLocalAddress();
    //在生成message id的时候带上进程id，避免一台机器上部署多个服务都发同样的消息时出问题
    private static final int PID = PidUtil.getPid();

    public static String getNext() {
        StringBuilder sb = new StringBuilder(40);
        long time = System.currentTimeMillis();
        String ts = new Timestamp(time).toString();
        for (int idx : codex)
            sb.append(ts.charAt(idx));
        sb.append('.').append(localAddress);
        sb.append('.').append(PID);
        messageOrder.add(1);
        sb.append('.').append(messageOrder.longValue());
        return sb.toString();
    }

    public static Message getMessage(String cnt, MessageType messageType) {
        byte[] bts = cnt.getBytes();
        MessageHead head = new MessageHead();
        head.setCreateDate(new Date());
        head.setLength(bts.length);
        head.setMessageId(getNext());
        head.setMessageType(messageType);
        Message message = new Message(head, bts);
        message.getHead().setToken(message.buidToken());
 
        return message;
    }

    public static Message getMessage(String cnt) {
        return getMessage(cnt, MessageType.Running);
    }
}
