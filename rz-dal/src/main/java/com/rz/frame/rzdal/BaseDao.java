package com.rz.frame.rzdal;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {

    public <T> List<T> resultSetToList(ResultSet rs, Class cc) throws SQLException, InstantiationException, IllegalAccessException {

        //结果集 中列的名称和类型的信息
        ResultSetMetaData rsm = rs.getMetaData();
        int colNumber = rsm.getColumnCount();
        List list = new ArrayList();
        Field[] fields = cc.getDeclaredFields();

        //遍历每条记录
        while (rs.next()) {
            //实例化对象
            Object obj = cc.newInstance();
            //取出每一个字段进行赋值
            for (int i = 1; i <= colNumber; i++) {
                Object value = rs.getObject(i);
                //匹配实体类中对应的属性
                for (int j = 0; j < fields.length; j++) {
                    Field f = fields[j];
                    if (f.getName().toLowerCase().equals(rsm.getColumnName(i).toLowerCase().replace("_", ""))) {
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                        break;
                    }
                }

            }
            list.add(obj);
        }
        return list;
    }

}
