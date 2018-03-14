package tw.com.billy.fastcat.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.billy.fastcat.core.business.service.IOrganizationService;
import tw.com.billy.fastcat.core.db.model.Organization;
import tw.com.billy.fastcat.core.util.JsonUtil;

/**
 * [單位]導頁控制器
 * 
 * @author Billy
 * 
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	IOrganizationService organizationService;

	@RequestMapping("/showOrganization")
	public String showOrganizationMain(Model model) {
		List<Organization> organizationList = organizationService.getAllOrganization();
		String jsonStr = JsonUtil.toJson(organizationList);
		model.addAttribute("organizationList", jsonStr);

		return "pages/organization";
	}

	@RequestMapping("/addOrganization")
	public String addOrganization(@RequestParam(value = "organizationName", required = true) String organizationName) {
		List<Organization> organizationList = organizationService.getAllOrganization();
		Integer organizationId = organizationList.get(organizationList.size() - 1).getOrganizationid() + 1;
		Organization organization = new Organization(organizationId, organizationName, true);

		organizationService.addOrganization(organization);

		return "common/result";
	}

	@RequestMapping("/updateOrganization")
	public String updateOrganization(@RequestParam(value = "organizationId", required = true) Integer organizationId,
			@RequestParam(value = "organizationName", required = true) String organizationName) {
		Organization organization = new Organization(organizationId, organizationName, true);

		organizationService.updateOrganizationById(organization);

		return "common/result";
	}

	@RequestMapping("/deleteOrganization")
	public String deleteOrganization(@RequestParam(value = "organizationId", required = true) Integer organizationId) {
		Organization organization = new Organization();
		organization.setOrganizationid(organizationId);
		organizationService.deleteOrganizationById(organization);

		return "common/result";
	}

}