package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Category;
@Repository
public interface IDaoCategory  extends JpaRepository<Category,String>{
	@Transactional
	@Query("select c from Category c")
	List<Category> getAllCategories();

}
