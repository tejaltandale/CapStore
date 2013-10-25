package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the returneditems database table.
 * 
 */
@Entity
@Table(name="returneditems")
public class Returneditem implements Serializable {
	private static final long serialVersionUID = 1L;
	private long returnId;
	private long orderId;
	private double productCost;
	private String productId;
	private String productName;
	private long productQuantity;
	private String returnStatus;
	private long returnstatusId;
	private long transactionId;
	private String userId;

	public Returneditem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="return_id", unique=true, nullable=false)
	public long getReturnId() {
		return this.returnId;
	}

	public void setReturnId(long returnId) {
		this.returnId = returnId;
	}


	@Column(name="order_id")
	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


	@Column(name="product_cost")
	public double getProductCost() {
		return this.productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}


	@Column(name="product_id", length=255)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}


	@Column(name="product_name", length=255)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	@Column(name="product_quantity")
	public long getProductQuantity() {
		return this.productQuantity;
	}

	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}


	@Column(name="return_status", length=255)
	public String getReturnStatus() {
		return this.returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}


	@Column(name="returnstatus_id")
	public long getReturnstatusId() {
		return this.returnstatusId;
	}

	public void setReturnstatusId(long returnstatusId) {
		this.returnstatusId = returnstatusId;
	}


	@Column(name="transaction_id")
	public long getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}


	@Column(name="user_id", length=255)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}