package learnJava.rz07;

public class AppMain {
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(AppMain::exit01));
		Runtime.getRuntime().addShutdownHook(new Thread(AppMain::exit02));
		Runtime.getRuntime().addShutdownHook(new Thread(AppMain::exit03));
		Runtime.getRuntime().removeShutdownHook(new Thread(() -> {
			while (true) {
				System.out.println(".....");
			}
		}));
		while (true) {
			System.out.println(".....");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void exit01() {
		
		System.out.println("exit01");
	}
	
	public static void exit02() {
		System.out.println("exit02");
	}
	
	public static void exit03() {
		System.out.println("exit03");
	}
}
