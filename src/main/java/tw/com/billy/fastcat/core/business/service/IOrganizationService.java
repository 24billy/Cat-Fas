package tw.com.billy.fastcat.core.business.service;

import java.util.List;

import tw.com.billy.fastcat.core.db.model.Organization;

public interface IOrganizationService {

	/**
	 * 根據單位代碼取得單位資料
	 * 
	 * @param organizationId
	 * @return
	 */
	Organization getOrganizationById(Integer organizationId);

	/**
	 * 取得所有單位資料
	 * 
	 * @return
	 */
	List<Organization> getAllOrganization();

	/**
	 * 新增單位資料
	 * 
	 * @param organization
	 */
	Integer addOrganization(Organization organization);

	/**
	 * 更新單位資料
	 * 
	 * @param Organization
	 * @return
	 */
	Integer updateOrganizationById(Organization organization);

	/**
	 * 刪除單位資料
	 * 
	 * @param organizationId
	 * @return
	 */
	Integer deleteOrganizationById(Organization organization);

}
