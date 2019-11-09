package LearnJava.spring.sprz02;


import LearnJava.spring.sprz01.RzConfig;
import com.rz.frame.utils.RzLogger;
import com.sun.jmx.remote.util.ClassLogger;
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
