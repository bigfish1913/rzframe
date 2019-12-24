package learnJava.spring.sprz03.service;

import learnJava.spring.sprz03.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Qualifier("")
	@Autowired OrderDao orderDao;
	
	public void printDao(){
		
		System.out.println(orderDao.toString());
	}
}
