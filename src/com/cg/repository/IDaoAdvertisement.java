package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.domain.Advertisement;
@Repository
public interface IDaoAdvertisement extends JpaRepository<Advertisement, Integer>{

}
