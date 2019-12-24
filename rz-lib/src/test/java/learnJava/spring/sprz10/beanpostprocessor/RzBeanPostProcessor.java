package learnJava.spring.sprz10.beanpostprocessor;

import com.rz.frame.utils.RzLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class RzBeanPostProcessor implements BeanPostProcessor {
	private final ConfigurableApplicationContext applicationContext;
	
	private final StringValueResolver embeddedValueResolver;
	
	
	/**
	 * Create a new ApplicationContextAwareProcessor for the given context.
	 */
	public RzBeanPostProcessor(ConfigurableApplicationContext applicationContext) {
		//拿到当前容器
		this.applicationContext = applicationContext;
		this.embeddedValueResolver = new EmbeddedValueResolver(applicationContext.getBeanFactory());
	}
	
	/**
	 * 前置处理器 和后置处理器
	 * @param bean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		RzLogger.info("Before init "+beanName);
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		RzLogger.info("After init "+beanName);
//		RzLogger.infoObject(bean);
		return bean;
	}
}
