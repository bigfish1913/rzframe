package learnJava.rz20200309.ParseClass.Constant;

public class Wrapper<T> {
	private int index;
	private int type;
	private T constant;
	
	public Wrapper(int index, int type, T constant) {
		this.index = index;
		this.type = type;
		this.constant = constant;
	}
	
	public T getConstant() {
		return constant;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getType() {
		return type;
	}
}
