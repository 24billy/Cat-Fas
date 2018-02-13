package tw.com.billy.fastcat.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * [共通]導頁控制器
 * 
 * @author Billy
 * 
 */
@Controller
public class GeneralController {

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		request.getSession().setAttribute("isLogin", false);

		return "index"; // RootPath
	}

	@RequestMapping("/main")
	public String showFullFunctionMain(HttpServletRequest request) {
		Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");

		return "main"; // 頁面導向 /WEB-INF/views/ 搜索檔案類型.jsp
	}

	@RequestMapping("/main_basic")
	public String showTestFunctionMain(HttpServletRequest request) {
		Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");

		return "main_basic"; // 頁面導向 /WEB-INF/views/ 搜索檔案類型.jsp
	}

	@RequestMapping("/outWebSite")
	public String outWebSite(@RequestParam(value = "webSite", required = false) String webSite, Model model) {
		return "redirect:" + webSite; // 頁面導向 /WEB-INF/views/ 搜索檔案類型.jsp
	}

}