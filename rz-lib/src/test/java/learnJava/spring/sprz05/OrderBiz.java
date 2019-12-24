package learnJava.spring.sprz05;

import org.springframework.stereotype.Component;

@Component
public class OrderBiz {
	
	public void doSomething(int x, int y) {
		System.out.println(x + y);
	}
}
