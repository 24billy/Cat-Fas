package tw.com.billy.fastcat.core.db.model;

/**
 * [成員] 表格物件
 * 
 * @author Billy
 *
 */
public class Member {

	private Integer memberId;
	private String account;
	private String name;
	private Organization organization;
	private String identity;
	private Role role;
	private String verifyCode;
	private String createDate;
	private String createUser;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", account=" + account + ", name=" + name + ", organization="
				+ organization + ", identity=" + identity + ", role=" + role + ", verifyCode=" + verifyCode
				+ ", createDate=" + createDate + ", createUser=" + createUser + "]";
	}

}