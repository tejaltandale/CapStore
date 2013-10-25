package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.domain.Media;
@Repository
public interface IDaoMedia extends JpaRepository<Media,Long>{

}
