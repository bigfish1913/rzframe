package learnJava.rz12;

public class CusmtomerObserver1 implements Observer {
	@Override
	public void reciveMessage(double price) {
		
		System.out.println("CusmtomerObserver1 收到了价格消息：" + price);
	}
}
