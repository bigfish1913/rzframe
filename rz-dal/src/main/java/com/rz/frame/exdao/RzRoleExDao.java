package com.rz.frame.exdao;

import com.ctrip.platform.dal.dao.*;
import com.ctrip.platform.dal.dao.helper.DalDefaultJpaParser;
import com.ctrip.platform.dal.dao.sqlbuilder.SelectSqlBuilder;
import com.rz.frame.dao.RzRoleDao;
import com.rz.frame.dao.RzUserDao;
import com.rz.frame.entity.RzRole;
import com.rz.frame.entity.RzUser;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * @author fu
 * @date 2019-07-14
 */
public class RzRoleExDao extends RzRoleDao {

    public RzRoleExDao() throws SQLException {
    }

    public RzRole getRoleByLoginName(String loginName) {
        try {
            String sql = "SELECT r.* FROM rz_user u JOIN rz_user_role ru ON u.user_id = ru.user_id AND u.user_status = 0 JOIN rz_role r ON ru.role_id = r.id AND r.role_status = 0 where u.login_name=?";
            StatementParameters statementParameters = new StatementParameters();
            statementParameters.add(new StatementParameter(1, Types.VARCHAR, loginName));
            return this.queryDao.queryFirstNullable(sql, statementParameters, DalHints.createIfAbsent(null), RzRole.class);
        } catch (Exception ex) {

        }
        return null;
    }

}