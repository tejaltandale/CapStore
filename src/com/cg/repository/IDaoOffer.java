package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Merchant;
import com.cg.domain.Offer;
import com.cg.domain.Product;
import com.cg.domain.Scheme;
@Repository
public interface IDaoOffer extends JpaRepository<Offer,Long> {

	@Transactional
	@Query("select p from Product p where p.merchant=?1")
	public List<Product> getProductId(Merchant mer);
	
	@Transactional
	@Query("select m from Merchant m where m.merchantId=?1")
	public Merchant getByMerchantId(String id);
	
	@Transactional
	@Query("select o from Offer o where o.merchant=?1")
	public List<Offer> getBySchemeId(Merchant mer);
	
	@Transactional
	@Query("select s from Scheme s where s.product=?1")
	public List<Scheme> getByentryId(Product p);
	
	@Transactional
	@Query("select s from Scheme s where s.product=?1 and s.offer=?2")
	public Scheme getByentryId(Product p,Offer o);
	
	@Transactional
	@Query("select o from Offer o where o.schemeId=?1")
	public Offer getBySchemeId(long id);

	@Transactional
	@Query("select o from Offer o where o.type=?1")
	public List<Offer> getBySchemeId(String name);
	
	
}
