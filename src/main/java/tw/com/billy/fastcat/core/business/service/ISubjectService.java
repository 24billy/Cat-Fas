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
	 * @return List<Subject>
	 */
	List<Subject> getAllSubject();

	/**
	 * 根據主試者取得受試者資料
	 * 
	 * @param subject
	 * @return List<Subject>
	 */
	List<Subject> getSubjectByExaminerId(Subject subject);

	/**
	 * 新增受試者資料
	 * 
	 * @param subject
	 * @return Integer
	 */
	Integer addSubject(Subject subject);

	/**
	 * 編輯受試者資料
	 * 
	 * @param subject
	 * @return Integer
	 */
	Integer updateSubject(Subject subject);

	/**
	 * 刪除受試者資料
	 * 
	 * @param subject
	 * @return
	 */
	Integer deleteSubject(Subject subject);

}