package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productBrand;
	private double productCost;
	private Date productCreationdate;
	private String productName;
	private Date productSolddate;
	private long productStock;
	private String productTag;
	private Set<Media> medias;
	private Category category;
	private Merchant merchant;
	private Set<ProductDescription> productdescriptions;
	private Set<Returnstatus> returnstatuses;
	private Set<Scheme> schemes;
	private Set<Transaction> transactions;
	private Viewcount viewcount;
	private Set<Wishlist> wishlists;

	public Product() {
	}


	@Id
	@Column(name="product_id", unique=true, nullable=false, length=255)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}


	@Column(name="product_brand", length=255)
	public String getProductBrand() {
		return this.productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}


	@Column(name="product_cost")
	public double getProductCost() {
		return this.productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="product_creationdate")
	public Date getProductCreationdate() {
		return this.productCreationdate;
	}

	public void setProductCreationdate(Date productCreationdate) {
		this.productCreationdate = productCreationdate;
	}


	@Column(name="product_name", length=255)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="product_solddate")
	public Date getProductSolddate() {
		return this.productSolddate;
	}

	public void setProductSolddate(Date productSolddate) {
		this.productSolddate = productSolddate;
	}


	@Column(name="product_stock")
	public long getProductStock() {
		return this.productStock;
	}

	public void setProductStock(long productStock) {
		this.productStock = productStock;
	}


	@Column(name="product_tag", length=255)
	public String getProductTag() {
		return this.productTag;
	}

	public void setProductTag(String productTag) {
		this.productTag = productTag;
	}


	//bi-directional many-to-one association to Media
	@OneToMany(mappedBy="product")
	public Set<Media> getMedias() {
		return this.medias;
	}

	public void setMedias(Set<Media> medias) {
		this.medias = medias;
	}

	public Media addMedia(Media media) {
		getMedias().add(media);
		media.setProduct(this);

		return media;
	}

	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setProduct(null);

		return media;
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


	//bi-directional many-to-one association to Merchant
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="merchant_id")
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}


	//bi-directional many-to-one association to ProductDescription
	@OneToMany(mappedBy="product")
	public Set<ProductDescription> getProductDescriptions() {
		return this.productdescriptions;
	}

	public void setProductDescriptions(Set<ProductDescription> productdescriptions) {
		this.productdescriptions = productdescriptions;
	}

	public ProductDescription addProductDescription(ProductDescription productdescription) {
		getProductDescriptions().add(productdescription);
		productdescription.setProduct(this);

		return productdescription;
	}

	public ProductDescription removeProductDescription(ProductDescription productdescription) {
		getProductDescriptions().remove(productdescription);
		productdescription.setProduct(null);

		return productdescription;
	}


	//bi-directional many-to-one association to Returnstatus
	@OneToMany(mappedBy="product")
	public Set<Returnstatus> getReturnstatuses() {
		return this.returnstatuses;
	}

	public void setReturnstatuses(Set<Returnstatus> returnstatuses) {
		this.returnstatuses = returnstatuses;
	}

	public Returnstatus addReturnstatus(Returnstatus returnstatus) {
		getReturnstatuses().add(returnstatus);
		returnstatus.setProduct(this);

		return returnstatus;
	}

	public Returnstatus removeReturnstatus(Returnstatus returnstatus) {
		getReturnstatuses().remove(returnstatus);
		returnstatus.setProduct(null);

		return returnstatus;
	}


	//bi-directional many-to-one association to Scheme
	@OneToMany(mappedBy="product")
	public Set<Scheme> getSchemes() {
		return this.schemes;
	}

	public void setSchemes(Set<Scheme> schemes) {
		this.schemes = schemes;
	}

	public Scheme addScheme(Scheme scheme) {
		getSchemes().add(scheme);
		scheme.setProduct(this);

		return scheme;
	}

	public Scheme removeScheme(Scheme scheme) {
		getSchemes().remove(scheme);
		scheme.setProduct(null);

		return scheme;
	}


	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="product")
	public Set<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setProduct(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setProduct(null);

		return transaction;
	}


	//bi-directional one-to-one association to Viewcount
	@OneToOne(mappedBy="product", fetch=FetchType.LAZY)
	public Viewcount getViewcount() {
		return this.viewcount;
	}

	public void setViewcount(Viewcount viewcount) {
		this.viewcount = viewcount;
	}


	//bi-directional many-to-one association to Wishlist
	@OneToMany(mappedBy="product")
	public Set<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public Product(String productId, Category category, 
			Merchant merchant,String productBrand, double productCost,
			Date productCreationdate, String productName, Date productSolddate,String productTag,long productStock) {
		super();
		this.productId = productId;
		this.productBrand = productBrand;
		this.productCost = productCost;
		this.productCreationdate = productCreationdate;
		this.productName = productName;
		this.productSolddate = productSolddate;
		this.productStock = productStock;
		this.productTag = productTag;
		this.category = category;
		this.merchant = merchant;
	}


	public void setWishlists(Set<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist addWishlist(Wishlist wishlist) {
		getWishlists().add(wishlist);
		wishlist.setProduct(this);

		return wishlist;
	}

	public Wishlist removeWishlist(Wishlist wishlist) {
		getWishlists().remove(wishlist);
		wishlist.setProduct(null);

		return wishlist;
	}

}