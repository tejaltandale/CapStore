package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Order;
import com.cg.domain.Transaction;
@Repository
public interface IDaoOrder extends JpaRepository<Order,Long>{
	@Transactional
	@Query("select t from Transaction t where t.merchant.merchantId=?1")
	public List<Transaction> getBymerchantId(String id);
	@Transactional
	@Query("select o from Order o")
	List<Order> getAllOrders();
}