package tw.com.billy.fastcat.core.business.service;

import java.util.List;

import tw.com.billy.fastcat.core.db.model.Response;
import tw.com.billy.fastcat.core.db.model.Subject;

public interface IResponseService {

	/**
	 * 取得所有作答反應
	 * 
	 * @return
	 */
	List<Response> getAllResponse();

	/**
	 * 根據受試者編號取得作答反應
	 * 
	 * @param subject
	 * @return
	 */
	List<Response> getResponseBySubjectId(Subject subject);

	/**
	 * 新增作答反應
	 * 
	 * @param response
	 * @return
	 */
	Response addResponse(Response response);

	/**
	 * 更新作答反應
	 * 
	 * @param response
	 * @return
	 */
	Response updateResponse(Response response);

	/**
	 * 刪除作答反應
	 * 
	 * @param response
	 * @return
	 */
	Integer deleteResponse(Response response);

}
