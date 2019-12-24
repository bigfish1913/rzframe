package learnJava.spring.sprz09;

import com.rz.frame.utils.RzLogger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Cat implements InitializingBean, DisposableBean {
	public Cat(){
		RzLogger.info("cat create");
	}
	@Override
	public void destroy() throws Exception {
		RzLogger.info("cat destroy");
	}
	
	/**
	 * 在容器创建的时候调用
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		RzLogger.info("cat afterPropertiesSet");
	}
}
