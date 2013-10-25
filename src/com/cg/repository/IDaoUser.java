package com.cg.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.User;

@Repository
public interface IDaoUser extends JpaRepository<User,String>{
	@Transactional
	@Query("select u.userPassword from User u where u.userEmail=?1")
	public String getUserPassword(String email);
	
	@Transactional
	@Query("select u.userStatus from User u where u.userEmail=?1")
	public String getUserStatus(String email);
	
	@Transactional
	@Query("select u.userId from User u where u.userEmail=?1")
	public String getUserId(String email);
	
	@Transactional
	@Query("select u from User u where u.userEmail=?1")
	public User findById(String email_id);
	
	@Transactional
	@Query("select u.userFirstname from User u where u.userEmail=?1")
	public String getUserName(String email);
}
