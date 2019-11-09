package LearnJava.spring.sprz06;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.rz.frame.LearnJava.spring.sprz06"})
public class ConfigMain {

//	@Bean
//	public OrderBiz getOrderBiz(){
//		return new OrderBiz();
//	}
//
//	@Bean
//	public LogAspector getLogAspector(){
//		return new LogAspector();
//	}
}
