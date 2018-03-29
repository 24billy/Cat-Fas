package tw.com.billy.fastcat.core.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tw.com.billy.fastcat.core.business.service.ISubjectService;
import tw.com.billy.fastcat.core.db.jdbc.JdbcDAO;
import tw.com.billy.fastcat.core.db.model.Subject;

@Service("subjectDao")
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	@Qualifier("jdbcDAO")
	private JdbcDAO jdbcDAO;

	@Override
	public List<Subject> getAllSubject() {
		StringBuilder sqlText = new StringBuilder();
		sqlText.append("SELECT *");
		sqlText.append(" FROM subject");
		sqlText.append(" ORDER BY subjectId");

		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText.toString());
		List<Subject> subjectList = new ArrayList<Subject>();

		for (Map<String, Object> map : resultList) {
			Subject subject = new Subject();
			subject.setSubjectId((Integer) map.get("subjectid"));
			subject.setOrganizationId((Integer) map.get("organizationid"));
			subject.setSubjectName((String) map.get("subjectname"));
			subject.setMedicalNumber((Integer) map.get("medicalnumber"));
			subject.setCreatedate((String) map.get("createdate"));
			subject.setBirthday((String) map.get("birthday"));
			subject.setGender((String) map.get("gender"));
			subject.setCreateMemberId((Integer) map.get("creatememberid"));

			subjectList.add(subject);
		}

		return subjectList;
	}

	@Override
	public List<Subject> getSubjectByExaminerId(Subject examiner) {
		Integer subjectId = examiner.getCreateMemberId();

		StringBuilder sqlText = new StringBuilder();
		sqlText.append("SELECT *");
		sqlText.append(" FROM subject");
		sqlText.append(" WHERE creatememberid = " + subjectId);
		sqlText.append(" ORDER BY subjectId");

		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText.toString());
		List<Subject> subjectList = new ArrayList<Subject>();

		for (Map<String, Object> map : resultList) {
			Subject subject = new Subject();
			subject.setSubjectId((Integer) map.get("subjectid"));
			subject.setOrganizationId((Integer) map.get("organizationid"));
			subject.setSubjectName((String) map.get("subjectname"));
			subject.setMedicalNumber((Integer) map.get("medicalnumber"));
			subject.setCreatedate((String) map.get("createdate"));
			subject.setBirthday((String) map.get("birthday"));
			subject.setGender((String) map.get("gender"));
			subject.setCreateMemberId((Integer) map.get("creatememberid"));

			subjectList.add(subject);
		}

		return subjectList;
	}

	@Override
	public Integer addSubject(Subject subject) {
		StringBuilder sqlText = new StringBuilder(
				"INSERT INTO SUBJECT (subjectid,organizationid,medicalnumber,subjectname,createdate,birthday,gender,creatememberid) VALUES (");
		sqlText.append(subject.getSubjectId() + ", ");
		sqlText.append(subject.getOrganizationId() + ", ");
		sqlText.append(subject.getMedicalNumber() + ", '");
		sqlText.append(subject.getSubjectName() + "', '");
		sqlText.append(subject.getCreatedate() + "', '");
		sqlText.append(subject.getBirthday() + "', '");
		sqlText.append(subject.getGender() + "', ");
		sqlText.append(subject.getCreateMemberId() + ")");

		Integer updateCount = jdbcDAO.update(sqlText.toString());

		return updateCount;
	}

	@Override
	public Integer updateSubject(Subject subject) {
		Integer subjectId = subject.getSubjectId();
		String subjectName = subject.getSubjectName();
		Integer medicalNumber = subject.getMedicalNumber();
		Integer organizationId = subject.getOrganizationId();
		String gender = subject.getGender();
		String birthday = subject.getBirthday();
		String createDate = subject.getCreatedate();
		Integer createMemberId = subject.getCreateMemberId();

		StringBuilder sqlText = new StringBuilder();
		sqlText.append("UPDATE SUBJECT SET ");
		sqlText.append("subjectName = '");
		sqlText.append(subjectName);
		sqlText.append("', medicalNumber = '");
		sqlText.append(medicalNumber);
		sqlText.append("', organizationId = '");
		sqlText.append(organizationId);
		sqlText.append("', gender = '");
		sqlText.append(gender);
		sqlText.append("', birthday = '");
		sqlText.append(birthday);
		sqlText.append("', createDate = '");
		sqlText.append(createDate);
		sqlText.append("', createMemberId = ");
		sqlText.append(createMemberId);
		sqlText.append("WHERE subjectId = ");
		sqlText.append(subjectId);

		Integer updateCount = jdbcDAO.update(sqlText.toString());

		return updateCount;
	}

	@Override
	public Integer deleteSubject(Subject subject) {
		Integer subjectId = subject.getSubjectId();
		String sqlText = "DELETE FROM SUBJECT WHERE subjectId = $P{subjectId}";
		sqlText = sqlText.replace("$P{subjectId}", subjectId.toString());

		Integer deleteCount = jdbcDAO.update(sqlText);

		return deleteCount;
	}

}
