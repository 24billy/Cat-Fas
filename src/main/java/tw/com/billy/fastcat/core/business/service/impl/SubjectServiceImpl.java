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
	public Subject addSubject(Subject subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteSubject(Subject subject) {
		// TODO Auto-generated method stub
		return null;
	}

}
