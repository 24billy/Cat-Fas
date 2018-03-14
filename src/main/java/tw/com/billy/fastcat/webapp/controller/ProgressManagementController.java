package tw.com.billy.fastcat.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.billy.fastcat.core.business.service.IResponseService;
import tw.com.billy.fastcat.core.db.model.Member;
import tw.com.billy.fastcat.core.db.model.Response;
import tw.com.billy.fastcat.core.db.model.Subject;
import tw.com.billy.fastcat.core.util.JsonUtil;
import tw.com.billy.fastcat.core.vo.AbilityVO;

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

		Response response = new Response();
		response.setSubject(subject);

		List<Response> responseList = responseService.getResponseBySubjectId(subject);

		String jsonStr = JsonUtil.toJson(responseList);
		model.addAttribute("responseList", jsonStr);
		model.addAttribute("subjectId", subjectId);

		return "pages/progressManagement";
	}

	@RequestMapping("/addExam")
	public String addExam(HttpServletRequest request,
			@RequestParam(value = "subjectId", required = false) Integer subjectId) {
		List<Response> responseList = responseService.getAllResponse();
		Integer recordId = responseList.get(responseList.size() - 1).getRecordId() + 1;

		Subject subject = new Subject();
		subject.setSubjectId(subjectId);

		Integer memberId = (Integer) request.getSession().getAttribute("memberId");
		Member member = new Member();
		member.setMemberId(memberId);

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

		return "common/result";
	}

	@RequestMapping("/deleteExam")
	public String deleteExam(@RequestParam(value = "recordId", required = false) Integer recordId) {
		Response response = new Response();
		response.setRecordId(recordId);

		responseService.deleteResponse(response);

		return "common/result";
	}

	@RequestMapping("/getExamResult")
	public String getExamResult(@RequestParam(value = "recordId", required = false) Integer recordId, Model model) {
		Response response = new Response();
		response.setRecordId(recordId);

		response = responseService.getResponseByResponseId(response);

		AbilityVO abilityVO = new AbilityVO();
		List<Double> tScore = JsonUtil.fromJson(response.gettScore(), List.class);
		List<Double> criUpper = JsonUtil.fromJson(response.getUpper95(), List.class);
		List<Double> criLower = JsonUtil.fromJson(response.getLower95(), List.class);
		List<Double> reliability = JsonUtil.fromJson(response.getReliability(), List.class);
		List<Integer> percentileLevel = JsonUtil.fromJson(response.getPercentileLevel(), List.class);

		abilityVO.settScore(tScore);
		abilityVO.setCriLower(criLower);
		abilityVO.setCriUpper(criUpper);
		abilityVO.setReliability(reliability);
		abilityVO.setPercentileLevel(percentileLevel);

		String result = JsonUtil.toJson(abilityVO);

		model.addAttribute("result", result);

		return "common/result";
	}

}
