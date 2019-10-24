package com.rz.frame.exdao;

import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.StatementParameter;
import com.ctrip.platform.dal.dao.StatementParameters;
import com.microsoft.sqlserver.jdbc.SQLServerParameterMetaData;
import com.rz.frame.dao.RzUserDao;
import com.rz.frame.entity.RzUser;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class RzUserExDao extends RzUserDao {
    public RzUserExDao() throws SQLException {
    }

    public RzUser getUserByLoginName(String loginName) {
        try {
            String sql = "select * from rz_user where login_name=?";
            StatementParameters statementParameters = new StatementParameters();
            statementParameters.add(new StatementParameter(1, Types.VARCHAR, loginName));
            return this.queryDao.queryFirstNullable(sql, statementParameters, DalHints.createIfAbsent(null), RzUser.class);
        } catch (Exception ex) {

        }
        return null;
    }

    public List<RzUser> getAllUser() {
        try {
            String sql = "select * from rz_user where user_status=0";
            StatementParameters statementParameters = new StatementParameters();
            return this.queryDao.query(sql, statementParameters, DalHints.createIfAbsent(null), RzUser.class);
        } catch (Exception ex) {
            return null;
        }

    }
}
