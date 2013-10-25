package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the shipping database table.
 * 
 */
@Entity
@Table(name="shipping")
public class Shipping implements Serializable {
	private static final long serialVersionUID = 1L;
	private long shippingId;
	private Date deliveryDate;
	private double orderValue;
	private String shippingAddress;
	private Date shippingDate;
	private String shippingStatus;
	private Order order;
	private User user;

	public Shipping() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shipping_id", unique=true, nullable=false)
	public long getShippingId() {
		return this.shippingId;
	}

	public void setShippingId(long shippingId) {
		this.shippingId = shippingId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="delivery_date")
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	@Column(name="order_value")
	public double getOrderValue() {
		return this.orderValue;
	}

	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}


	@Column(name="shipping_address", length=500)
	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="shipping_date")
	public Date getShippingDate() {
		return this.shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}


	@Column(name="shipping_status", length=255)
	public String getShippingStatus() {
		return this.shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
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