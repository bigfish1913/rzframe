package learnJava.spring.sprz01;

import com.rz.frame.utils.RzLogger;

public class Person {
	public Person(){
		RzLogger.info("创建Person实例");
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
