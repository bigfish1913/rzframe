package learnJava.spring.sprz13.beandefinition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(RzConfig.class);
		configApplicationContext.close();
	}
}
