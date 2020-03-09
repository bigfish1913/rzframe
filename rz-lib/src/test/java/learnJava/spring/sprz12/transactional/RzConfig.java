package learnJava.spring.sprz12.transactional;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("learnJava.spring.sprz12.transactional")
@EnableTransactionManagement
public class RzConfig {
 
 @Bean
 public DataSource dataSource() {
		final HikariConfig cpConfig = new HikariConfig();
		cpConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		cpConfig.setJdbcUrl("jdbc:mysql://localhost:3306/rzframe?characterEncoding=utf8&useSSL=true&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC");
		cpConfig.setUsername("root");
		cpConfig.setPassword("abc12345");
		cpConfig.setMaximumPoolSize(10);
		HikariDataSource hikariDataSource = new HikariDataSource(cpConfig);
		return hikariDataSource;
	}
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public PlatformTransactionManager platformTransactionManager(){
		return new DataSourceTransactionManager(dataSource());
	}
	
	
}
