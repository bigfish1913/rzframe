package com.rz.frame.utils;

import com.rabbitmq.client.*;

import java.io.IOException;

public class MqUtils {

    private static Connection connection;

    private static Connection getConnect() {
        try {
            if (connection != null) {
                return connection;
            }
            ConnectionFactory factory = new ConnectionFactory();
            // "guest"/"guest" by default, limited to localhost connections
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            factory.setHost("192.168.0.108");
            factory.setPort(5672);
            connection = factory.newConnection();
        } catch (Exception ex) {
            return null;
        }
        return connection;
    }

    public static boolean sendMessage(String queueName, Object data) {
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            //发送消息
            String message = JsonUtils.serializeObject(data);
            channel.basicPublish("", queueName, null, message.getBytes("utf-8"));
            channel.close();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
//    public static <T> T getMessage(String queueName) {
//        try {
//            Channel channel = connection.createChannel();
//            channel.queueDeclare(queueName, false, false, false, null);
//            DefaultConsumer consumer = new DefaultConsumer(channel)
//            {
//                //当消息到达时执行回调方法
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
//                                           byte[] body) throws IOException
//                {
//                    String message = new String(body, "utf-8");
//                    System.out.println("[Receive]：" + message);
//                }
//            };
//            //监听队列
//            channel.basicConsume(queueName, true, consumer);
//            channel.close();
//        } catch (Exception ex) {
//            return false;
//        }
//        return true;
//    }
}
