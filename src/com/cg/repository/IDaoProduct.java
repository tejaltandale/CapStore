package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Product;
@Repository
public interface IDaoProduct extends JpaRepository<Product,String>{

	@Transactional
	@Query("select p from Product p where p.merchant.merchantId=?1")
	public List<Product> getById(String id);
}
