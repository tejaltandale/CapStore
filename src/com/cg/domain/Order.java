package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private long orderId;
	private double cartValue;
	private Date orderDate;
	private String orderStatus;
	private User user;
	private Set<Returnstatus> returnstatuses;
	private Set<Shipping> shippings;
	private Set<Transaction> transactions;

	public Order() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id", unique=true, nullable=false)
	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


	@Column(name="cart_value")
	public double getCartValue() {
		return this.cartValue;
	}

	public void setCartValue(double cartValue) {
		this.cartValue = cartValue;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	@Column(name="order_status", length=255)
	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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


	//bi-directional many-to-one association to Returnstatus
	@OneToMany(mappedBy="order")
	public Set<Returnstatus> getReturnstatuses() {
		return this.returnstatuses;
	}

	public void setReturnstatuses(Set<Returnstatus> returnstatuses) {
		this.returnstatuses = returnstatuses;
	}

	public Returnstatus addReturnstatus(Returnstatus returnstatus) {
		getReturnstatuses().add(returnstatus);
		returnstatus.setOrder(this);

		return returnstatus;
	}

	public Returnstatus removeReturnstatus(Returnstatus returnstatus) {
		getReturnstatuses().remove(returnstatus);
		returnstatus.setOrder(null);

		return returnstatus;
	}


	//bi-directional many-to-one association to Shipping
	@OneToMany(mappedBy="order")
	public Set<Shipping> getShippings() {
		return this.shippings;
	}

	public void setShippings(Set<Shipping> shippings) {
		this.shippings = shippings;
	}

	public Shipping addShipping(Shipping shipping) {
		getShippings().add(shipping);
		shipping.setOrder(this);

		return shipping;
	}

	public Shipping removeShipping(Shipping shipping) {
		getShippings().remove(shipping);
		shipping.setOrder(null);

		return shipping;
	}


	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="order")
	public Set<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setOrder(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setOrder(null);

		return transaction;
	}

}