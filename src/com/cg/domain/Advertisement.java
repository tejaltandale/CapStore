package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the advertisement database table.
 * 
 */
@Entity
@Table(name="advertisement")
public class Advertisement implements Serializable {
	private static final long serialVersionUID = 1L;
	private long advertisementEntryid;
	private Date advertisementEnddate;
	private long advertisementId;
	private Date advertisementStartdate;
	private Media media;

	public Advertisement() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="advertisement_entryid", unique=true, nullable=false)
	public long getAdvertisementEntryid() {
		return this.advertisementEntryid;
	}

	public void setAdvertisementEntryid(long advertisementEntryid) {
		this.advertisementEntryid = advertisementEntryid;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="advertisement_enddate")
	public Date getAdvertisementEnddate() {
		return this.advertisementEnddate;
	}

	public void setAdvertisementEnddate(Date advertisementEnddate) {
		this.advertisementEnddate = advertisementEnddate;
	}


	@Column(name="advertisement_id")
	public long getAdvertisementId() {
		return this.advertisementId;
	}

	public void setAdvertisementId(long advertisementId) {
		this.advertisementId = advertisementId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="advertisement_startdate")
	public Date getAdvertisementStartdate() {
		return this.advertisementStartdate;
	}

	public void setAdvertisementStartdate(Date advertisementStartdate) {
		this.advertisementStartdate = advertisementStartdate;
	}


	//bi-directional many-to-one association to Media
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="media_id")
	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}