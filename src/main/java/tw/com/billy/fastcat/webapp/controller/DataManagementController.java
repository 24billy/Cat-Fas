package tw.com.billy.fastcat.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.billy.fastcat.core.business.service.ISubjectService;
import tw.com.billy.fastcat.core.db.model.Subject;
import tw.com.billy.fastcat.core.util.JsonUtil;

/**
 * [資料管理]導頁控制器
 * 
 * @author Billy
 * 
 */
@Controller
@RequestMapping("/dataManagement")
public class DataManagementController {

	@Autowired
	ISubjectService subjectService;

	@RequestMapping("/showDataManagement")
	public String showDataManagementMain(Model model) {
		List<Subject> subjectList = subjectService.getAllSubject();
		String jsonStr = JsonUtil.toJson(subjectList);
		model.addAttribute("subjectList", jsonStr);

		return "pages/dataManagement";
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