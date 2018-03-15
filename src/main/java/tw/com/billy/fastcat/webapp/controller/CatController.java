package tw.com.billy.fastcat.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.billy.fastcat.core.business.service.IItemService;
import tw.com.billy.fastcat.core.business.service.IResponseService;
import tw.com.billy.fastcat.core.common.Constant;
import tw.com.billy.fastcat.core.db.model.Item;
import tw.com.billy.fastcat.core.db.model.Response;
import tw.com.billy.fastcat.core.util.CatUtil;
import tw.com.billy.fastcat.core.util.JsonUtil;
import tw.com.billy.fastcat.core.vo.AbilityVO;
import tw.com.billy.fastcat.core.vo.ItemVO;

/**
 * [電腦化適性測驗]導頁控制器
 * 
 * @author Billy
 * 
 */
@Controller
@RequestMapping("/cat")
public class CatController {

	@Autowired
	IItemService itemService;
	@Autowired
	IResponseService responseService;

	@RequestMapping("/chooseItem")
	public String chooseItem(HttpServletRequest request, @RequestParam(value = "answer", required = true) Double answer,
			Model model) {
		@SuppressWarnings("unchecked")
		List<ItemVO> itemList = (List<ItemVO>) request.getSession().getAttribute("itemPool");
		@SuppressWarnings("unchecked")
		List<ItemVO> selectedItemList = (List<ItemVO>) request.getSession().getAttribute("selectedItemList");
		String examType = (String) request.getSession().getAttribute("examType");
		AbilityVO prevAbilityVO = (AbilityVO) request.getSession().getAttribute("abilityVO");
		ItemVO selectedItem = (ItemVO) request.getSession().getAttribute("selectedItem");

		// 紀錄結果
		Response record = (Response) request.getSession().getAttribute("record");

		// 取得答案、清除可選題庫
		selectedItem.setResponse(answer);
		selectedItemList.add(selectedItem);
		itemList.remove(selectedItem);

		// 能力估計
		AbilityVO abilityVO = CatUtil.loopAbilityEstimate(selectedItemList, prevAbilityVO, Constant.scoringMatrixList,
				Constant.designMatrix, Constant.covInverseArray, Constant.muArray);

		// Stopping rule：第4題開始計算LRI
		// 高信度hr：4向度LRI < 0.01；高效率hv：4向度LRI < 0.02。
		boolean isStopTest = false;

		if (selectedItemList.size() > 4) {
			isStopTest = true;

			for (int i = 0; i < 4; i++) {
				Double lri = Math.abs(abilityVO.getReliability().get(i) - prevAbilityVO.getReliability().get(i));

				if ("hr".equals(examType) && lri > 0.01) {
					isStopTest = false;
				} else if ("hv".equals(examType) && lri > 0.02) {
					isStopTest = false;
				}
			}
		}

		// 測驗結束
		if (isStopTest) {
			System.out.println("isStopTest : " + isStopTest);
			record.setAbility(JsonUtil.toJson(abilityVO.getOriginalAbility()));
			record.setIsComplete(true);
			record.setUpper95(JsonUtil.toJson(abilityVO.getCriUpper()));
			record.setLower95(JsonUtil.toJson(abilityVO.getCriLower()));
			record.setReliability(JsonUtil.toJson(abilityVO.getReliability()));
			record.settScore(JsonUtil.toJson(abilityVO.gettScore()));
			record.setSe(JsonUtil.toJson(abilityVO.getSe()));
			record.setPercentileLevel(JsonUtil.toJson(abilityVO.getPercentileLevel()));

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String dateStr = format.format(today);
			record.setStartDate(dateStr);
			
			// 紀錄所選題目與作答結果
			List<Integer> selectItem = CatUtil.getChooseItem(selectedItemList);
			List<Integer> answerList = CatUtil.getAnswer(selectedItemList);
			record.setChoosedItem(JsonUtil.toJson(selectItem));
			record.setAnswer(JsonUtil.toJson(answerList));
			
			// 計算百分等級
			List<Map<String, Object>> percentileList = responseService.getPercentileLevel();
			CatUtil.getPercentileLevel(abilityVO, percentileList);
			String percentileLeveStr = JsonUtil.toJson(abilityVO.getPercentileLevel());
			record.setPercentileLevel(percentileLeveStr);

			responseService.updateResponse(record);
			
			model.addAttribute("record", JsonUtil.toJson(record));
			model.addAttribute("abilityVO", JsonUtil.toJson(abilityVO));

			return "pages/test/examComplete";
		}

		// 選題
		ItemVO itemVO = CatUtil.itemSelection(abilityVO.getOriginalAbility(), abilityVO.getInfomationMatrix(), itemList,
				Constant.scoringMatrixList, Constant.designMatrix, Constant.covInverseArray, Constant.muArray);
		Integer itemId = itemVO.getNum();
		Item item = itemService.getItemById(itemId);
		String itemStr = JsonUtil.toJson(item);
		String abilityStr = JsonUtil.toJson(abilityVO);

		model.addAttribute("item", itemStr);
		model.addAttribute("itemIndex", selectedItemList.size() + 1);
		model.addAttribute("ability", abilityStr);

		request.getSession().setAttribute("itemPool", itemList);
		request.getSession().setAttribute("selectedItem", itemVO);
		request.getSession().setAttribute("selectedItemList", selectedItemList);
		request.getSession().setAttribute("abilityVO", abilityVO);
		request.getSession().setAttribute("record", record);

		return "pages/test/exam";
	}

