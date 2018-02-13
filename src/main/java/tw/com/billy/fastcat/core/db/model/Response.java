package tw.com.billy.fastcat.core.db.model;

/**
 * [作答反應]表格物件
 * 
 * @author Billy
 *
 */
public class Response {

	Integer recordId;
	String startDate;
	String answer;
	String choosedItem;
	Boolean isComplete;
	Boolean isDelete;

	Member examiner;
	Subject subject;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getChoosedItem() {
		return choosedItem;
	}

	public void setChoosedItem(String choosedItem) {
		this.choosedItem = choosedItem;
	}

	public Member getExaminer() {
		return examiner;
	}

	public void setExaminer(Member examiner) {
		this.examiner = examiner;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Response [recordId=" + recordId + ", startDate=" + startDate + ", answer=" + answer + ", choosedItem="
				+ choosedItem + ", isComplete=" + isComplete + ", isDelete=" + isDelete + ", examiner=" + examiner
				+ ", subject=" + subject + "]";
	}

}