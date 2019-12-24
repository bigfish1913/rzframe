package learnJava.rz08;

import com.rz.frame.utils.RzLogger;

public class DefalutService implements IService{
	@Override
	public void doSomeThing() {
		RzLogger.info("默认服务");
	}
}
