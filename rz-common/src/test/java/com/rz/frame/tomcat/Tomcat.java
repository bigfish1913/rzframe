package com.rz.frame.tomcat;

import com.rz.frame.nio.BioServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
    ServerSocket serverSocket;

    public Tomcat(int port) {
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
                OutputStream outputStream = accept.getOutputStream();
                StringBuilder resp = new StringBuilder();
                resp.append("HTTP/1.1\n");
                resp.append("Content-type:text/html\n\n");
                resp.append("<h1>Hello Tomcat!</h1>");
                byte[] bytes = resp.toString().getBytes();
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            } catch (Exception ex) {

            }
        }
    }

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat(8080);
        tomcat.start();
    }
}
