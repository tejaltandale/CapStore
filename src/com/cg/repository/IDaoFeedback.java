package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Count;
import com.cg.domain.Feedback;
@Repository
public interface IDaoFeedback extends JpaRepository<Feedback,Long>{
	@Transactional
	@Query("select f from Feedback f ")
	List<Feedback> getAllFeedback();
	@Transactional	
	@Query("select avg(f.rating)  from Feedback f where f.id=?1 and f.rating>0")
	double getrate(String pid);
	@Transactional
	@Query("select new com.cg.domain.Count(count(f.rating)) from Feedback f where f.id=?1 and f.rating>0")
	Count countrate(String pid);

}
