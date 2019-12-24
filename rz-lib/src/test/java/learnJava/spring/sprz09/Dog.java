package learnJava.spring.sprz09;

import com.rz.frame.utils.RzLogger;


public class Dog {
	public Dog(){
		RzLogger.info("创建Dog");
	}
	
	public void init(){
		RzLogger.info("Dog init ");
	}
	
	public void destroy(){
		RzLogger.info("Dog destory");
	}
}
