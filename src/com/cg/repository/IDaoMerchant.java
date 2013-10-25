package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Merchant;
@Repository
public interface IDaoMerchant extends JpaRepository<Merchant,String>{
	@Transactional
	@Query("select m.merchantPassword from Merchant m where m.merchantEmail=?1")
	String getMerchantPassword(String merchantemail);
			
	@Transactional
	@Query("select m.merchantStatus from Merchant m where m.merchantEmail=?1")
	String getMerchantStatus(String merchantiemail);	

	@Transactional
	@Query("select m.merchantId from Merchant m where m.merchantEmail=?1")
	String getMerchantId(String merchantiemail);

	@Transactional
	@Query("select m from Merchant m where m.merchantEmail=?1")
	public Merchant findById(String email_id);
}
