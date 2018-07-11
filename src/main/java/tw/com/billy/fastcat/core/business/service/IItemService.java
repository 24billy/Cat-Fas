package tw.com.billy.fastcat.core.business.service;

import java.util.List;

import tw.com.billy.fastcat.core.db.model.Item;

/**
 * 「試題﹞服務介面
 * 
 * @author Billy
 *
 */
public interface IItemService {

	/**
	 * 根據題目代碼取得題目資訊
	 * 
	 * @param itemId
	 * @return
	 */
	Item getItemById(Integer itemId);

	/**
	 * 取得所有題目資訊
	 * 
	 * @return
	 */
	List<Item> getAllItem();
	
	/**
	 * 清空所有題目
	 * 
	 * @return
	 */
	Integer deleteAllItem();
	
	/**
	 * 加入所有題目
	 * 
	 * @return
	 */
	Integer insertAllItem();

}