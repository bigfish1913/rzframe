package learnJava.spring.sprz11;


import com.rz.frame.utils.RzLogger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain11
{
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(RzConfig11.class);
		String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			RzLogger.info(beanDefinitionNames[i]);
		}
		
 		Author author=configApplicationContext.getBean(Author.class);
 
		RzLogger.infoObject(author);
		
		
	}
}
