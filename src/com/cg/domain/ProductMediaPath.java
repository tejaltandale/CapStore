package com.cg.domain;

public class ProductMediaPath {
	public ProductMediaPath(Product product, String productmediapath) {
		super();
		this.product = product;
		this.productmediapath = productmediapath;
	}
	private Product product;
	private String productmediapath;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getProductmediapath() {
		return productmediapath;
	}
	public void setProductmediapath(String productmediapath) {
		this.productmediapath = productmediapath;
	} 

}
