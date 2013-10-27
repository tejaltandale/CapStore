package com.cg.domain;

public class WishlistCount implements Comparable<WishlistCount>{
	String productId;
	int count;
	String productName;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public int compareTo(WishlistCount aThat) {
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;

	    //this optimization is usually worthwhile, and can
	    //always be added
	    if (this == aThat) return EQUAL;

	    //primitive numbers follow this form
	    if (this.count < aThat.count) return AFTER;
	    if (this.count > aThat.count) return BEFORE;
	    return EQUAL;
	}
	
}
