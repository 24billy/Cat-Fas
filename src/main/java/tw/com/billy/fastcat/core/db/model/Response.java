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
	String tScore;
	String se;
	String upper95;
	String lower95;
	String reliability;
	String examType;
	String percentileLevel;
	String ability;
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

	public String gettScore() {
		return tScore;
	}

	public void settScore(String tScore) {
		this.tScore = tScore;
	}

	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	public String getUpper95() {
		return upper95;
	}

	public void setUpper95(String upper95) {
		this.upper95 = upper95;
	}

	public String getLower95() {
		return lower95;
	}

	public void setLower95(String lower95) {
		this.lower95 = lower95;
	}

	public String getReliability() {
		return reliability;
	}

	public void setReliability(String reliability) {
		this.reliability = reliability;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getPercentileLevel() {
		return percentileLevel;
	}

	public void setPercentileLevel(String percentileLevel) {
		this.percentileLevel = percentileLevel;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	@Override
	public String toString() {
		return "Response [recordId=" + recordId + ", startDate=" + startDate + ", answer=" + answer + ", choosedItem="
				+ choosedItem + ", tScore=" + tScore + ", se=" + se + ", upper95=" + upper95 + ", lower95=" + lower95
				+ ", reliability=" + reliability + ", examType=" + examType + ", percentileLevel=" + percentileLevel
				+ ", ability=" + ability + ", isComplete=" + isComplete + ", isDelete=" + isDelete + ", examiner="
				+ examiner + ", subject=" + subject + "]";
	}

}