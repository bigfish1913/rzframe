package learnJava.spring.sprz10.beanpostprocessor;


import com.rz.frame.utils.RzLogger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain10
{
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(RzConfig10.class);
		String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			RzLogger.info(beanDefinitionNames[i]);
		}
		
		Dog dog=configApplicationContext.getBean(Dog.class);
 
		configApplicationContext.close();
		
		
	}
}
