package tw.com.billy.fastcat.core.db.model;

public class Item {

	private Integer itemId;
	private Integer dimension;
	private Integer categoryNumber;
	private Double delta;
	private Double step1;
	private Double step2;
	private Double step3;

	public Item() {
		super();
	}

	public Item(Integer itemId, Integer dimension, Integer categoryNumber, Double delta) {
		super();
		this.itemId = itemId;
		this.dimension = dimension;
		this.categoryNumber = categoryNumber;
		this.delta = delta;
	}

	public Item(Integer itemId, Integer dimension, Integer categoryNumber, Double delta, Double step1, Double step2) {
		super();
		this.itemId = itemId;
		this.dimension = dimension;
		this.categoryNumber = categoryNumber;
		this.delta = delta;
		this.step1 = step1;
		this.step2 = step2;
	}

	public Item(Integer itemId, Integer dimension, Integer categoryNumber, Double delta, Double step1, Double step2,
			Double step3) {
		super();
		this.itemId = itemId;
		this.dimension = dimension;
		this.categoryNumber = categoryNumber;
		this.delta = delta;
		this.step1 = step1;
		this.step2 = step2;
		this.step3 = step3;
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

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", dimension=" + dimension + ", categoryNumber=" + categoryNumber + ", delta="
				+ delta + ", step1=" + step1 + ", step2=" + step2 + ", step3=" + step3 + "]";
	}

}
