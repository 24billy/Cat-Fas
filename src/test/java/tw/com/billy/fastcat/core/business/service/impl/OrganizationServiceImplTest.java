package tw.com.billy.fastcat.core.business.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.billy.fastcat.SpringTest;
import tw.com.billy.fastcat.core.business.service.IOrganizationService;
import tw.com.billy.fastcat.core.db.model.Organization;

public class OrganizationServiceImplTest extends SpringTest {

	@Autowired
	IOrganizationService organizationService;

	@Test
	public void testGetOrganizationById() {
		Integer id = 1;
		Organization organization = organizationService.getOrganizationById(id);

		System.out.println(organization);
	}

	@Test
	public void testGetAllOrganization() {
		List<Organization> organizationList = organizationService.getAllOrganization();

		System.out.println(organizationList);
	}

	@Test
	public void testAddOrganization() {
		Organization organization = new Organization(3, "中正", true);
		organizationService.addOrganization(organization);

		List<Organization> organizationList = organizationService.getAllOrganization();

		System.out.println(organizationList);
	}

	@Test
	public void testUpdateOrganizationById() {
		Organization organization = new Organization(2, "政治大學", true);
		organizationService.updateOrganizationById(organization);

		List<Organization> organizationList = organizationService.getAllOrganization();

		System.out.println(organizationList);
	}

	@Test
	public void testDeleteOrganizationById() {
		Organization organization = new Organization(3, "清大", true);
		organizationService.deleteOrganizationById(organization);

		List<Organization> organizationList = organizationService.getAllOrganization();

		System.out.println(organizationList);
	}

}
