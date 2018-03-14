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

		sqlText.append("T1.tscore,");
		sqlText.append("T1.upper95,");
		sqlText.append("T1.lower95,");
		sqlText.append("T1.reliability,");
		sqlText.append("T1.ability,");
		sqlText.append("T1.examtype,");
		sqlText.append("T1.percentilelevel,");

		sqlText.append("T2.medicalnumber,");
		sqlText.append("T2.subjectname");
		sqlText.append(" FROM response T1");
		sqlText.append(" LEFT JOIN subject T2");
		sqlText.append(" ON T1.subjectid = T2.subjectid");
		sqlText.append(" ORDER BY T1.responseid");
		 

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

			response.settScore((String) map.get("tscore"));
			response.setUpper95((String) map.get("upper95"));
			response.setLower95((String) map.get("lower95"));
			response.setReliability((String) map.get("reliability"));
			response.setExamType((String) map.get("examtype"));
			response.setPercentileLevel((String) map.get("percentilelevel"));
			response.setAbility((String) map.get("ability"));

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

		sqlText.append("T1.tscore,");
		sqlText.append("T1.upper95,");
		sqlText.append("T1.lower95,");
		sqlText.append("T1.reliability,");
		sqlText.append("T1.ability,");
		sqlText.append("T1.examtype,");
		sqlText.append("T1.percentilelevel,");

		sqlText.append("T2.medicalnumber,");
		sqlText.append("T2.subjectname");
		sqlText.append(" FROM response T1");
		sqlText.append(" LEFT JOIN subject T2");
		sqlText.append(" ON T1.subjectid = T2.subjectid");
		sqlText.append(" WHERE");
		sqlText.append(" T1.subjectid = " + subjectId);
		sqlText.append(" ORDER BY T1.responseid");

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

			response.settScore((String) map.get("tscore"));
			response.setUpper95((String) map.get("upper95"));
			response.setLower95((String) map.get("lower95"));
			response.setReliability((String) map.get("reliability"));
			response.setExamType((String) map.get("examtype"));
			response.setPercentileLevel((String) map.get("percentilelevel"));
			response.setAbility((String) map.get("ability"));

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
	public Integer addResponse(Response response) {
		Integer recordId = response.getRecordId();

		// 受測者編號
		Subject subject = response.getSubject();
		Integer subjectId = subject.getSubjectId();
		Boolean isComplete = response.getIsComplete();
		String startDate = response.getStartDate();

		// 施測者編號
		Member member = response.getExaminer();
		Integer memberId = member.getMemberId();
		String answer = response.getAnswer();
		Boolean isDelete = response.getIsDelete();
		String choosedItem = response.getChoosedItem();

		StringBuilder sqlText = new StringBuilder(
				"INSERT INTO Response (responseid,subjectid,iscomplete,startdate,examinerid,answer,isdelete,chooseditem) VALUES (");
		sqlText.append(recordId + ", ");
		sqlText.append(subjectId + ", ");
		sqlText.append(isComplete + ", '");
		sqlText.append(startDate + "', ");
		sqlText.append(memberId + ", '");
		sqlText.append(answer + "', ");
		sqlText.append(isDelete + ", '");
		sqlText.append(choosedItem + "')");

		Integer insertCount = jdbcDAO.update(sqlText.toString());

		return insertCount;
	}

	@Override
	public Integer updateResponse(Response response) {
		Integer recordId = response.getRecordId();

		// 受測者編號
		Subject subject = response.getSubject();
		Integer subjectId = subject.getSubjectId();
		Boolean isComplete = response.getIsComplete();
		String startDate = response.getStartDate();

		// 施測者編號
		Member member = response.getExaminer();
		Integer memberId = member.getMemberId();
		String answer = response.getAnswer();
		Boolean isDelete = response.getIsDelete();
		String choosedItem = response.getChoosedItem();

		String tscore = response.gettScore();
		String upper95 = response.getUpper95();
		String lower95 = response.getLower95();
		String reliability = response.getReliability();
		String ability = response.getAbility();
		String examType = response.getExamType();
		String percentileLevel = response.getPercentileLevel();
		
		StringBuilder sqlText = new StringBuilder();
		sqlText.append("UPDATE RESPONSE SET ");
		sqlText.append("subjectid = '");
		sqlText.append(subjectId);
		sqlText.append("', iscomplete = ");
		sqlText.append(isComplete);
		sqlText.append(", startdate = '");
		sqlText.append(startDate);
		sqlText.append("', examinerid = ");
		sqlText.append(memberId);
		sqlText.append(", answer = '");
		sqlText.append(answer);
		sqlText.append("', isdelete = ");
		sqlText.append(isDelete);
		sqlText.append(", chooseditem = '");
		sqlText.append(choosedItem);
		
		sqlText.append("', tscore = '");
		sqlText.append(tscore);
		sqlText.append("', upper95 = '");
		sqlText.append(upper95);
		sqlText.append("', lower95 = '");
		sqlText.append(lower95);
		sqlText.append("', reliability = '");
		sqlText.append(reliability);
		sqlText.append("', ability = '");
		sqlText.append(ability);
		sqlText.append("', examtype = '");
		sqlText.append(examType);
		sqlText.append("', percentilelevel = '");
		sqlText.append(percentileLevel);
		
		sqlText.append("' WHERE responseid = ");
		sqlText.append(recordId);

		Integer updateCount = jdbcDAO.update(sqlText.toString());

		return updateCount;
	}

	@Override
	public Integer deleteResponse(Response response) {
		Integer recordId = response.getRecordId();

		StringBuilder sqlText = new StringBuilder();
		sqlText.append("UPDATE RESPONSE SET ");

		sqlText.append("isdelete = ");
		sqlText.append(true);
		sqlText.append(" WHERE responseid = ");
		sqlText.append(recordId);

		Integer updateCount = jdbcDAO.update(sqlText.toString());

		return updateCount;
	}

	@Override
	public Response getResponseByResponseId(Response response) {
		Integer responseId = response.getRecordId();
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

		sqlText.append("T1.tscore,");
		sqlText.append("T1.upper95,");
		sqlText.append("T1.lower95,");
		sqlText.append("T1.reliability,");
		sqlText.append("T1.ability,");
		sqlText.append("T1.examtype,");
		sqlText.append("T1.percentilelevel,");

		sqlText.append("T2.subjectid,");
		sqlText.append("T2.medicalnumber,");
		sqlText.append("T2.subjectname");
		sqlText.append(" FROM response T1");
		sqlText.append(" LEFT JOIN subject T2");
		sqlText.append(" ON T1.subjectid = T2.subjectid");
		sqlText.append(" WHERE");
		sqlText.append(" T1.responseid = " + responseId);
		sqlText.append(" ORDER BY T1.responseid");

		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqlText.toString());

		Map<String, Object> map = resultList.get(0);

		response.setRecordId((Integer) map.get("responseid"));
		response.setIsComplete((Boolean) map.get("iscomplete"));
		response.setStartDate((String) map.get("startdate"));
		response.setAnswer((String) map.get("answer"));
		response.setIsDelete((Boolean) map.get("isdelete"));
		response.setChoosedItem((String) map.get("chooseditem"));

		response.settScore((String) map.get("tscore"));
		response.setUpper95((String) map.get("upper95"));
		response.setLower95((String) map.get("lower95"));
		response.setReliability((String) map.get("reliability"));
		response.setExamType((String) map.get("examtype"));
		response.setPercentileLevel((String) map.get("percentilelevel"));
		response.setAbility((String) map.get("ability"));

		Subject subject = new Subject();
		subject.setSubjectId((Integer) map.get("subjectid"));
		subject.setMedicalNumber((Integer) map.get("medicalnumber"));
		subject.setSubjectName((String) map.get("subjectname"));
		response.setSubject(subject);

		Member member = new Member();
		member.setMemberId((Integer) map.get("examinerid"));
		response.setExaminer(member);

		return response;
	}

	@Override
	public List<Map<String, Object>> getPercentileLevel() {
		String sqlText = "SELECT * FROM PERCENTILELEVEL";
		List<Map<String, Object>> percentileList = jdbcDAO.queryForList(sqlText);
		
		return percentileList;
	}

}
