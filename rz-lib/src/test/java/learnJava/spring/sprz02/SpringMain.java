package learnJava.spring.sprz02;


import com.rz.frame.utils.RzLogger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext(RzConfig02.class);
		String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
		
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			RzLogger.info("获得注入的类："+beanDefinitionNames[i]);
		}
		
		 
		
	}
}
