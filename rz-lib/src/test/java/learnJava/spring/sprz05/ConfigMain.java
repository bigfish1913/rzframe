package learnJava.spring.sprz05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ConfigMain {

 @Bean
 public OrderBiz getOrderBiz(){
  return new OrderBiz();
 }
 
 @Bean
 public LogAspector getLogAspector(){
  return new LogAspector();
 }
}
