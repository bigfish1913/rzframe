package LearnJava.spring.sprz03;

import LearnJava.spring.sprz03.dao.OrderDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan({"com.rz.frame.LearnJava.spring.sprz03.service","com.rz.frame.LearnJava.spring.sprz03.dao"})
public class ConfigMain {
 	@Primary
	@Bean
	public OrderDao getOrderDao(){
		OrderDao orderDao = new OrderDao();
		orderDao.setFlag("来自Bean");
		return orderDao;
		
	}
}
