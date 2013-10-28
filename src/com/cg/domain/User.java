package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private Date userAddeddate;
	private String userAddress;
	private String userEmail;
	private String userFirstname;
	private String userLastname;
	private String userPassword;
	private Date userRemoveddate;
	private String userSecurityanswer;
	private String userSecurityquestion;
	private String userStatus;
	private Set<Feedback> feedbacks;
	private Set<Order> orders;
	private Set<Returnstatus> returnstatuses;
	private Set<Reward> rewards;
	private Set<Shipping> shippings;
	private Set<Wishlist> wishlists;

	public User() {
	}


	@Id
	@Column(name="user_id", unique=true, nullable=false, length=255)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="user_addeddate")
	public Date getUserAddeddate() {
		return this.userAddeddate;
	}

	public void setUserAddeddate(Date userAddeddate) {
		this.userAddeddate = userAddeddate;
	}


	@Column(name="user_address", length=255)
	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	@Column(name="user_email", length=255)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	@Column(name="user_firstname", length=255)
	public String getUserFirstname() {
		return this.userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}


	@Column(name="user_lastname", length=255)
	public String getUserLastname() {
		return this.userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}


	@Column(name="user_password", length=255)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="user_removeddate")
	public Date getUserRemoveddate() {
		return this.userRemoveddate;
	}

	public void setUserRemoveddate(Date userRemoveddate) {
		this.userRemoveddate = userRemoveddate;
	}


	@Column(name="user_securityanswer", length=255)
	public String getUserSecurityanswer() {
		return this.userSecurityanswer;
	}

	public void setUserSecurityanswer(String userSecurityanswer) {
		this.userSecurityanswer = userSecurityanswer;
	}


	@Column(name="user_securityquestion", length=255)
	public String getUserSecurityquestion() {
		return this.userSecurityquestion;
	}

	public void setUserSecurityquestion(String userSecurityquestion) {
		this.userSecurityquestion = userSecurityquestion;
	}


	@Column(name="user_status", length=255)
	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}


	//bi-directional many-to-one association to Feedback
	@OneToMany(mappedBy="user")
	public Set<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Feedback addFeedback(Feedback feedback) {
		getFeedbacks().add(feedback);
		feedback.setUser(this);

		return feedback;
	}

	public Feedback removeFeedback(Feedback feedback) {
		getFeedbacks().remove(feedback);
		feedback.setUser(null);

		return feedback;
	}


	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}


	//bi-directional many-to-one association to Returnstatus
	@OneToMany(mappedBy="user")
	public Set<Returnstatus> getReturnstatuses() {
		return this.returnstatuses;
	}

	public void setReturnstatuses(Set<Returnstatus> returnstatuses) {
		this.returnstatuses = returnstatuses;
	}

	public Returnstatus addReturnstatus(Returnstatus returnstatus) {
		getReturnstatuses().add(returnstatus);
		returnstatus.setUser(this);

		return returnstatus;
	}

	public Returnstatus removeReturnstatus(Returnstatus returnstatus) {
		getReturnstatuses().remove(returnstatus);
		returnstatus.setUser(null);

		return returnstatus;
	}


	//bi-directional many-to-one association to Reward
	@OneToMany(mappedBy="user")
	public Set<Reward> getRewards() {
		return this.rewards;
	}

	public void setRewards(Set<Reward> rewards) {
		this.rewards = rewards;
	}

	public Reward addReward(Reward reward) {
		getRewards().add(reward);
		reward.setUser(this);

		return reward;
	}

	public Reward removeReward(Reward reward) {
		getRewards().remove(reward);
		reward.setUser(null);

		return reward;
	}


	//bi-directional many-to-one association to Shipping
	@OneToMany(mappedBy="user")
	public Set<Shipping> getShippings() {
		return this.shippings;
	}

	public void setShippings(Set<Shipping> shippings) {
		this.shippings = shippings;
	}

	public Shipping addShipping(Shipping shipping) {
		getShippings().add(shipping);
		shipping.setUser(this);

		return shipping;
	}

	public Shipping removeShipping(Shipping shipping) {
		getShippings().remove(shipping);
		shipping.setUser(null);

		return shipping;
	}


	//bi-directional many-to-one association to Wishlist
	@OneToMany(mappedBy="user")
	public Set<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(Set<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist addWishlist(Wishlist wishlist) {
		getWishlists().add(wishlist);
		wishlist.setUser(this);

		return wishlist;
	}

	public Wishlist removeWishlist(Wishlist wishlist) {
		getWishlists().remove(wishlist);
		wishlist.setUser(null);

		return wishlist;
	}

}