package com.cg.domain;

public class MerchantRatings implements Comparable<MerchantRatings>{
	public String merchantid;
	public String merchantName;
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public double rating;
	
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public int compareTo(MerchantRatings aThat) {
		 final int BEFORE = -1;
		    final int EQUAL = 0;
		    final int AFTER = 1;

		    //this optimization is usually worthwhile, and can
		    //always be added
		    if (this == aThat) return EQUAL;

		    //primitive numbers follow this form
		    if (this.rating < aThat.rating) return AFTER;
		    if (this.rating > aThat.rating) return BEFORE;
		    return EQUAL;
	}

}
