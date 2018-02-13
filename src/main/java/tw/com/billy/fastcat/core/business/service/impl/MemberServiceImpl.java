package tw.com.billy.fastcat.core.business.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tw.com.billy.fastcat.core.business.service.IMemberService;
import tw.com.billy.fastcat.core.db.jdbc.JdbcDAO;
import tw.com.billy.fastcat.core.db.model.Member;
import tw.com.billy.fastcat.core.db.model.Organization;
import tw.com.billy.fastcat.core.db.model.Role;

/**
 * [成員] 服務實作
 * 
 * @author Billy
 *
 */
@Service("memberDao")
public class MemberServiceImpl implements IMemberService {

	@Autowired
	@Qualifier("jdbcDAO")
	private JdbcDAO jdbcDAO;

	@Override
	public Member getMemberByMemberId(Member member) {
		Integer memberId = member.getMemberId();

		StringBuilder sqlText = new StringBuilder();
		sqlText.append("SELECT ");
		sqlText.append("T1.memberid,");
		sqlText.append("T1.account,");
		sqlText.append("T1.name AS memberName,");
		sqlText.append("T1.organizationid,");
		sqlText.append("T2.name AS organizationName,");
		sqlText.append("T2.visible AS isVisible,");
		sqlText.append("T1.identity,");
		sqlText.append("T1.createdate,");
		sqlText.append("T1.createuser,");
		sqlText.append("T1.verifycode,");
		sqlText.append("T1.roleid,");
		sqlText.append("T3.roleName");
		sqlText.append(" FROM member T1");
		sqlText.append(" LEFT JOIN organization T2");
		sqlText.append(" ON T1.organizationid = T2.organizationid");
		sqlText.append(" LEFT JOIN role T3");
		sqlText.append(" ON T1.roleid = T3.roleid");
		sqlText.append(" WHERE T1.memberId = " + memberId);
		sqlText.append(" ORDER BY T1.memberId");

		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText.toString());

