package com.maadhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maadhar.entity.CitizenEntity;

public interface CitizenRepository extends JpaRepository<CitizenEntity, Long> {

}
