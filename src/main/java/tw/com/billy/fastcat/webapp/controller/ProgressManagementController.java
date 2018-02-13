package tw.com.billy.fastcat.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.billy.fastcat.core.business.service.IResponseService;
import tw.com.billy.fastcat.core.db.model.Response;
import tw.com.billy.fastcat.core.db.model.Subject;
import tw.com.billy.fastcat.core.util.JsonUtil;

/**
 * [測驗歷程管理]導頁控制器
 * 
 * @author Billy
 * 
 */
@Controller
@RequestMapping("/progressManagement")
public class ProgressManagementController {

	@Autowired
	IResponseService responseService;

	@RequestMapping("/showProgressManagement")
	public String showProgressManagementMain(@RequestParam(value = "subjectId", required = false) Integer subjectId,
			Model model) {
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		List<Response> responseList = responseService.getResponseBySubjectId(subject);
		String jsonStr = JsonUtil.toJson(responseList);
		model.addAttribute("responseList", jsonStr);

		return "pages/progressManagement";
	}

	@RequestMapping("/beginExam")
	public String beginExam(@RequestParam(value = "recordId", required = false) String recordId, Model model) {
		System.out.println("recordId : " + recordId);
		return "pages/test/exam";
	}
}