		member = new Member();
		member.setMemberId((Integer) resultList.get(0).get("memberid"));
		member.setAccount((String) resultList.get(0).get("account"));
		member.setName((String) resultList.get(0).get("membername"));
		member.setIdentity((String) resultList.get(0).get("identity"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		member.setCreateDate(format.format((Date) resultList.get(0).get("createdate")));
		member.setCreateUser((String) resultList.get(0).get("createuser"));
		member.setVerifyCode((String) resultList.get(0).get("verifycode"));

		Organization organization = new Organization((Integer) resultList.get(0).get("organizationid"),
				(String) resultList.get(0).get("organizationname"), (Boolean) resultList.get(0).get("isVisible"));
		member.setOrganization(organization);

		Role role = new Role((Integer) resultList.get(0).get("roleid"), (String) resultList.get(0).get("rolename"));
		member.setRole(role);

		return member;
	}
	
	@Override
	public Member getMemberByAccount(Member member) {
		String account = member.getAccount();

		StringBuilder sqlText = new StringBuilder();
		sqlText.append("SELECT ");
		sqlText.append("T1.memberid,");
		sqlText.append("T1.account,");
		sqlText.append("T1.name AS memberName,");
		sqlText.append("T1.organizationid,");
		sqlText.append("T2.name AS organizationName,");
		sqlText.append("T2.visible AS isVisible,");
		sqlText.append("T1.identity,");
		sqlText.append("T1.createdate,");
		sqlText.append("T1.createuser,");
		sqlText.append("T1.verifycode,");
		sqlText.append("T1.roleid,");
		sqlText.append("T3.roleName");
		sqlText.append(" FROM member T1");
		sqlText.append(" LEFT JOIN organization T2");
		sqlText.append(" ON T1.organizationid = T2.organizationid");
		sqlText.append(" LEFT JOIN role T3");
		sqlText.append(" ON T1.roleid = T3.roleid");
		sqlText.append(" WHERE T1.account = '" + account + "'");

		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText.toString());

		member = new Member();
		member.setMemberId((Integer) resultList.get(0).get("memberid"));
		member.setAccount((String) resultList.get(0).get("account"));
		member.setName((String) resultList.get(0).get("membername"));
		member.setIdentity((String) resultList.get(0).get("identity"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		member.setCreateDate(format.format((Date) resultList.get(0).get("createdate")));
		member.setCreateUser((String) resultList.get(0).get("createuser"));
		member.setVerifyCode((String) resultList.get(0).get("verifycode"));

		Organization organization = new Organization((Integer) resultList.get(0).get("organizationid"),
				(String) resultList.get(0).get("organizationname"), (Boolean) resultList.get(0).get("isVisible"));
		member.setOrganization(organization);

		Role role = new Role((Integer) resultList.get(0).get("roleid"), (String) resultList.get(0).get("rolename"));
		member.setRole(role);

		return member;
	}

	@Override
	public List<Member> getAllMember() {
		StringBuilder sqlText = new StringBuilder();
		sqlText.append("SELECT ");
		sqlText.append("T1.memberid,");
		sqlText.append("T1.account,");
		sqlText.append("T1.name AS memberName,");
		sqlText.append("T1.organizationid,");
		sqlText.append("T2.name AS organizationName,");
		sqlText.append("T2.visible AS isVisible,");
		sqlText.append("T1.identity,");
		sqlText.append("T1.createdate,");
		sqlText.append("T1.createuser,");
		sqlText.append("T1.verifycode,");
		sqlText.append("T1.roleid,");
		sqlText.append("T3.roleName");
		sqlText.append(" FROM member T1");
		sqlText.append(" LEFT JOIN organization T2");
		sqlText.append(" ON T1.organizationid = T2.organizationid");
		sqlText.append(" LEFT JOIN role T3");
		sqlText.append(" ON T1.roleid = T3.roleid");
		sqlText.append(" ORDER BY T1.memberId");

		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText.toString());
		List<Member> memberList = new ArrayList<Member>();

		for (Map<String, Object> map : resultList) {
			Member member = new Member();
			member.setMemberId((Integer) map.get("memberid"));
			member.setAccount((String) map.get("account"));
			member.setName((String) map.get("membername"));
			member.setIdentity((String) map.get("identity"));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			member.setCreateDate(format.format((Date) map.get("createdate")));
			member.setCreateUser((String) map.get("createuser"));
			member.setVerifyCode((String) map.get("verifycode"));

			Organization organization = new Organization((Integer) map.get("organizationid"),
					(String) map.get("organizationname"), (Boolean) map.get("isVisible"));
			member.setOrganization(organization);

			Role role = new Role((Integer) map.get("roleid"), (String) map.get("rolename"));
			member.setRole(role);

			memberList.add(member);
		}

		return memberList;
	}

	@Override
	public Member addMember(Member member) {
		Integer memberId = member.getMemberId();
		String account = member.getAccount();
		String name = member.getName();
		Organization organization = member.getOrganization();
		String identity = member.getIdentity();
		String createDate = member.getCreateDate();
		String createUser = member.getCreateUser();
		String verifyCode = member.getVerifyCode();
		Role role = member.getRole();

		StringBuilder sqlText = new StringBuilder();
		sqlText.append(
				"INSERT INTO member(memberid, account, name, organizationid, identity, createdate,createuser, verifycode, roleid) VALUES (");
		sqlText.append(memberId);
		sqlText.append(",'");
		sqlText.append(account);
		sqlText.append("','");
		sqlText.append(name);
		sqlText.append("',");
		sqlText.append(organization.getOrganizationid());
		sqlText.append(",'");
		sqlText.append(identity);
		sqlText.append("','");
		sqlText.append(createDate);
		sqlText.append("','");
		sqlText.append(createUser);
		sqlText.append("','");
		sqlText.append(verifyCode);
		sqlText.append("', ");
		sqlText.append(role.getRoleId());
		sqlText.append(");");

		System.out.println(sqlText.toString());
		Integer updateCount = jdbcDAO.update(sqlText.toString());
		if (updateCount > 0) {

		}

		return member;
	}

	@Override
	public Member updateMember(Member member) {
		Integer memberId = member.getMemberId();
		String account = member.getAccount();
		String name = member.getName();
		Organization organization = member.getOrganization();
		String identity = member.getIdentity();
		String createDate = member.getCreateDate();
		String createUser = member.getCreateUser();
		String verifyCode = member.getVerifyCode();
		Role role = member.getRole();

		StringBuilder sqlText = new StringBuilder();
		sqlText.append("UPDATE MEMBER SET ");
		sqlText.append("memberid = ");
		sqlText.append(memberId);
		sqlText.append(", account = '");
		sqlText.append(account);
		sqlText.append("', name = '");
		sqlText.append(name);
		sqlText.append("', organizationid = '");
		sqlText.append(organization.getOrganizationid());
		sqlText.append("', identity = '");
		sqlText.append(identity);

		if (createDate != null) {
			sqlText.append("', createdate = '");
			sqlText.append(createDate);
		}

		if (createUser != null) {
			sqlText.append("', createuser = '");
			sqlText.append(createUser);
		}
		if (verifyCode != null) {
			sqlText.append("', verifycode = '");
			sqlText.append(verifyCode);
		}
		sqlText.append("', roleid = ");
		sqlText.append(role.getRoleId());
		sqlText.append("WHERE memberId = ");
		sqlText.append(memberId);
		System.out.println(sqlText.toString());
		Integer updateCount = jdbcDAO.update(sqlText.toString());

		if (updateCount > 0) {
			System.out.println("updateCount : " + updateCount);
		}

		return member;
	}

	@Override
	public Integer deleteMember(Member member) {
		Integer memberId = member.getMemberId();

		StringBuilder sqlText = new StringBuilder();
		sqlText.append("DELETE FROM member WHERE memberId = ");
		sqlText.append(memberId);

		Integer deleteCount = jdbcDAO.update(sqlText.toString());

		return deleteCount;
	}

}