package LearnJava.spring.sprz03;

import LearnJava.spring.sprz03.dao.OrderDao;
import LearnJava.spring.sprz03.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		
 	AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(ConfigMain.class);
//		OrderDao bean = applicationContext.getBean(OrderDao.class);
//		System.out.println(bean);
		
//		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//		for (int i = 0; i < beanDefinitionNames.length; i++) {
//			System.out.println(beanDefinitionNames[i]);
//		}
		OrderService service = applicationContext.getBean(OrderService.class);
		service.printDao();
	}
}
