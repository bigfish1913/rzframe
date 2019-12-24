package learnJava.spring.sprz04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sun {
	
	private Moon moon;
	private Jupiter jupiter;
	@Autowired
	public void setJupiter(Jupiter jupiter) {
		this.jupiter = jupiter;
	}
	
	@Autowired
	public Sun(Moon moon) {
		this.moon = moon;
	}
	
	public void printMoon(){
		System.out.println(this.moon);
	}
	public void printJupiter(){
		System.out.println(this.jupiter);
	}
}
