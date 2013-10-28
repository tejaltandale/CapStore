package com.cg.domain;

public class ProductViews implements Comparable<ProductViews>{
	String productId;
	double views;
	String productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getViews() {
		return views;
	}
	public void setViews(double views) {
		this.views = views;
	}
	@Override
	public int compareTo(ProductViews aThat) {
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;

	    //this optimization is usually worthwhile, and can
	    //always be added
	    if (this == aThat) return EQUAL;

	    //primitive numbers follow this form
	    if (this.views < aThat.views) return AFTER;
	    if (this.views > aThat.views) return BEFORE;
	    return EQUAL;
	}
	

}
