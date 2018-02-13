package tw.com.billy.fastcat.core.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.billy.fastcat.SpringTest;
import tw.com.billy.fastcat.core.business.service.IMemberService;
import tw.com.billy.fastcat.core.db.model.Member;
import tw.com.billy.fastcat.core.db.model.Organization;
import tw.com.billy.fastcat.core.db.model.Role;

public class MemberServiceImplTest extends SpringTest {

	@Autowired
	IMemberService memberService;

	@Test
	public void testGetMemberByMemberId() {
		Member member = new Member();
		member.setMemberId(1);

		System.out.println(memberService.getMemberByMemberId(member));
	}

	@Test
	public void testGetMemberByAccount() {
		Member member = new Member();
		member.setAccount("Billy");

		member = memberService.getMemberByAccount(member);
		System.out.println(member);
	}
	
	@Test
	public void testGetAllMember() {
		System.out.println(memberService.getAllMember());
	}

	@Test
	public void testAddMember() {
		Member member = new Member();
		member.setMemberId(3);
		member.setAccount("allen");
		member.setName("艾倫");
		member.setIdentity("管理者");
		Role role = new Role();
		role.setRoleId(1);
		member.setRole(role);
		member.setVerifyCode("allen");
		member.setCreateUser("billy");
		Organization organization = new Organization();
		organization.setOrganizationid(1);
		member.setOrganization(organization);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String dateStr = format.format(today);
		member.setCreateDate(dateStr);

		memberService.addMember(member);
	}

	@Test
	public void testUpdateMember() {
		Member member = new Member();
		member.setMemberId(2);
		member.setAccount("Billy");
		member.setName("比利");
		member.setIdentity("管理者");
		Role role = new Role();
		role.setRoleId(1);
		member.setRole(role);
		Organization organization = new Organization();
		organization.setOrganizationid(1);
		member.setOrganization(organization);

		memberService.updateMember(member);
	}

	@Test
	public void testDeleteMember() {
		Member member = new Member();
		member.setMemberId(3);

		memberService.deleteMember(member);
	}

}
