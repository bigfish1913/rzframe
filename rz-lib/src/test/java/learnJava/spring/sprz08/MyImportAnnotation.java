package learnJava.spring.sprz08;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Import({RzImportBeanDefinitionRegistrar.class})
public @interface MyImportAnnotation {

}
