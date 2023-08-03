package com.maadhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maadhar.entity.AadharEntity;

@Repository
public interface AadharRepository extends JpaRepository<AadharEntity, Long> {

	AadharEntity findByCitizenId(Long citizenId);
}
