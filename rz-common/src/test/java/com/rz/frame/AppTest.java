package com.rz.frame;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;


/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    public static void main(String[] args) {
        System.out.println("hello");
    }

    @Test
    public void shouldAnswerWithTrue() {
        ConnectionFactory factory = new ConnectionFactory();
        // "guest"/"guest" by default, limited to localhost connections
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("192.168.0.108");
        factory.setPort(5672);

        try {
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();

            String QUEUE_NAME = "testQuene";

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "This is simple queue";
            //发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
            System.out.println("[send]：" + message);
            channel.close();
            conn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
