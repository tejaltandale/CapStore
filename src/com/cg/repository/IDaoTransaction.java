package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Transaction;
@Repository
public interface IDaoTransaction extends JpaRepository<Transaction,Long>{
	@Transactional
	@Query("select t from Transaction t")
	List<Transaction> getAllTransactions();
}
