package learnJava.rz12;

public class CusmtomerObserver2 implements Observer {
	@Override
	public void reciveMessage(double price) {
		
		System.out.println("CusmtomerObserver2 收到了价格消息：" + price);
	}
}
