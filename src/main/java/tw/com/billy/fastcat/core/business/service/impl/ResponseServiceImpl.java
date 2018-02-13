package tw.com.billy.fastcat.core.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tw.com.billy.fastcat.core.business.service.IResponseService;
import tw.com.billy.fastcat.core.db.jdbc.JdbcDAO;
import tw.com.billy.fastcat.core.db.model.Member;
import tw.com.billy.fastcat.core.db.model.Response;
import tw.com.billy.fastcat.core.db.model.Subject;

/**
 * [作答反應]服務實作
 * 
 * @author Billy
 *
 */
@Service("responseDao")
public class ResponseServiceImpl implements IResponseService {

	@Autowired
	@Qualifier("jdbcDAO")
	private JdbcDAO jdbcDAO;

	@Override
	public List<Response> getAllResponse() {
		StringBuilder sqlText = new StringBuilder();
		sqlText.append("SELECT ");
		sqlText.append("T1.responseid,");
		sqlText.append("T1.subjectid,");
		sqlText.append("T1.iscomplete,");
		sqlText.append("T1.startdate,");
		sqlText.append("T1.examinerid,");
		sqlText.append("T1.answer,");
		sqlText.append("T1.isdelete,");
		sqlText.append("T1.chooseditem,");
		sqlText.append("T2.medicalnumber,");
		sqlText.append("T2.subjectname");
		sqlText.append(" FROM response T1");
		sqlText.append(" LEFT JOIN subject T2");
		sqlText.append(" ON T1.subjectid = T2.subjectid");

		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText.toString());

		List<Response> responseList = new ArrayList<Response>();

		for (Map<String, Object> map : resultList) {
			Response response = new Response();
			response.setRecordId((Integer) map.get("responseid"));
			response.setIsComplete((Boolean) map.get("iscomplete"));
			response.setStartDate((String) map.get("startdate"));
			response.setAnswer((String) map.get("answer"));
			response.setIsDelete((Boolean) map.get("isdelete"));
			response.setChoosedItem((String) map.get("chooseditem"));

			Subject subject = new Subject();
			subject.setSubjectId((Integer) map.get("subjectid"));
			subject.setMedicalNumber((Integer) map.get("medicalnumber"));
			subject.setSubjectName((String) map.get("subjectname"));
			response.setSubject(subject);

			Member member = new Member();
			member.setMemberId((Integer) map.get("examinerid"));
			response.setExaminer(member);

			responseList.add(response);
		}

		return responseList;
	}

	@Override
	public List<Response> getResponseBySubjectId(Subject subject) {
		Integer subjectId = subject.getSubjectId();
		StringBuilder sqlText = new StringBuilder();
		sqlText.append("SELECT ");
		sqlText.append("T1.responseid,");
		sqlText.append("T1.subjectid,");
		sqlText.append("T1.iscomplete,");
		sqlText.append("T1.startdate,");
		sqlText.append("T1.examinerid,");
		sqlText.append("T1.answer,");
		sqlText.append("T1.isdelete,");
		sqlText.append("T1.chooseditem,");
		sqlText.append("T2.medicalnumber,");
		sqlText.append("T2.subjectname");
		sqlText.append(" FROM response T1");
		sqlText.append(" LEFT JOIN subject T2");
		sqlText.append(" ON T1.subjectid = T2.subjectid");
		sqlText.append(" WHERE");
		sqlText.append(" T1.subjectid = " + subjectId);

		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText.toString());

		List<Response> responseList = new ArrayList<Response>();

		for (Map<String, Object> map : resultList) {
			Response response = new Response();
			response.setRecordId((Integer) map.get("responseid"));
			response.setIsComplete((Boolean) map.get("iscomplete"));
			response.setStartDate((String) map.get("startdate"));
			response.setAnswer((String) map.get("answer"));
			response.setIsDelete((Boolean) map.get("isdelete"));
			response.setChoosedItem((String) map.get("chooseditem"));

			subject.setMedicalNumber((Integer) map.get("medicalnumber"));
			subject.setSubjectName((String) map.get("subjectname"));
			response.setSubject(subject);

			Member member = new Member();
			member.setMemberId((Integer) map.get("examinerid"));
			response.setExaminer(member);

			responseList.add(response);
		}

		return responseList;
	}

	@Override
	public Response addResponse(Response response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateResponse(Response response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteResponse(Response response) {
		// TODO Auto-generated method stub
		return null;
	}

}
