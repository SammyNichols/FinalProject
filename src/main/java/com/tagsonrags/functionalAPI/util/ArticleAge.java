package com.tagsonrags.functionalAPI.util;

public enum ArticleAge {
	
	SIXMONTHS(.50),
	TWELVEMONTHS(.75),
	EIGHTEENMONTHS(.90);
	
	private double newPrice;

	ArticleAge(double newPrice) {
		this.newPrice = newPrice;
	}
	
	public double getNewPrice() {
		return newPrice;
	}
}
