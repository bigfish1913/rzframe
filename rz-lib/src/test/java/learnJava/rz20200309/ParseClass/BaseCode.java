package learnJava.rz20200309.ParseClass;

public abstract class BaseCode {
	public BaseCode(){
		 register();
	}
	
	public  abstract void register();
	public abstract void process(ClassComplieFile classFile);
}
