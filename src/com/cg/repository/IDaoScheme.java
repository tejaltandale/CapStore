package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.domain.Scheme;
@Repository
public interface IDaoScheme extends JpaRepository<Scheme,Long>{

}
