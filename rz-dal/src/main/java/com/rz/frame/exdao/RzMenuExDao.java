package com.rz.frame.exdao;

import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.StatementParameter;
import com.ctrip.platform.dal.dao.StatementParameters;
import com.rz.frame.dao.RzMenuDao;
import com.rz.frame.entity.RzMenu;
import com.rz.frame.entity.RzRole;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class RzMenuExDao extends RzMenuDao {
    public RzMenuExDao() throws SQLException {
    }

    public List<RzMenu> getMenuByRoleId(long roleId) {
        try {
            String sql = "SELECT m.* FROM rz_menu m JOIN rz_role_menu rm ON (rm.menu_id = m.id) AND m.menu_status = 0 WHERE rm.role_id = ?";
            StatementParameters statementParameters = new StatementParameters();
            statementParameters.add(new StatementParameter(1, Types.BIGINT, roleId));
            return this.queryDao.query(sql, statementParameters, DalHints.createIfAbsent(null), RzMenu.class);
        } catch (Exception ex) {

        }
        return null;
    }

}
