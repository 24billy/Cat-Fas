package tw.com.billy.fastcat.core.db.model;

/**
 * [受試者] 表格物件
 * 
 * @author Billy
 *
 */
public class Subject {

	private Integer subjectId;
	private Integer organizationId;
	private String medicalNumber;
	private String subjectName;
	private String birthday;
	private String gender;
	private Integer createMemberId;
	private String createdate;

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getMedicalNumber() {
		return medicalNumber;
	}

	public void setMedicalNumber(String medicalNumber) {
		this.medicalNumber = medicalNumber;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getCreateMemberId() {
		return createMemberId;
	}

	public void setCreateMemberId(Integer createMemberId) {
		this.createMemberId = createMemberId;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", organizationId=" + organizationId + ", medicalNumber="
				+ medicalNumber + ", subjectName=" + subjectName + ", birthday=" + birthday + ", gender=" + gender
				+ ", createMemberId=" + createMemberId + ", createdate=" + createdate + "]";
	}

}
