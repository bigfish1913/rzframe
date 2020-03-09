package learnJava.rz12;

public class ObserverMain {
	public static void main(String[] args) {
		MarketSubject marketSubject=new MarketSubject();
		marketSubject.addObserver(new CusmtomerObserver1());
		marketSubject.addObserver(new CusmtomerObserver2());
		marketSubject.setPrice(100d);
	
	}
}
