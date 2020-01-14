package com.rz.frame.netty;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SendMsgToServer {
	private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int port = 9098;
		NettyClient nettyClient = NettyManager.getInstance().getNettyClient(ip, port);
		try {
 
			executor.scheduleAtFixedRate(()-> {
				try {
					Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
					// java自动化类
					Robot robot = new Robot();
					// 截图
					BufferedImage image = robot.createScreenCapture(screenRect);
					// 创建输出
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					ImageIO.write(image, "png", out);
					nettyClient.sendPicMessage(out.toByteArray());
				} catch (Exception e) {
					e.printStackTrace();
				}
			},1,1000/60, TimeUnit.MILLISECONDS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
