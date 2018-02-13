package tw.com.billy.fastcat.core.business.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.billy.fastcat.SpringTest;
import tw.com.billy.fastcat.core.business.service.ISubjectService;

public class SubjectServiceImplTest extends SpringTest {

	@Autowired
	ISubjectService subjectService;

	@Test
	public void testGetAllSubject() {
		System.out.println(subjectService.getAllSubject());
	}

	@Test
	public void testAddSubject() {
	}

	@Test
	public void testUpdateSubject() {
	}

	@Test
	public void testDeleteSubject() {
	}

}
