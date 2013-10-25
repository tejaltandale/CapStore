package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the scheme database table.
 * 
 */
@Entity
@Table(name="scheme")
public class Scheme implements Serializable {
	private static final long serialVersionUID = 1L;
	private long entryId;
	private Date endDate;
	private Date startDate;
	private Offer offer;
	private Product product;

	public Scheme() {
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


	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	//bi-directional many-to-one association to Offer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="scheme_id")
	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
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

}