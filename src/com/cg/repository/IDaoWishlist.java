package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Wishlist;
@Repository
public interface IDaoWishlist extends JpaRepository<Wishlist,String> {
	@Transactional
	@Query("select w from Wishlist w")
	List<Wishlist>getAllWishlist();
}
