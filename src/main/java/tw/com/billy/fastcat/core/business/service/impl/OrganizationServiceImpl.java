package tw.com.billy.fastcat.core.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tw.com.billy.fastcat.core.business.service.IOrganizationService;
import tw.com.billy.fastcat.core.db.jdbc.JdbcDAO;
import tw.com.billy.fastcat.core.db.model.Organization;

/**
 * [單位]服務實作
 * 
 * @author Billy
 *
 */
@Service("organizationDao")
public class OrganizationServiceImpl implements IOrganizationService {

	@Autowired
	@Qualifier("jdbcDAO")
	private JdbcDAO jdbcDAO;

	@Override
	public Organization getOrganizationById(Integer organizationId) {
		String sqlText = "SELECT * FROM ORGANIZATION WHERE organizationId = $P{organizationId}";
		sqlText = sqlText.replace("$P{organizationId}", organizationId.toString());
		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText);

		Integer id = (Integer) resultList.get(0).get("organizationid");
		String name = (String) resultList.get(0).get("name");
		Boolean visible = (Boolean) resultList.get(0).get("visible");

		Organization organization = new Organization(id, name, visible);

		return organization;
	}

	@Override
	public List<Organization> getAllOrganization() {
		String sqlText = "SELECT * FROM ORGANIZATION ORDER BY ORGANIZATIONID";
		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText);

		List<Organization> organizationList = new ArrayList<Organization>();

		for (Map<String, Object> map : resultList) {
			Integer id = (Integer) map.get("organizationid");
			String name = (String) map.get("name");
			Boolean visible = (Boolean) map.get("visible");

			Organization organization = new Organization(id, name, visible);
			organizationList.add(organization);
		}

		return organizationList;
	}

	@Override
	public Integer addOrganization(Organization organization) {
		Integer organizationId = organization.getOrganizationid();
		String name = organization.getName();
		Boolean visible = organization.getIsVisible();

		String sqlText = "INSERT INTO ORGANIZATION (organizationid, name, visible) VALUES ( $P{organizationId}, '$P{name}', $P{visible})";
		sqlText = sqlText.replace("$P{organizationId}", organizationId.toString());
		sqlText = sqlText.replace("$P{name}", name.toString());
		sqlText = sqlText.replace("$P{visible}", visible.toString());

		Integer updateCount = jdbcDAO.update(sqlText);

		return updateCount;
	}

	@Override
	public Integer updateOrganizationById(Organization organization) {
		Integer organizationId = organization.getOrganizationid();
		String name = organization.getName();
		Boolean visible = organization.getIsVisible();

		String sqlText = "UPDATE organization SET name = '$P{name}', visible = $P{visible} WHERE organizationid = $P{organizationId}";
		sqlText = sqlText.replace("$P{organizationId}", organizationId.toString());
		sqlText = sqlText.replace("$P{name}", name.toString());
		sqlText = sqlText.replace("$P{visible}", visible.toString());

		Integer updateCount = jdbcDAO.update(sqlText);

		return updateCount;
	}

	@Override
	public Integer deleteOrganizationById(Organization organization) {
		Integer organizationId = organization.getOrganizationid();
		String sqlText = "DELETE FROM ORGANIZATION WHERE organizationid = $P{organizationId}";
		sqlText = sqlText.replace("$P{organizationId}", organizationId.toString());

		Integer deleteCount = jdbcDAO.update(sqlText);

		return deleteCount;
	}

}
