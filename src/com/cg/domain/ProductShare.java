package com.cg.domain;

public class ProductShare implements Comparable<ProductShare>{
	public String productid;
	public int numberoftransactions;
	public String productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}

	public int getNumberoftransactions() {
		return numberoftransactions;
	}
	public void setNumberoftransactions(int numberoftransactions) {
		this.numberoftransactions = numberoftransactions;
	}
	@Override
	public int compareTo(ProductShare aThat) {
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;

	    //this optimization is usually worthwhile, and can
	    //always be added
	    if (this == aThat) return EQUAL;

	    //primitive numbers follow this form
	    if (this.numberoftransactions < aThat.numberoftransactions) return AFTER;
	    if (this.numberoftransactions > aThat.numberoftransactions) return BEFORE;
	    return EQUAL;
	}
	@Override
	public String toString() {
		return "ProductShare [productid=" + productid
				+ ", numberoftransactions=" + numberoftransactions
				+ ", productName=" + productName + "]";
	}
	
}
