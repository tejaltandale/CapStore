package com.cg.domain;

public class ProductRatings implements Comparable<ProductRatings>{
	@Override
	public String toString() {
		return "ProductRatings [productId=" + productId + ", rating=" + rating
				+ ", productName=" + productName + ", category=" + category
				+ "]";
	}
	public String productId;
	public double rating;
	public String productName;
	public String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public int compareTo(ProductRatings aThat) {
		 final int BEFORE = -1;
		    final int EQUAL = 0;
		    final int AFTER = 1;

		    //this optimization is usually worthwhile, and can
		    //always be added
		    if (this == aThat) return EQUAL;

		    //primitive numbers follow this form
		    if (this.rating < aThat.rating) return AFTER;
		    if (this.rating > aThat.rating) return BEFORE;
		    return EQUAL;
	}
	
}
