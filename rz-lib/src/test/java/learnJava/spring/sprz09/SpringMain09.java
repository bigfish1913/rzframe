package learnJava.spring.sprz09;

import com.rz.frame.utils.RzLogger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain09
{
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(RzConfig09.class);
		String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			RzLogger.info(beanDefinitionNames[i]);
		}
		
		Dog dog=configApplicationContext.getBean(Dog.class);
		Cat cat = configApplicationContext.getBean(Cat.class);
		configApplicationContext.close();
		
		
	}
}
