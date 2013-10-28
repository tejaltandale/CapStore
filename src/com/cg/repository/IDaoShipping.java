package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.domain.Shipping;
@Repository
public interface IDaoShipping extends JpaRepository<Shipping,Integer>{

}
