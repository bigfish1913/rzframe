package com.rz.frame.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    ServerSocket serverSocket;

    public BioServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务端启动成功，端口是：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                byte[] buff = new byte[1024];
                int len = inputStream.read(buff);
                if (len > 0) {
                    String msg = new String(buff, 0, len);
                    System.out.println("接收到数据：" + msg);
                }
            } catch (Exception ex) {

            }
        }
    }

    public static void main(String[] args) {
        BioServer bioServer = new BioServer(8080);
        bioServer.start();
    }
}
