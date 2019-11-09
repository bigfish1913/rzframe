package LearnJava.spring.sprz07;

import LearnJava.spring.sprz01.Person;
import LearnJava.spring.sprz01.RzConfig;
import com.rz.frame.utils.RzLogger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain07 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext(RzConfig07.class);
		String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			RzLogger.info("条件注入的类：",beanDefinitionNames[i]);
		}
	}
}
