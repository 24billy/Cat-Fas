package tw.com.billy.fastcat.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.billy.fastcat.core.business.service.IMemberService;
import tw.com.billy.fastcat.core.business.service.IOrganizationService;
import tw.com.billy.fastcat.core.db.model.Member;
import tw.com.billy.fastcat.core.db.model.Organization;
import tw.com.billy.fastcat.core.db.model.Role;
import tw.com.billy.fastcat.core.util.JsonUtil;

/**
 * [成員]導頁控制器
 * 
 * @author Billy
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	IMemberService memberService;

	@Autowired
	IOrganizationService organizationService;

	@RequestMapping("/showMember")
	public String showMember(Model model) {
		// 取得訪員資訊
		List<Member> subjectList = memberService.getAllMember();
		String jsonStr = JsonUtil.toJson(subjectList);
		model.addAttribute("memberList", jsonStr);

		// 取得單位資訊
		List<Organization> organizationList = organizationService.getAllOrganization();
		String organizationJsonStr = JsonUtil.toJson(organizationList);
		model.addAttribute("organizationList", organizationJsonStr);

		return "pages/member";
	}

	@RequestMapping("/addMember")
	public String addMember(HttpServletRequest request,
			@RequestParam(value = "account", required = true) String account,
			@RequestParam(value = "organizationId", required = true) Integer organizationId,
			@RequestParam(value = "roleId", required = true) Integer roleId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "identity", required = true) String identity,
			@RequestParam(value = "verifyCode", required = true) String verifyCode) {
		// 取得memberId
		List<Member> subjectList = memberService.getAllMember();
		Integer memberId = subjectList.get(subjectList.size() - 1).getMemberId() + 1;

		// 取得單位資訊
		Organization organization = new Organization();
		organization.setOrganizationid(organizationId);

		// 設定角色編號
		Role role = new Role();
		role.setRoleId(roleId);

		// 取得建立者
		String createUser = (String) request.getSession().getAttribute("userName");

		Member member = new Member();
		member.setMemberId(memberId);
		member.setAccount(account);
		member.setName(name);
		member.setOrganization(organization);
		member.setRole(role);
		member.setVerifyCode(verifyCode);
		member.setIdentity(identity);
		member.setCreateUser(createUser);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String dateStr = format.format(today);
		member.setCreateDate(dateStr);

		memberService.addMember(member);

		return "common/result";
	}

	@RequestMapping("/updateMember")
	public String updateMember(HttpServletRequest request,
			@RequestParam(value = "memberId", required = true) Integer memberId,
			@RequestParam(value = "organizationId", required = true) Integer organizationId,
			@RequestParam(value = "roleId", required = true) Integer roleId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "identity", required = true) String identity,
			@RequestParam(value = "verifyCode", required = true) String verifyCode) {
		Member member = new Member();
		member.setMemberId(memberId);
		member = memberService.getMemberByMemberId(member);

		// 取得單位資訊
		Organization organization = new Organization();
		organization.setOrganizationid(organizationId);

		// 設定角色編號
		Role role = new Role();
		role.setRoleId(roleId);

		// 取得建立者
		String createUser = (String) request.getSession().getAttribute("userName");

		if (!StringUtils.isEmpty(name)) {
			member.setName(name);
		}

		if (!StringUtils.isEmpty(identity)) {
			member.setIdentity(identity);
		}

		if (!StringUtils.isEmpty(verifyCode)) {
			member.setVerifyCode(verifyCode);
		}

		member.setOrganization(organization);
		member.setRole(role);
		member.setCreateUser(createUser);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String dateStr = format.format(today);
		member.setCreateDate(dateStr);

		memberService.updateMember(member);

		return "common/result";
	}

	@RequestMapping("/deleteMember")
	public String deleteMember(@RequestParam(value = "memberId", required = true) Integer memberId) {
		Member member = new Member();
		member.setMemberId(memberId);

		memberService.deleteMember(member);

		return "common/result";
	}

}