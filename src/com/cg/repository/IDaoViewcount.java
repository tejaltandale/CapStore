package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Viewcount;
@Repository
public interface IDaoViewcount extends JpaRepository<Viewcount,String>{
	@Transactional
	@Query("select v from Viewcount v ORDER BY v.count DESC")
	List<Viewcount>getAllViewcount();
	@Transactional
	@Modifying
	@Query("update Viewcount v set v.count=?2 where v.product.productId=?1 and v.category.categoryId=?3")
	public void updateRewardsByuserId(String productId, long i, String cat_gory);
	
	@Transactional
	@Query("select v.productId from Viewcount v where v.count>6")
	public List<String> getMaxPid();
	@Transactional
	@Query("select v from Viewcount v where v.category.categoryId= ?1")
	public List<Viewcount> getByProductId(String categoryid);
}
