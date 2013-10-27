package com.cg.domain;

public class MarketShare implements Comparable<MarketShare>{
	String merchantname;
	String merchantid;
	
	
	
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public String getMerchantname() {
		return merchantname;
	}
	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}
	int numberoftransactions;

	public int getNumberoftransactions() {
		return numberoftransactions;
	}
	public void setNumberoftransactions(int numberoftransactions) {
		this.numberoftransactions = numberoftransactions;
	}
	@Override
	public int compareTo(MarketShare aThat) {
		 final int BEFORE = -1;
		    final int EQUAL = 0;
		    final int AFTER = 1;

		    //this optimization is usually worthwhile, and can
		    //always be added
		    if (this == aThat) return EQUAL;

		    //primitive numbers follow this form
		    if (this.numberoftransactions < aThat.numberoftransactions) return AFTER;
		    if (this.numberoftransactions > aThat.numberoftransactions) return BEFORE;
		    return EQUAL;
	}

}
