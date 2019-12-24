package learnJava.spring.sprz11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:/Author.properties"})
public class RzConfig11 {
	
	@Bean
	public Author getAuthor(){
		return new Author();
	}

}
