package tw.com.billy.fastcat.core.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tw.com.billy.fastcat.core.business.service.IRoleService;
import tw.com.billy.fastcat.core.db.jdbc.JdbcDAO;
import tw.com.billy.fastcat.core.db.model.Role;

@Service("roleDao")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	@Qualifier("jdbcDAO")
	private JdbcDAO jdbcDAO;

	@Override
	public Role getRoleById(Role role) {
		String sqlText = "SELECT * FROM ROLE WHERE roleId = $P{roleId}";
		sqlText = sqlText.replace("$P{roleId}", role.getRoleId().toString());
		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText);

		Integer id = (Integer) resultList.get(0).get("roleid");
		String roleName = (String) resultList.get(0).get("roleName");

		role = new Role(id, roleName);

		return role;
	}

	@Override
	public List<Role> getAllRole() {
		String sqlText = "SELECT * FROM ROLE ORDER BY ROLEID";
		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText);

		List<Role> roleList = new ArrayList<Role>();

		for (Map<String, Object> map : resultList) {
			Integer roleId = (Integer) map.get("roleid");
			String roleName = (String) map.get("roleName");

			Role role = new Role(roleId, roleName);
			roleList.add(role);
		}

		return roleList;
	}

}