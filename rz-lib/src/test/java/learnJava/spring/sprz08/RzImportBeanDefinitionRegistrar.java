package learnJava.spring.sprz08;

import com.rz.frame.utils.RzLogger;
import learnJava.spring.sprz01.RzConfig;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class RzImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	/**
	 * 把所有需要添加到容器的bean手工注入到容器中
	 *
	 * @param importingClassMetadata 当前类的注册信息
	 * @param registry               BeanDefinition注册类
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		/**
		 * 当前写的Demo的例子是，只有dog和cat类都有了，才手工注入pig类。
		 * 使用的场景很少，也很贼，不太建议项目中使用
		 */
		AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MyImportAnnotation.class.getName()));
		boolean dogRegister = registry.containsBeanDefinition("learnJava.spring.sprz08.Dog");
		boolean catRegister = registry.containsBeanDefinition("learnJava.spring.sprz08.Cat");
		
		if (dogRegister && catRegister) {
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Pig.class);
			MutablePropertyValues propertyValues = new MutablePropertyValues();
			rootBeanDefinition.setPropertyValues(propertyValues);
			registry.registerBeanDefinition("pig", rootBeanDefinition);
		}
	}
}
