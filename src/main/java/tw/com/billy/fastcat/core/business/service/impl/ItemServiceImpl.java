package tw.com.billy.fastcat.core.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tw.com.billy.fastcat.core.business.service.IItemService;
import tw.com.billy.fastcat.core.db.jdbc.JdbcDAO;
import tw.com.billy.fastcat.core.db.model.Item;
import tw.com.billy.fastcat.core.util.ResourceFileUtil;

/**
 * [試題] 服務實作
 * 
 * @author Billy
 *
 */
@Service("ItemDao")
public class ItemServiceImpl implements IItemService {

	@Autowired
	@Qualifier("jdbcDAO")
	private JdbcDAO jdbcDAO;

	@Override
	public Item getItemById(Integer itemId) {
		String sqltext = "SELECT * FROM ITEM WHERE id = $P{itemId}";
		sqltext = sqltext.replace("$P{itemId}", itemId.toString());
		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqltext);

		Integer id = (Integer) resultList.get(0).get("id");
		Integer dimension = (Integer) resultList.get(0).get("dimension");
		Integer categoryNumber = (Integer) resultList.get(0).get("categorynumber");
		Double delta = (Double) resultList.get(0).get("delta");
		Double step1 = (Double) resultList.get(0).get("step1");
		Double step2 = (Double) resultList.get(0).get("step2");
		Double step3 = (Double) resultList.get(0).get("step3");
		String engTitle = (String) resultList.get(0).get("engtitle");
		String chtTitle = (String) resultList.get(0).get("chttitle");
		String introduction = (String) resultList.get(0).get("introduction");
		String startPose = (String) resultList.get(0).get("startpose");
		String option1 = (String) resultList.get(0).get("option1");
		String option2 = (String) resultList.get(0).get("option2");
		String option3 = (String) resultList.get(0).get("option3");
		String option4 = (String) resultList.get(0).get("option4");

		Item item = new Item();
		item.setItemId(id);

		item.setCategoryNumber(categoryNumber);
		item.setDimension(dimension);
		item.setDelta(delta);
		item.setStep1(step1);
		item.setStep2(step2);
		item.setStep3(step3);

		item.setEngTitle(engTitle);
		item.setChtTitle(chtTitle);
		item.setIntroduction(introduction);
		item.setStartPose(startPose);

		item.setOption1(option1);
		item.setOption2(option2);
		item.setOption3(option3);
		item.setOption4(option4);

		return item;
	}

	@Override
	public List<Item> getAllItem() {
		String sqltext = "SELECT * FROM ITEM";
		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqltext);

		List<Item> itemList = new ArrayList<Item>();

		for (int i = 0; i < resultList.size(); i++) {
			Integer id = (Integer) resultList.get(i).get("id");
			Integer dimension = (Integer) resultList.get(i).get("dimension");
			Integer categoryNumber = (Integer) resultList.get(i).get("categorynumber");
			Double delta = (Double) resultList.get(i).get("delta");
			Double step1 = (Double) resultList.get(i).get("step1");
			Double step2 = (Double) resultList.get(i).get("step2");
			Double step3 = (Double) resultList.get(i).get("step3");
			String engTitle = (String) resultList.get(i).get("engtitle");
			String chtTitle = (String) resultList.get(i).get("chttitle");
			String introduction = (String) resultList.get(i).get("introduction");
			String startPose = (String) resultList.get(i).get("startpose");
			String option1 = (String) resultList.get(i).get("option1");
			String option2 = (String) resultList.get(i).get("option2");
			String option3 = (String) resultList.get(i).get("option3");
			String option4 = (String) resultList.get(i).get("option4");

			Item item = new Item();
			item.setItemId(id);

			item.setCategoryNumber(categoryNumber);
			item.setDimension(dimension);
			item.setDelta(delta);
			item.setStep1(step1);
			item.setStep2(step2);
			item.setStep3(step3);

			item.setEngTitle(engTitle);
			item.setChtTitle(chtTitle);
			item.setIntroduction(introduction);
			item.setStartPose(startPose);

			item.setOption1(option1);
			item.setOption2(option2);
			item.setOption3(option3);
			item.setOption4(option4);

			itemList.add(item);
		}

		return itemList;
	}

	@Override
	public Integer deleteAllItem() {
		String sqltext = "DELETE FROM ITEM";
		Integer deleteCount = jdbcDAO.update(sqltext);

		return deleteCount;
	}

	@Override
	public Integer insertAllItem() {
		String sqlText = ResourceFileUtil.SQL.getResource("item", "insertItem");

		Integer deleteCount = jdbcDAO.update(sqlText.toString());

		return deleteCount;
	}

}
