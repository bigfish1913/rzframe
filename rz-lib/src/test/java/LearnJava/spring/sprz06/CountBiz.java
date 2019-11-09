package LearnJava.spring.sprz06;

import org.springframework.stereotype.Component;

@Component
public class CountBiz {
	
	public void doSomething(int x, int y) {
		System.out.println(x + y);
	}
}
