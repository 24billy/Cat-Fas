package tw.com.billy.fastcat.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.billy.fastcat.core.business.service.IMemberService;
import tw.com.billy.fastcat.core.db.model.Member;

/**
 * [登入]導頁控制器
 * 
 * @author Billy
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	IMemberService memberService;

	@RequestMapping("/login")
	public String login(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password, Model model,
			HttpServletRequest request) {
		Member member = new Member();
		member.setAccount(userName);
		member = memberService.getMemberByAccount(member);

		Integer roleId = member.getRole().getRoleId();
		Integer memberId = member.getMemberId();
		Integer organizationId = member.getOrganization().getOrganizationid();

		if (password.equals(member.getVerifyCode())) {
			request.setAttribute("result", "true");
			request.getSession().setAttribute("userName", userName);
			request.getSession().setAttribute("memberId", memberId);
			request.getSession().setAttribute("organizationId", organizationId);
			request.getSession().setAttribute("isLogin", true);
			request.getSession().setAttribute("roleId", roleId);

			return "common/result";
		}

		request.setAttribute("result", "false");

		return "common/result";
	}

	@RequestMapping("/showMainView")
	public String showMainView(HttpServletRequest request) {
		Integer roleId = (Integer) request.getSession().getAttribute("roleId");
		request.setAttribute("roleId", roleId);

		if (roleId != null && roleId.equals(1)) {
			return "redirect:/main";
		} else if (roleId != null && roleId.equals(2)) {
			return "redirect:/main_basic";
		}

		return "redirect:/main";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("isLogin");

		return "redirect:/";
	}

}
