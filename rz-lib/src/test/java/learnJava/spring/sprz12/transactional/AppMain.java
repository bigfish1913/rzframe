package learnJava.spring.sprz12.transactional;

import com.google.common.collect.Lists;
import learnJava.spring.sprz11.RzConfig11;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AppMain {
 public static void main(String[] args) throws Exception {
  AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(RzConfig.class);
  JdbcTemplate jdbcTemplate = configApplicationContext.getBean(JdbcTemplate.class);
  MenuService service = configApplicationContext.getBean(MenuService.class);
  service.insertMenu();
  List<HashMap<Object, Object>> query = jdbcTemplate.query(" SELECT * FROM `rz_menu` ", r -> {
   List<HashMap<Object, Object>> objectObjectList = new ArrayList<>();
   while (r.next()) {
    HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
    objectObjectHashMap.put("Name", r.getString("menu_name"));
    objectObjectHashMap.put("Icon", r.getString("menu_icon"));
    objectObjectList.add(objectObjectHashMap);
   }
   return objectObjectList;
  });
  try {
   System.out.println(query);
   
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}
