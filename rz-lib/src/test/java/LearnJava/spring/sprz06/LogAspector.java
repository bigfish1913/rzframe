package LearnJava.spring.sprz06;

import com.rz.frame.utils.JsonUtils;
import com.rz.frame.utils.RzLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspector {
	
	//可以过滤包下的所有方法
	@Pointcut("execution(public  void LearnJava.spring.sprz06.*.*(..))")
	public void pointCut(){}
	
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint) {
		System.out.println(joinPoint);
		RzLogger.info("方法开始执行....");
	}
	
	@After("pointCut()")
	public void logEnd() {
		RzLogger.info("方法执行完成....");
	}
	
	@AfterReturning("pointCut()")
	public void logReturn() {
		RzLogger.info("方法执行结束返回数据....");
	}
	@AfterThrowing(value = "pointCut()",throwing = "ex")
	public void logException(Exception ex) {
		RzLogger.info("方法执行异常....");
		RzLogger.error(JsonUtils.serializeObject(ex));
	}
	
	@Around("pointCut()")
	public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
		RzLogger.info("Around开始");
		Object proceed = joinPoint.proceed();
		RzLogger.info("Around结束");
		return proceed;
	}
}
