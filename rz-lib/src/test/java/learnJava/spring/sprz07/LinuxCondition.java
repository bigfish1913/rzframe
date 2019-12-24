package learnJava.spring.sprz07;

import com.rz.frame.utils.RzLogger;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {
	
	/**
	 *
	 * @param context 判断上下文的环境
	 * @param metadata 注解信息
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		ClassLoader classLoader = context.getClassLoader();
		Environment environment = context.getEnvironment();
		BeanDefinitionRegistry registry = context.getRegistry();
 
		RzLogger.infoObject(context);
		RzLogger.infoObject(metadata);
		String env=environment.getProperty("os.name");
		if(env.contains("Linux")){
			return true;
		}
		return false;
	}
}
