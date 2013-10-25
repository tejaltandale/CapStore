package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private long entryId;
	private double productCost;
	private String productName;
	private long productQuantity;
	private Date transactionDate;
	private long transactionId;
	private Merchant merchant;
	private Order order;
	private Product product;

	public Transaction() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="entry_id", unique=true, nullable=false)
	public long getEntryId() {
		return this.entryId;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}


	@Column(name="product_cost")
	public double getProductCost() {
		return this.productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
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


	@Temporal(TemporalType.DATE)
	@Column(name="transaction_date")
	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	@Column(name="transaction_id")
	public long getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}


	//bi-directional many-to-one association to Merchant
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="merchant_id")
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}


	//bi-directional many-to-one association to Order
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}