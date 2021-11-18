package com.ensate.app.studentapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensate.app.studentapp.entity.CommentaireEntity;

@Repository
public interface CommentaireRepository extends JpaRepository<CommentaireEntity, Long> {

	
	
}
