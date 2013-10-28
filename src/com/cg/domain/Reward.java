package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewards database table.
 * 
 */
@Entity
@Table(name="rewards")
public class Reward implements Serializable {
	private static final long serialVersionUID = 1L;
	private long entryId;
	private long rewardPoints;
	private User user;

	public Reward() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="entry_id", unique=true, nullable=false)
	public long getEntryId() {
		return this.entryId;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}


	@Column(name="reward_points")
	public long getRewardPoints() {
		return this.rewardPoints;
	}

	public void setRewardPoints(long rewardPoints) {
		this.rewardPoints = rewardPoints;
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