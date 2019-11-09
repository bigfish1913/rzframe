package LearnJava.spring.sprz01;

import com.rz.frame.utils.RzLogger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext(RzConfig.class);
		String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
		RzLogger.info("容器创建完成");
		
		 
			Person person = (Person)configApplicationContext.getBean("person");
			Person person1 = (Person)configApplicationContext.getBean("person");
		 
			System.out.println(person==person1);
		 
		 
		
	}
}
