package learnJava.spring.sprz10.beanpostprocessor;

import com.rz.frame.utils.RzLogger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {
	public Dog() {
		RzLogger.info("创建Dog");
	}
	
	
	@PostConstruct
	public void init() {
		RzLogger.info("Dog init PostConstruct");
	}

	@PreDestroy
	public void destroy() {
		RzLogger.info("Dog destory PreDestroy");
	}
}
