package learnJava.spring.sprz08;

import com.rz.frame.utils.RzLogger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain08 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(RzConfig08.class);
		String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			RzLogger.info(beanDefinitionNames[i]);
		}
		
		Eal eal = configApplicationContext.getBean(Eal.class);
//		RzFactoryBean rzFactoryBean = configApplicationContext.getBean(RzFactoryBean.class);
		
		try {
			System.out.println(eal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
