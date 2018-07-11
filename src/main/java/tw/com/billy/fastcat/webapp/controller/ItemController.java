package tw.com.billy.fastcat.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.billy.fastcat.core.business.service.IItemService;
import tw.com.billy.fastcat.core.util.JsonUtil;

/**
 * [電腦化適性測驗]導頁控制器
 * 
 * @author Billy
 * 
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	IItemService itemService;

	@RequestMapping("/reloadItem")
	public String reloadItem(HttpServletRequest request, Model model) {
		// 判斷登入
		Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
		Integer roleId = (Integer) request.getSession().getAttribute("roleId");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		String returnCode = "00";
		String returnMessage = "FAILED";

		if (isLogin && roleId != null && roleId.equals(1)) {
			Integer deleteCount = itemService.deleteAllItem();
			System.out.println("deleteCount : " + deleteCount);

			if (deleteCount > 0) {
				Integer insertCount = itemService.insertAllItem();
				System.out.println("insertCount : " + insertCount);

				if (insertCount > 0) {
					returnCode = "01";
					returnMessage = "SUCCESS";
				} else {
					returnCode = "02";
					returnMessage = "NO RESULT";
				}
			}
		} else {
			returnCode = "00";
			returnMessage = "NO AUTHORITY";
		}

		resultMap.put("returnCode", returnCode);
		resultMap.put("returnMessage", returnMessage);

		String result = JsonUtil.toJson(resultMap);
		model.addAttribute("result", result);

		return "common/result";
	}

}