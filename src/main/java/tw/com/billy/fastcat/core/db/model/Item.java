package tw.com.billy.fastcat.core.db.model;

public class Item {

	private Integer itemId;
	private Integer dimension;
	private Integer categoryNumber;
	private Double delta;
	private Double step1;
	private Double step2;
	private Double step3;
	private String engTitle;
	private String chtTitle;
	private String introduction;
	private String startPose;
	private String option1;
	private String option2;
	private String option3;
	private String option4;

	public Item() {
		super();
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}

	public Integer getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(Integer categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public Double getDelta() {
		return delta;
	}

	public void setDelta(Double delta) {
		this.delta = delta;
	}

	public Double getStep1() {
		return step1;
	}

	public void setStep1(Double step1) {
		this.step1 = step1;
	}

	public Double getStep2() {
		return step2;
	}

	public void setStep2(Double step2) {
		this.step2 = step2;
	}

	public Double getStep3() {
		return step3;
	}

	public void setStep3(Double step3) {
		this.step3 = step3;
	}

	public String getEngTitle() {
		return engTitle;
	}

	public void setEngTitle(String engTitle) {
		this.engTitle = engTitle;
	}

	public String getChtTitle() {
		return chtTitle;
	}

	public void setChtTitle(String chtTitle) {
		this.chtTitle = chtTitle;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getStartPose() {
		return startPose;
	}

	public void setStartPose(String startPose) {
		this.startPose = startPose;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", dimension=" + dimension + ", categoryNumber=" + categoryNumber + ", delta="
				+ delta + ", step1=" + step1 + ", step2=" + step2 + ", step3=" + step3 + ", engTitle=" + engTitle
				+ ", chtTitle=" + chtTitle + ", introduction=" + introduction + ", startPose=" + startPose
				+ ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4
				+ "]";
	}

}
