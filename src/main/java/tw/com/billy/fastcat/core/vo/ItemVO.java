package tw.com.billy.fastcat.core.vo;

import java.io.Serializable;

public class ItemVO implements Serializable{

	private static final long serialVersionUID = -7350691865918400661L;
	
	// 題目編號
	private Integer num;
	// 題目的向度 (1-4)
	private Integer dimension;
	// 題目的計分點數量(2-4)
	private Integer categoryNumber;
	// 題目的難度
	private Double delta;
	// 題目的第一階難度
	private Double step1;
	// 題目的第二難度
	private Double step2;
	// 題目的第三難度
	private Double step3;
	// 題目的作答反應
	private Double response;

	/**
	 * 基本建構子
	 */
	public ItemVO() {

	}

	/**
	 * 二點計分建構子
	 * 
	 * @param num
	 * @param dimension
	 * @param categoryNumber
	 * @param delta
	 */
	public ItemVO(Integer num, Integer dimension, Integer categoryNumber, Double delta) {
		super();
		this.num = num;
		this.dimension = dimension;
		this.categoryNumber = categoryNumber;
		this.delta = delta;
	}

	/**
	 * 三點計分建構子
	 * 
	 * @param num
	 * @param dimension
	 * @param categoryNumber
	 * @param delta
	 * @param step1
	 * @param step2
	 */
	public ItemVO(Integer num, Integer dimension, Integer categoryNumber, Double delta, Double step1, Double step2) {
		super();
		this.num = num;
		this.dimension = dimension;
		this.categoryNumber = categoryNumber;
		this.delta = delta;
		this.step1 = step1;
		this.step2 = step2;
	}

	/**
	 * 四點計分建構子
	 * 
	 * @param num
	 * @param dimension
	 * @param categoryNumber
	 * @param delta
	 * @param step1
	 * @param step2
	 * @param step3
	 */
	public ItemVO(Integer num, Integer dimension, Integer categoryNumber, Double delta, Double step1, Double step2,
			Double step3) {
		super();
		this.num = num;
		this.dimension = dimension;
		this.categoryNumber = categoryNumber;
		this.delta = delta;
		this.step1 = step1;
		this.step2 = step2;
		this.step3 = step3;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	public Double getResponse() {
		return response;
	}

	public void setResponse(Double response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Item [num=" + num + ", dimension=" + dimension + ", categoryNumber=" + categoryNumber + ", delta="
				+ delta + ", step1=" + step1 + ", step2=" + step2 + ", step3=" + step3 + ", response=" + response + "]";
	}

}