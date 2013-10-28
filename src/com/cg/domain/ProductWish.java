package com.cg.domain;

public class ProductWish {
	

	private String productBrand;
	private String productName;
	

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductbrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public ProductWish(String productBrand, String productName) {
		super();
		this.productBrand = productBrand;
		this.productName = productName;
	}
	

}
