package LearnJava.spring.sprz01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class RzConfig {
	
	/**
	 * 	@Scope("prototype")是多实例
	 * @return
	 */
	/**
	 * @Scope("prototype") 代表示多例
	 * @Lazy 代表是懒加载，先创建Spring容器，在使用的时候再创建对象，而且是单例
	 * @return
	 */
	//	@Scope("prototype")
//	@Lazy
	@Bean
	public Person person() {
		return new Person();
	}
	
}
