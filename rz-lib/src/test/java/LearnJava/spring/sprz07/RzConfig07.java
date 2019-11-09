package LearnJava.spring.sprz07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RzConfig07 {
	/**
	 * 条件注入，根据启动的环境来决定使用哪一个配置
	 * 不能找到一个定义一个类的 实现多条件的加载吗？
	 */
	
	@Bean("Windows")
	@Conditional({WinCondition.class})
	public WinConfig getWinConfig(){
		return new WinConfig();
	}
	
	@Bean("Linux")
	@Conditional(value = {LinuxCondition.class})
	public LinuxConfig getLinuxConfig(){
		return new LinuxConfig();
	}

}
