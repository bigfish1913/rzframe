package learnJava.rz08;

import com.rz.frame.utils.RzLogger;

public class LogService implements IService {
	@Override
	public void doSomeThing() {
		RzLogger.info("日志服务");
	}
}
