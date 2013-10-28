package com.cg.domain;

import java.util.Date;

public class AdvertisementMedia {
	private long advertisementId;
	private long mediaId;
	private String mediaPath;
	private String mediaType;
	private Date advertisementStartdate;
	private Date advertisementEnddate;
	public AdvertisementMedia(long advertisementId,long mediaId,String mediaPath,String mediaType,Date advertisementStartdate,Date advertisementEnddate){
		this.advertisementId=advertisementId;
		this.mediaId=mediaId;
		this.mediaPath=mediaPath;
		this.mediaType=mediaType;
		this.advertisementStartdate=advertisementStartdate;
		this.advertisementEnddate=advertisementEnddate;
	}
	public Date getAdvertisementEnddate() {
		return advertisementEnddate;
	}
	public void setAdvertisementEnddate(Date advertisementEnddate) {
		this.advertisementEnddate = advertisementEnddate;
	}
	public long getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(long advertisementId) {
		this.advertisementId = advertisementId;
	}
	public Date getAdvertisementStartdate() {
		return advertisementStartdate;
	}
	public void setAdvertisementStartdate(Date advertisementStartdate) {
		this.advertisementStartdate = advertisementStartdate;
	}
	public long getMediaId() {
		return mediaId;
	}
	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaPath() {
		return mediaPath;
	}
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

}
