package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.domain.Feedback;
@Repository
public interface IDaoFeedback extends JpaRepository<Feedback,Long>{

}
