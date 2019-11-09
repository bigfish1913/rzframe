package LearnJava.spring.sprz03.dao;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class OrderDao {
	
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		return "OrderDao{" + "flag='" + flag + '\'' + '}';
	}
}
