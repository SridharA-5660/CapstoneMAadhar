package com.maadhar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maadhar.entity.ApplicationEntity;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
	// Find all applications by citizenid
    Optional<ApplicationEntity> findByCitizenid(long citizenid);

    // Check if an application exists by citizenid
    boolean existsByCitizenid(long citizenid);

    // Delete all applications by citizenid
    void deleteByCitizenid(long citizenid);
}
