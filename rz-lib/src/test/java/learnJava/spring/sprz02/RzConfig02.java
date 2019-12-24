package learnJava.spring.sprz02;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value = "learnJava.spring.*",includeFilters = {
		@ComponentScan.Filter(type = FilterType.CUSTOM,classes = {SpRzTypeFilters.class})
},useDefaultFilters = false)
public class RzConfig02 {
}
