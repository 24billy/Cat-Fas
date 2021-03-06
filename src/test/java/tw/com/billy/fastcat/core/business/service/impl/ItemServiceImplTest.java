package tw.com.billy.fastcat.core.business.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.billy.fastcat.SpringTest;
import tw.com.billy.fastcat.core.business.service.IItemService;
import tw.com.billy.fastcat.core.db.model.Item;
import tw.com.billy.fastcat.core.util.JsonUtil;
import tw.com.billy.fastcat.core.util.ResourceFileUtil;

public class ItemServiceImplTest extends SpringTest {

	@Autowired
	IItemService itemService;

	@Test
	public void testGetItemById() {
		Integer itemId = 1;
		Item item = itemService.getItemById(itemId);

		System.out.println(item);
	}

	@Test
	public void testGetAllItem() {
		List<Item> itemList = itemService.getAllItem();
		System.out.println(itemList);

		System.out.println(JsonUtil.toJson(itemList));
	}

	@Test
	public void testDeleteAllItem() {
		Integer deleteCount = itemService.deleteAllItem();

		System.out.println(deleteCount);
	}
	
	@Test
	public void testInsertAllItem() {
		Integer insertCount = itemService.insertAllItem();
		System.out.println(insertCount);
	}

}
