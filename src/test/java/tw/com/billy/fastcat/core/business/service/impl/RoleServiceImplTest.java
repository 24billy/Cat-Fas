package tw.com.billy.fastcat.core.business.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.billy.fastcat.SpringTest;
import tw.com.billy.fastcat.core.business.service.IRoleService;
import tw.com.billy.fastcat.core.db.model.Role;

public class RoleServiceImplTest extends SpringTest {

	@Autowired
	IRoleService roleService;

	@Test
	public void testGetRoleById() {
		Role role = new Role();
		role.setRoleId(1);

		role = roleService.getRoleById(role);
		System.out.println(role);
	}

	@Test
	public void testGetAllRole() {
		List<Role> roleList = roleService.getAllRole();

		System.out.println(roleList);
	}

}
