package learnJava.spring.sprz13.beandefinition;

import com.rz.frame.utils.RzLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class RzBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		RzLogger.info("03. RzBeanFactoryPostProcessor=>postProcessBeanFactory");
	}
}
