package learnJava.spring.sprz09;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RzConfig09 {
	/**
	 * Bean的生命周期
	 */
	
	 
	@Bean(initMethod = "init",destroyMethod = "destroy")
	public  Dog getDog(){
		return new Dog();
	}
	@Bean
	public  Cat getCat(){
		return new Cat();
	}
}
