package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.domain.Returnstatus;
@Repository
public interface IDaoReturnstatus extends JpaRepository<Returnstatus,Long>{

}
