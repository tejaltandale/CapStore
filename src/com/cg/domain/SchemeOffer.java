package com.cg.domain;

public class SchemeOffer {
private Scheme scheme;
private Offer offer;
public Scheme getScheme() {
	return scheme;
}
public void setScheme(Scheme scheme) {
	this.scheme = scheme;
}
public Offer getOffer() {
	return offer;
}
public void setOffer(Offer offer) {
	this.offer = offer;
}
@Override
public String toString() {
	return "SchemeOffer [scheme=" + scheme + ", offer=" + offer + "]";
}

}
