package LearnJava.spring.sprz03.service;

import LearnJava.spring.sprz03.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class OrderService {
	
	@Autowired OrderDao orderDao;
	
	public void printDao(){
		
		System.out.println(orderDao.toString());
	}
}
