package com.cg.domain;

public class MostBoughtProducts implements Comparable<MostBoughtProducts>{
	String productname;
	int numberofsales;
	
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getNumberofsales() {
		return numberofsales;
	}
	public void setNumberofsales(int numberofsales) {
		this.numberofsales = numberofsales;
	}
	@Override
	public int compareTo(MostBoughtProducts aThat) {
//		o.numberofsales
	    final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;

	    //this optimization is usually worthwhile, and can
	    //always be added
	    if (this == aThat) return EQUAL;

	    //primitive numbers follow this form
	    if (this.numberofsales < aThat.numberofsales) return AFTER;
	    if (this.numberofsales > aThat.numberofsales) return BEFORE;

//	    //booleans follow this form
//	    if (!this.fIsNewAccount && aThat.fIsNewAccount) return BEFORE;
//	    if (this.fIsNewAccount && !aThat.fIsNewAccount) return AFTER;
//
//	    //objects, including type-safe enums, follow this form
//	    //note that null objects will throw an exception here
//	    int comparison = this.fAccountType.compareTo(aThat.fAccountType);
//	    if (comparison != EQUAL) return comparison;
//
//	    comparison = this.fLastName.compareTo(aThat.fLastName);
//	    if (comparison != EQUAL) return comparison;
//
//	    comparison = this.fFirstName.compareTo(aThat.fFirstName);
//	    if (comparison != EQUAL) return comparison;
//
//	    if (this.fBalance < aThat.fBalance) return BEFORE;
//	    if (this.fBalance > aThat.fBalance) return AFTER;
//
//	    //all comparisons have yielded equality
//	    //verify that compareTo is consistent with equals (optional)
//	    assert this.equals(aThat) : "compareTo inconsistent with equals.";

	    return EQUAL;
	}

}
