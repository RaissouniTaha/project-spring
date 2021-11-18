package com.ensate.app.studentapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensate.app.studentapp.entity.AnnonceEntity;

@Repository
public interface AnnonceRepository extends JpaRepository<AnnonceEntity, Long> {

	
}
