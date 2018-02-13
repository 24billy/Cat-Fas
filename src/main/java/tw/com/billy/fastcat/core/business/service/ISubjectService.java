package tw.com.billy.fastcat.core.business.service;

import java.util.List;

import tw.com.billy.fastcat.core.db.model.Subject;

/**
 * [受試者]服務介面
 * 
 * @author Billy
 *
 */
public interface ISubjectService {

	/**
	 * 取得所有受試者資料
	 * 
	 * @return
	 */
	List<Subject> getAllSubject();

	/**
	 * 新增受試者資料
	 * 
	 * @param subject
	 * @return
	 */
	Subject addSubject(Subject subject);

	/**
	 * 編輯受試者資料
	 * 
	 * @param subject
	 * @return
	 */
	Subject updateSubject(Subject subject);

	/**
	 * 刪除受試者資料
	 * 
	 * @param subject
	 * @return
	 */
	Integer deleteSubject(Subject subject);

}