package tw.com.billy.fastcat.core.business.service;

import java.util.List;

import tw.com.billy.fastcat.core.db.model.Role;

/**
 * [角色] 服務介面
 * 
 * @author Billy
 *
 */
public interface IRoleService {

	/**
	 * 根據角色代碼取得角色資料
	 * 
	 * @param role
	 * @return
	 */
	Role getRoleById(Role role);

	/**
	 * 取得所有角色資料
	 * 
	 * @return
	 */
	List<Role> getAllRole();

}
