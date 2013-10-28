package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wishlist database table.
 * 
 */
@Entity
@Table(name="wishlist")
public class Wishlist implements Serializable {
	private static final long serialVersionUID = 1L;
	private long wishId;
	private Date wishCreationdate;
	private Date wishEnddate;
	private Product product;
	private User user;

	public Wishlist() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="wish_id", unique=true, nullable=false)
	public long getWishId() {
		return this.wishId;
	}

	public void setWishId(long wishId) {
		this.wishId = wishId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="wish_creationdate")
	public Date getWishCreationdate() {
		return this.wishCreationdate;
	}

	public void setWishCreationdate(Date wishCreationdate) {
		this.wishCreationdate = wishCreationdate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="wish_enddate")
	public Date getWishEnddate() {
		return this.wishEnddate;
	}

	public void setWishEnddate(Date wishEnddate) {
		this.wishEnddate = wishEnddate;
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


	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}