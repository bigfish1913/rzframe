package learnJava.rz09;

import org.eclipse.jetty.server.Server;

import java.util.concurrent.CountDownLatch;

public class JettyUse {
	public static void main(String[] args) {
		try {
			final Server server = new Server(8888);
			server.setHandler(new HelloWorldHandle());
			server.start();
			new CountDownLatch(1).await();
			//			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
