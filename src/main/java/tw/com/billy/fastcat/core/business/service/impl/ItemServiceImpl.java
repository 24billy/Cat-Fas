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

		Item item = new Item(id, dimension, categoryNumber, delta, step1, step2, step3);

		return item;
	}

	@Override
	public List<Item> getAllItem() {
		String sqltext = "SELECT * FROM ITEM";
		List<Map<String, Object>> resultList = jdbcDAO.queryForList(sqltext);

		List<Item> itemList = new ArrayList<Item>();
		for (Map<String, Object> map : resultList) {
			Integer id = (Integer) map.get("id");
			Integer dimension = (Integer) resultList.get(0).get("dimension");
			Integer categoryNumber = (Integer) map.get("categorynumber");
			Double delta = (Double) map.get("delta");
			Double step1 = (Double) map.get("step1");
			Double step2 = (Double) map.get("step2");
			Double step3 = (Double) map.get("step3");

			Item item = new Item(id, dimension, categoryNumber, delta, step1, step2, step3);
			itemList.add(item);
		}

		return itemList;
	}

}
