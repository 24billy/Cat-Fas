package tw.com.billy.fastcat.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * [成員]導頁控制器
 * 
 * @author Billy
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/showMember")
	public String showMember(HttpServletRequest request) {
		return "pages/member";
	}

}