package tw.com.billy.fastcat.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tw.com.billy.fastcat.core.business.service.IResponseService;
import tw.com.billy.fastcat.core.business.service.ISubjectService;
import tw.com.billy.fastcat.core.db.model.Response;
import tw.com.billy.fastcat.core.db.model.Subject;
import tw.com.billy.fastcat.core.util.ExcelUtil;
import tw.com.billy.fastcat.core.util.JsonUtil;
import tw.com.billy.fastcat.webapp.view.ExcelView;

/**
 * [資料管理]導頁控制器
 * 
 * @author Billy
 * 
 */
@Controller
@RequestMapping("/dataManagement")
public class DataManagementController {

	@Autowired
	ISubjectService subjectService;

	@Autowired
	IResponseService responseService;

	@RequestMapping("/showDataManagement")
	public String showDataManagementMain(HttpServletRequest request, Model model) {
		Integer roleId = (Integer) request.getSession().getAttribute("roleId");
		Integer memberId = (Integer) request.getSession().getAttribute("memberId");

		// 管理員直接取得所有資料
		if (roleId == 1) {
			List<Subject> subjectList = subjectService.getAllSubject();

			String jsonStr = JsonUtil.toJson(subjectList);

			model.addAttribute("subjectList", jsonStr);
		} else {
			Subject subject = new Subject();
			subject.setCreateMemberId(memberId);
			List<Subject> subjectList = subjectService.getSubjectByExaminerId(subject);

			String jsonStr = JsonUtil.toJson(subjectList);

			model.addAttribute("subjectList", jsonStr);
		}

		return "pages/dataManagement";
	}

	@RequestMapping("/addSubject")
	public String addSubject(HttpServletRequest request,
			@RequestParam(value = "medicalNumber", required = true) Integer medicalNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "gender", required = true) String gender,
			@RequestParam(value = "birthday", required = true) String birthday) {
		List<Subject> subjectList = subjectService.getAllSubject();
		Integer subjectId = 1;

		// 若非第一筆資料，則依目前最後一筆的代碼往後遞增
		if (subjectList != null && subjectList.size() > 0) {
			subjectId = subjectList.get(subjectList.size() - 1).getSubjectId() + 1;
		}

		// 取得建立者
		Integer memberId = (Integer) request.getSession().getAttribute("memberId");
		Integer organizationId = (Integer) request.getSession().getAttribute("organizationId");

		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		subject.setMedicalNumber(medicalNumber);
		subject.setSubjectName(name);
		subject.setGender(gender);
		subject.setBirthday(birthday);
		subject.setCreateMemberId(memberId);
		subject.setOrganizationId(organizationId);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String dateStr = format.format(today);
		subject.setCreatedate(dateStr);

		subjectService.addSubject(subject);

		return "common/result";
	}

	@RequestMapping("/updateSubject")
	public String updateSubject(HttpServletRequest request,
			@RequestParam(value = "subjectId", required = true) Integer subjectId,
			@RequestParam(value = "medicalNumber", required = true) Integer medicalNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "gender", required = true) String gender,
			@RequestParam(value = "birthday", required = true) String birthday) {
		// 取得建立者
		Integer memberId = (Integer) request.getSession().getAttribute("memberId");
		Integer organizationId = (Integer) request.getSession().getAttribute("organizationId");

		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		subject.setMedicalNumber(medicalNumber);
		subject.setSubjectName(name);
		subject.setGender(gender);
		subject.setBirthday(birthday);
		subject.setCreateMemberId(memberId);
		subject.setOrganizationId(organizationId);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String dateStr = format.format(today);
		subject.setCreatedate(dateStr);

		subjectService.updateSubject(subject);

		return "common/result";
	}

	@RequestMapping("/deleteSubject")
	public String deleteSubject(@RequestParam(value = "subjectId", required = true) Integer subjectId) {
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);

		subjectService.deleteSubject(subject);

		return "common/result";
	}

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel(HttpServletRequest request, HttpServletResponse response) {
		Integer roleId = (Integer) request.getSession().getAttribute("roleId");
		Integer memberId = (Integer) request.getSession().getAttribute("memberId");

		List<Response> data = null;

		// 管理員直接取得所有資料
		if (roleId == 1) {
			data = responseService.getAllCompletedResponse();
		} else {
			Subject subject = new Subject();
			subject.setCreateMemberId(memberId);
			data = responseService.getAllCompletedResponseByExaminerId(subject);
		}

		try {
			Workbook workbook = ExcelUtil.exportExcel("CAT-FAS_輸出", data);
			ExcelView viewExcel = new ExcelView();
			viewExcel.buildExcelDocument(null, workbook, request, response);

			return new ModelAndView(viewExcel);
		} catch (Exception e) {

		}

		return null;
	}

	@RequestMapping(value = "/downloadExcelBySubject", method = RequestMethod.GET)
	public ModelAndView downloadExcelBySubject(@RequestParam(value = "subjectId", required = true) Integer subjectId,
			HttpServletRequest request, HttpServletResponse response) {
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		List<Response> data = responseService.getAllCompletedResponseBySubjectId(subject);

		try {
			Workbook workbook = ExcelUtil.exportExcel("CAT-FAS_輸出_受試者編號(" + subjectId + ")_", data);
			ExcelView viewExcel = new ExcelView();
			viewExcel.buildExcelDocument(null, workbook, request, response);

			return new ModelAndView(viewExcel);
		} catch (Exception e) {
			System.out.println("匯出Excel異常" + e.getMessage());
		}

		return null;
	}

}