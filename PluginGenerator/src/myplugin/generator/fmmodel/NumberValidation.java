package myplugin.generator.fmmodel;

public class NumberValidation extends Validation {

	private Integer minValue;
	private Integer maxValue;

	public NumberValidation(Boolean unique, Boolean notNull, Integer minValue, Integer maxValue) {
		super(unique, notNull);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
}
