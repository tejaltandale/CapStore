package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the returnstatus database table.
 * 
 */
@Entity
@Table(name="returnstatus")
public class Returnstatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private long returnstatusId;
	private String returnStatus;
	private Product product;
	private Order order;
	private User user;

	public Returnstatus() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="returnstatus_id", unique=true, nullable=false)
	public long getReturnstatusId() {
		return this.returnstatusId;
	}

	public void setReturnstatusId(long returnstatusId) {
		this.returnstatusId = returnstatusId;
	}


	@Column(name="return_status", length=255)
	public String getReturnStatus() {
		return this.returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
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


	//bi-directional many-to-one association to Order
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
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