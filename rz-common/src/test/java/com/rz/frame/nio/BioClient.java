package com.rz.frame.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BioClient {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 8080);
            OutputStream outputStream = client.getOutputStream();
            byte[] msg = new byte[1024];
            while (true) {
                System.out.println("输入发送内容：");
                System.in.read(msg);
                outputStream.write(msg);
                System.out.println("--------");
                InputStream inputStream = client.getInputStream();
                inputStream.read(msg);
                System.out.println(new String(msg));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
