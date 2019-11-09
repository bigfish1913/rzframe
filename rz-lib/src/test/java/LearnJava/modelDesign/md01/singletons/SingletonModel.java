package LearnJava.modelDesign.md01.singletons;

public class SingletonModel {
	
	public SingletonModel(){
		System.out.println(this.getClass().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
