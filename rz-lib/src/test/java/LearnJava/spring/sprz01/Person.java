package LearnJava.spring.sprz01;

public class Person {
	public Person(){
		System.out.println("创建实例");
	}
	
	private String userName;
	private String userCode;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
