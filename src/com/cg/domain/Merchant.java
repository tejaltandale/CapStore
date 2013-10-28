package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the merchant database table.
 * 
 */
@Entity
@Table(name="merchant")
public class Merchant implements Serializable {
	private static final long serialVersionUID = 1L;
	private String merchantId;
	private Date merchantAddeddate;
	private String merchantAddress;
	private String merchantEmail;
	private String merchantName;
	private String merchantPassword;
	private Date merchantRemoveddate;
	private String merchantSecurityanswer;
	private String merchantSecurityquestion;
	private String merchantStatus;
	private String merchantType;
	private Set<Offer> offers;
	private Set<Product> products;
	private Set<Transaction> transactions;

	public Merchant() {
	}


	@Id
	@Column(name="merchant_id", unique=true, nullable=false, length=255)
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="merchant_addeddate")
	public Date getMerchantAddeddate() {
		return this.merchantAddeddate;
	}

	public void setMerchantAddeddate(Date merchantAddeddate) {
		this.merchantAddeddate = merchantAddeddate;
	}


	@Column(name="merchant_address", length=500)
	public String getMerchantAddress() {
		return this.merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}


	@Column(name="merchant_email", length=255)
	public String getMerchantEmail() {
		return this.merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}


	@Column(name="merchant_name", length=255)
	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	@Column(name="merchant_password", length=255)
	public String getMerchantPassword() {
		return this.merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="merchant_removeddate")
	public Date getMerchantRemoveddate() {
		return this.merchantRemoveddate;
	}

	public void setMerchantRemoveddate(Date merchantRemoveddate) {
		this.merchantRemoveddate = merchantRemoveddate;
	}


	@Column(name="merchant_securityanswer", length=255)
	public String getMerchantSecurityanswer() {
		return this.merchantSecurityanswer;
	}

	public void setMerchantSecurityanswer(String merchantSecurityanswer) {
		this.merchantSecurityanswer = merchantSecurityanswer;
	}


	@Column(name="merchant_securityquestion", length=255)
	public String getMerchantSecurityquestion() {
		return this.merchantSecurityquestion;
	}

	public void setMerchantSecurityquestion(String merchantSecurityquestion) {
		this.merchantSecurityquestion = merchantSecurityquestion;
	}


	@Column(name="merchant_status", length=255)
	public String getMerchantStatus() {
		return this.merchantStatus;
	}

	public void setMerchantStatus(String merchantStatus) {
		this.merchantStatus = merchantStatus;
	}


	@Column(name="merchant_type", length=255)
	public String getMerchantType() {
		return this.merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}


	//bi-directional many-to-one association to Offer
	@OneToMany(mappedBy="merchant")
	public Set<Offer> getOffers() {
		return this.offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Offer addOffer(Offer offer) {
		getOffers().add(offer);
		offer.setMerchant(this);

		return offer;
	}

	public Offer removeOffer(Offer offer) {
		getOffers().remove(offer);
		offer.setMerchant(null);

		return offer;
	}


	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="merchant")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setMerchant(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setMerchant(null);

		return product;
	}


	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="merchant")
	public Set<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setMerchant(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setMerchant(null);

		return transaction;
	}

}