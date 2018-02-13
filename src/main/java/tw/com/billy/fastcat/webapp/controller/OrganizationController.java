package tw.com.billy.fastcat.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * [單位]導頁控制器
 * 
 * @author Billy
 * 
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

	@RequestMapping("/showOrganization")
	public String showOrganizationMain() {
		return "pages/organization";
	}

	@RequestMapping("/addOrganization")
	public String addOrganization(Model model) {
		return "main"; // 頁面導向 /WEB-INF/views/ 搜索檔案類型.jsp
	}

	@RequestMapping("/updateOrganization")
	public String updateOrganization(@RequestParam(value = "webSite", required = false) String webSite, Model model) {
		return "redirect:" + webSite; // 頁面導向 /WEB-INF/views/ 搜索檔案類型.jsp
	}

	@RequestMapping("/deleteOrganization")
	public String deleteOrganization(Model model) {
		return "main"; // 頁面導向 /WEB-INF/views/ 搜索檔案類型.jsp
	}

}