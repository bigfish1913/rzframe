package learnJava.spring.sprz08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Dog.class, Cat.class})
@MyImportAnnotation
public class RzConfig08 {
	/**
	 * Import 导入类
	 */
	
	/**
	 * 创建一个Eal的类的FacrotyBean,注入的Eal的对象的实例是经过工厂处理的
	 * @return
	 */
	@Bean
	public RzFactoryBean getRzFactoryBean() {
		return new RzFactoryBean();
	}
	
}
