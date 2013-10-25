package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the viewcount database table.
 * 
 */
@Entity
@Table(name="viewcount")
public class Viewcount implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productId;
	private long count;
	private Category category;
	private Product product;

	public Viewcount() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id", unique=true, nullable=false, length=255)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}


	public long getCount() {
		return this.count;
	}

	public void setCount(long count) {
		this.count = count;
	}


	//bi-directional many-to-one association to Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	//bi-directional one-to-one association to Product
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id", nullable=false, insertable=false, updatable=false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}