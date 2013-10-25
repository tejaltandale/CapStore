package com.cg.domain;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the media database table.
 * 
 */
@Entity
@Table(name="media")
public class Media implements Serializable {
	private static final long serialVersionUID = 1L;
	private long mediaId;
	private String mediaPath;
	private String mediaType;
	private Set<Advertisement> advertisements;
	private Product product;

	public Media() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_id", unique=true, nullable=false)
	public long getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}


	@Column(name="media_path", length=255)
	public String getMediaPath() {
		return this.mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}


	@Column(name="media_type", length=255)
	public String getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}


	//bi-directional many-to-one association to Advertisement
	@OneToMany(mappedBy="media")
	public Set<Advertisement> getAdvertisements() {
		return this.advertisements;
	}

	public void setAdvertisements(Set<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	public Advertisement addAdvertisement(Advertisement advertisement) {
		getAdvertisements().add(advertisement);
		advertisement.setMedia(this);

		return advertisement;
	}

	public Advertisement removeAdvertisement(Advertisement advertisement) {
		getAdvertisements().remove(advertisement);
		advertisement.setMedia(null);

		return advertisement;
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