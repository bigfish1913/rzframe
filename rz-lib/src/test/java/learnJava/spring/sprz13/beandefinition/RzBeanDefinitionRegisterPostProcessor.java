package learnJava.spring.sprz13.beandefinition;

import com.rz.frame.utils.RzLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

@Component
public class RzBeanDefinitionRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
		
		RzLogger.info("01. RzBeanDefinitionRegisterPostProcessor==>postProcessBeanDefinitionRegistry");
		//此处可以使用两种方法进行注册实体
		//01. RootBeanDefinition
		//02. AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(getClass()).getBeanDefinition();
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		RzLogger.info("02. RzBeanDefinitionRegisterPostProcessor==>postProcessBeanFactory");
		
		
	}
}
