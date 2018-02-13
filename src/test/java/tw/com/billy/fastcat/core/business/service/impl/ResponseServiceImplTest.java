package tw.com.billy.fastcat.core.business.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.billy.fastcat.SpringTest;
import tw.com.billy.fastcat.core.business.service.IResponseService;
import tw.com.billy.fastcat.core.db.model.Subject;

public class ResponseServiceImplTest extends SpringTest {

	@Autowired
	IResponseService responseService;

	@Test
	public void testGetAllResponse() {
		System.out.println(responseService.getAllResponse());
	}

	@Test
	public void testGetResponseBySubjectId() {
		Subject subject = new Subject();
		subject.setSubjectId(1);

		System.out.println(responseService.getResponseBySubjectId(subject));
	}

	@Test
	public void testAddResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteResponse() {
		fail("Not yet implemented");
	}

}
