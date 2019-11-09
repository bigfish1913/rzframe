package LearnJava.spring.sprz05;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigMain.class);
		OrderBiz bean = applicationContext.getBean(OrderBiz.class);
		bean.doSomething(1, 3);
		
	}
}
