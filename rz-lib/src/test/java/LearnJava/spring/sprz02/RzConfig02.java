package LearnJava.spring.sprz02;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value = "com.rz.frame.LearnJava.spring.sprz02",includeFilters = {
		@ComponentScan.Filter(type = FilterType.CUSTOM,classes = {SpRzTypeFilters.class})
},useDefaultFilters = false)
public class RzConfig02 {
}
