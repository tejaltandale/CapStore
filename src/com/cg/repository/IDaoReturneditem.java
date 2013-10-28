package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.domain.Returneditem;
@Repository
public interface IDaoReturneditem extends JpaRepository<Returneditem,Long>{

}
