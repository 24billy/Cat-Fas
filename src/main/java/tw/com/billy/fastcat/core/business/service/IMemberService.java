package tw.com.billy.fastcat.core.business.service;

import java.util.List;

import tw.com.billy.fastcat.core.db.model.Member;

/**
 * [成員] 服務介面
 * 
 * @author Billy
 *
 */
public interface IMemberService {

	/**
	 * 根據成員代碼取得成員資料
	 * 
	 * @param member
	 * @return
	 */
	Member getMemberByMemberId(Member member);

	/**
	 * 根據成員名稱取得成員資料
	 * 
	 * @param member
	 * @return
	 */
	Member getMemberByAccount(Member member);
	
	/**
	 * 取得所有成員資料
	 * 
	 * @return
	 */
	List<Member> getAllMember();

	/**
	 * 新增成員資料
	 * 
	 * @param member
	 * @return
	 */
	Member addMember(Member member);

	/**
	 * 更新成員資料
	 * 
	 * @param member
	 * @return
	 */
	Member updateMember(Member member);

	/**
	 * 刪除成員資料
	 * 
	 * @param member
	 * @return
	 */
	Integer deleteMember(Member member);

}