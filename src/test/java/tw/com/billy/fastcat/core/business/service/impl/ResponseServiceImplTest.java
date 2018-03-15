package tw.com.billy.fastcat.core.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.billy.fastcat.SpringTest;
import tw.com.billy.fastcat.core.business.service.IResponseService;
import tw.com.billy.fastcat.core.db.model.Member;
import tw.com.billy.fastcat.core.db.model.Response;
import tw.com.billy.fastcat.core.db.model.Subject;
import tw.com.billy.fastcat.core.util.JsonUtil;

public class ResponseServiceImplTest extends SpringTest {

	@Autowired
	IResponseService responseService;

	@Test
	public void testGetAllResponse() {
		List<Response> result = responseService.getAllResponse();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	@Test
	public void testGetResponseBySubjectId() {
		Subject subject = new Subject();
		subject.setSubjectId(1);

		System.out.println(responseService.getResponseBySubjectId(subject));
	}

	@Test
	public void testAddResponse() {
		List<Response> responseList = responseService.getAllResponse();
		Integer recordId = responseList.get(responseList.size() - 1).getRecordId() + 1;
		Subject subject = new Subject();
		subject.setSubjectId(1);

		Member member = new Member();
		member.setMemberId(1);

		Response response = new Response();
		response.setRecordId(recordId);
		response.setAnswer("");
		response.setChoosedItem("");
		response.setExaminer(member);
		response.setSubject(subject);
		response.setIsComplete(false);
		response.setIsDelete(false);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String dateStr = format.format(today);
		response.setStartDate(dateStr);

		responseService.addResponse(response);
	}

	@Test
	public void testUpdateResponse() {
		Integer recordId = 2;
		Subject subject = new Subject();
		subject.setSubjectId(1);

		Member member = new Member();
		member.setMemberId(1);

		Response response = new Response();
		response.setRecordId(recordId);
		response.setAnswer("123");
		response.setChoosedItem("32313");
		response.setExaminer(member);
		response.setSubject(subject);
		response.setIsComplete(false);
		response.setIsDelete(false);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String dateStr = format.format(today);
		response.setStartDate(dateStr);

		responseService.updateResponse(response);
	}

	@Test
	public void testDeleteResponse() {
		Response response = new Response();
		response.setRecordId(2);

		responseService.deleteResponse(response);
	}

	@Test
	public void testGetResponseById() {
		Response response = new Response();
		response.setRecordId(1);

		response = responseService.getResponseByResponseId(response);

		System.out.println(response);

		ArrayList<Double> ability = JsonUtil.fromJson(response.getLower95(), ArrayList.class);

		System.out.println(ability.get(0));
	}

}
