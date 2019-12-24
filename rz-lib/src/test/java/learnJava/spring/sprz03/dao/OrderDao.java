package learnJava.spring.sprz03.dao;


import org.springframework.stereotype.Repository;

@Repository
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
