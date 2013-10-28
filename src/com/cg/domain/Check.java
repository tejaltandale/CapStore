package com.cg.domain;


public class Check {
	
	private int entry_id;
	private String order_id;
	private String merchant_id;
    private String product_id;
    private String product_name;
    
	private int product_quantity;
	
	
	@Override
	public String toString() {
		return "Check [entry_id=" + entry_id + ", order_id=" + order_id
				+ ", merchant_id=" + merchant_id + ", product_id=" + product_id
				+ ", product_name=" + product_name + ", product_quantity="
				+ product_quantity + "]";
	}
	public Check(int entry_id, String order_id, String merchant_id,
			String product_id, String product_name, int product_quantity) {
		super();
		this.entry_id = entry_id;
		this.order_id = order_id;
		this.merchant_id = merchant_id;
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_quantity = product_quantity;
	}
	public int getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
}