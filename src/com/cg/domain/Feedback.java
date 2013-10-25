package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the feedback database table.
 * 
 */
@Entity
@Table(name="feedback")
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;
	private long feedbackId;
	private String feedbackContent;
	private Date feedbackDate;
	private String id;
	private double rating;
	private String type;
	private User user;

	public Feedback() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feedback_id", unique=true, nullable=false)
	public long getFeedbackId() {
		return this.feedbackId;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}


	@Column(name="feedback_content", length=500)
	public String getFeedbackContent() {
		return this.feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="feedback_date")
	public Date getFeedbackDate() {
		return this.feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}


	@Column(length=255)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public double getRating() {
		return this.rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}


	@Column(length=255)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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