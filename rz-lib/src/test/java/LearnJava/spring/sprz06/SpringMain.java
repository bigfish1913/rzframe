package LearnJava.spring.sprz06;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigMain.class);
		OrderBiz bean = applicationContext.getBean(OrderBiz.class);
		bean.doSomething(1, 3);
		
		CountBiz countBean = applicationContext.getBean(CountBiz.class);
		countBean.doSomething(1, 3);
		
	}
}
