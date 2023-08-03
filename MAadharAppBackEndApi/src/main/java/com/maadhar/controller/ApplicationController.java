package com.maadhar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maadhar.entity.ApplicationEntity;
import com.maadhar.repository.ApplicationRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/AadharApp/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    // View all applications
    @GetMapping("/all")
    public ResponseEntity<List<ApplicationEntity>> viewAllApplications() {
        List<ApplicationEntity> applications = applicationRepository.findAll();
        if (!applications.isEmpty()) {
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // View application by citizenid
    @GetMapping("/{citizenid}")
    public ResponseEntity<ApplicationEntity> viewApplicationByCitizenId(@PathVariable long citizenid) {
        Optional<ApplicationEntity> application = applicationRepository.findByCitizenid(citizenid);
        return application.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update application for a specific citizen
    @PutMapping("/update/{citizenid}")
    public ResponseEntity<ApplicationEntity> updateApplication(@PathVariable long citizenid, @RequestBody ApplicationEntity updatedApplication) {
        Optional<ApplicationEntity> existingApplication = applicationRepository.findByCitizenid(citizenid);
        if (existingApplication.isPresent()) {
            ApplicationEntity application = existingApplication.get();
            application.setApplicationStatus(updatedApplication.getApplicationStatus());
            application.setDateOfAppln(updatedApplication.getDateOfAppln());
            ApplicationEntity updatedApplicationEntity = applicationRepository.save(application);
            return new ResponseEntity<>(updatedApplicationEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApplicationEntity> addApplicationForCitizen(@RequestBody ApplicationEntity newApplication) {
        ApplicationEntity savedApplication = applicationRepository.save(newApplication);
        return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
    }
    
    // Delete application for a specific citizen
    @Transactional
    @DeleteMapping("/delete/{citizenid}")
    public ResponseEntity<Void> deleteApplication(@PathVariable long citizenid) {
        if (applicationRepository.existsByCitizenid(citizenid)) {
            applicationRepository.deleteByCitizenid(citizenid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}