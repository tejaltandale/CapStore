package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.domain.Viewcount;
@Repository
public interface IDaoViewcount extends JpaRepository<Viewcount,String>{

}
