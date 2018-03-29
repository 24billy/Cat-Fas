package tw.com.billy.fastcat.core.business.service;

import java.util.List;
import java.util.Map;

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
	 * 取得所有已完成的作答反應
	 * 
	 * @return
	 */
	List<Response> getAllCompletedResponse();
	
	/**
	 * 根據主試者編號取得所有已完成的作答反應
	 * @param subject
	 * @return
	 */
	List<Response> getAllCompletedResponseByExaminerId(Subject subject);

	/**
	 * 根據受試者編號取得所有已完成的作答反應
	 * @param subject
	 * @return
	 */
	List<Response> getAllCompletedResponseBySubjectId(Subject subject);
	
	/**
	 * 根據受試者編號取得作答反應
	 * 
	 * @param subject
	 * @return
	 */
	List<Response> getResponseBySubjectId(Subject subject);

	/**
	 * 根據作答反應代碼取得作答反應
	 * 
	 * @param response
	 * @return
	 */
	Response getResponseByResponseId(Response response);

	/**
	 * 新增作答反應
	 * 
	 * @param response
	 * @return
	 */
	Integer addResponse(Response response);

	/**
	 * 更新作答反應
	 * 
	 * @param response
	 * @return
	 */
	Integer updateResponse(Response response);

	/**
	 * 刪除作答反應
	 * 
	 * @param response
	 * @return
	 */
	Integer deleteResponse(Response response);

	/**
	 * 取得百分等級資料
	 * 
	 * @return
	 */
	List<Map<String, Object>> getPercentileLevel();

}
