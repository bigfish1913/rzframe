package learnJava.spring.sprz12.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void insertMenu() throws Exception {
		String sql = "insert into `rz_menu` (`menu_name`, `menu_src`, `menu_icon`, `menu_status`, `parentmenu_id`, `create_time`, `dataChange_lastTime`) values('test','/','fa-desktop','0','0',now(),now());";
		jdbcTemplate.execute(sql);
		int i = 1 / 0;
		
	}
}