	@RequestMapping("/finishExam")
	public String finishExam(@RequestParam(value = "organizationId", required = true) Integer organizationId) {

		return "common/result";
	}

	@RequestMapping("/beginHighReliabilityExam")
	public String beginHighReliabilityExam(HttpServletRequest request,
			@RequestParam(value = "recordId", required = false) Integer recordId, Model model) {
		System.out.println("recordId : " + recordId);

		// 初始化作答紀錄
		Response record = new Response();
		record.setRecordId(recordId);
		record = responseService.getResponseByResponseId(record);
		record.setExamType("hr");

		// Init Test
		List<ItemVO> itemList = CatUtil.initItem();
		List<ItemVO> selectedItemList = new ArrayList<ItemVO>();
		ItemVO itemVO = CatUtil.itemSelection(Constant.initAbility, Constant.priorInfo, itemList,
				Constant.scoringMatrixList, Constant.designMatrix, Constant.covInverseArray, Constant.muArray);

		// 取得題目內容
		Integer itemId = itemVO.getNum();
		Item item = itemService.getItemById(itemId);
		String itemStr = JsonUtil.toJson(item);

		// 初始能力
		AbilityVO abilityVO = new AbilityVO();
		abilityVO.setAbility(Constant.initAbility);

		model.addAttribute("item", itemStr);
		model.addAttribute("itemIndex", 1);
		model.addAttribute("examType", "hr");

		request.getSession().setAttribute("itemPool", itemList);
		request.getSession().setAttribute("selectedItem", itemVO);
		request.getSession().setAttribute("selectedItemList", selectedItemList);
		request.getSession().setAttribute("examType", "hr");
		request.getSession().setAttribute("abilityVO", abilityVO);
		request.getSession().setAttribute("record", record);

		return "pages/test/exam";
	}

	@RequestMapping("/beginHighValidityExam")
	public String beginHighValidityExam(HttpServletRequest request,
			@RequestParam(value = "recordId", required = false) Integer recordId, Model model) {
		System.out.println("recordId : " + recordId);

		// 初始化作答紀錄
		Response record = new Response();
		record.setRecordId(recordId);
		record = responseService.getResponseByResponseId(record);
		record.setExamType("hv");

		// Init Test
		List<ItemVO> itemList = CatUtil.initItem();
		List<ItemVO> selectedItemList = new ArrayList<ItemVO>();
		ItemVO itemVO = CatUtil.itemSelection(Constant.initAbility, Constant.priorInfo, itemList,
				Constant.scoringMatrixList, Constant.designMatrix, Constant.covInverseArray, Constant.muArray);

		// 取得題目內容
		Integer itemId = itemVO.getNum();

		Item item = itemService.getItemById(itemId);
		String itemStr = JsonUtil.toJson(item);

		// 初始能力
		AbilityVO abilityVO = new AbilityVO();
		abilityVO.setAbility(Constant.initAbility);

		model.addAttribute("item", itemStr);
		model.addAttribute("itemIndex", 1);
		model.addAttribute("examType", "hv");

		request.getSession().setAttribute("itemPool", itemList);
		request.getSession().setAttribute("selectedItem", itemVO);
		request.getSession().setAttribute("selectedItemList", selectedItemList);
		request.getSession().setAttribute("examType", "hv");
		request.getSession().setAttribute("abilityVO", abilityVO);
		request.getSession().setAttribute("record", record);

		return "pages/test/exam";
	}

}