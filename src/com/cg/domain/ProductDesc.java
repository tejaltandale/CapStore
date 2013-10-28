package com.cg.domain;



public class ProductDesc {

	@Override
	public String toString() {
		return "ProductDesc [product=" + product + ", proddesc=" + proddesc
				+ "]";
	}
	private Product product;
	private ProductDescription proddesc;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductDescription getProddesc() {
		return proddesc;
	}
	public void setProddesc(ProductDescription pd) {
		this.proddesc = pd;
	}

	
	
}
