package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private String categoryId;
	private String categoryName;
	private Set<Product> products;
	private Set<Viewcount> viewcounts;

	public Category() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id", unique=true, nullable=false, length=255)
	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	@Column(name="category_name", length=255)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="category")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}


	//bi-directional many-to-one association to Viewcount
	@OneToMany(mappedBy="category")
	public Set<Viewcount> getViewcounts() {
		return this.viewcounts;
	}

	public void setViewcounts(Set<Viewcount> viewcounts) {
		this.viewcounts = viewcounts;
	}

	public Viewcount addViewcount(Viewcount viewcount) {
		getViewcounts().add(viewcount);
		viewcount.setCategory(this);

		return viewcount;
	}

	public Viewcount removeViewcount(Viewcount viewcount) {
		getViewcounts().remove(viewcount);
		viewcount.setCategory(null);

		return viewcount;
	}

}