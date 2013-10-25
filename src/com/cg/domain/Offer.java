package com.cg.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the offer database table.
 * 
 */
@Entity
@Table(name="offer")
public class Offer implements Serializable {
	private static final long serialVersionUID = 1L;
	private long schemeId;
	private String schemeDescription;
	private String schemeName;
	private String type;
	private String value;
	private Merchant merchant;
	private Set<Scheme> schemes;

	public Offer() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="scheme_id", unique=true, nullable=false)
	public long getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(long schemeId) {
		this.schemeId = schemeId;
	}


	@Column(name="scheme_description", length=500)
	public String getSchemeDescription() {
		return this.schemeDescription;
	}

	public void setSchemeDescription(String schemeDescription) {
		this.schemeDescription = schemeDescription;
	}


	@Column(name="scheme_name", length=255)
	public String getSchemeName() {
		return this.schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}


	@Column(length=255)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Column(length=255)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
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


	//bi-directional many-to-one association to Scheme
	@OneToMany(mappedBy="offer")
	public Set<Scheme> getSchemes() {
		return this.schemes;
	}

	public void setSchemes(Set<Scheme> schemes) {
		this.schemes = schemes;
	}

	public Scheme addScheme(Scheme scheme) {
		getSchemes().add(scheme);
		scheme.setOffer(this);

		return scheme;
	}

	public Scheme removeScheme(Scheme scheme) {
		getSchemes().remove(scheme);
		scheme.setOffer(null);

		return scheme;
	}

}