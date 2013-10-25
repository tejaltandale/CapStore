package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productdescription database table.
 * 
 */
@Entity
@Table(name="productdescription")
public class ProductDescription implements Serializable {
	private static final long serialVersionUID = 1L;
	private long descId;
	private String attributeName;
	private String attributeValue;
	private Product product;

	public ProductDescription(String attributeName, String attributeValue,
			Product product) {
		super();
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.product = product;
	}


	public ProductDescription() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="desc_id", unique=true, nullable=false)
	public long getDescId() {
		return this.descId;
	}

	public void setDescId(long descId) {
		this.descId = descId;
	}


	@Column(name="attribute_name", length=255)
	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}


	@Column(name="attribute_value", length=255)
	public String getAttributeValue() {
		return this.attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
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