package LearnJava.spring.sprz04;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigMain.class);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			System.out.println(beanDefinitionNames[i]);
		}
		Sun sun = applicationContext.getBean(Sun.class);
		sun.printMoon();
		sun.printJupiter();
	}
}
