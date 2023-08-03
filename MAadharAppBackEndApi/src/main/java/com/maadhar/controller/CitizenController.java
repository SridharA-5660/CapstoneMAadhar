package com.maadhar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maadhar.entity.CitizenEntity;
import com.maadhar.repository.CitizenRepository;

@RestController
@RequestMapping("/AadharApp/citizens")
public class CitizenController {
	
	@Autowired
	CitizenRepository ctznRepo;
	
	//private static final Logger logger = LoggerFactory.getLogger(CitizenController.class);
	
	//to view all citizens list
	@GetMapping(path="/list", produces = "application/json")
	public ResponseEntity<List<CitizenEntity>> displayData()
	{
		List<CitizenEntity> citizen = ctznRepo.findAll();		
		// logger.info("Citizens fetched: {}", citizen); // Log
		return new ResponseEntity<List<CitizenEntity>>(citizen, HttpStatusCode.valueOf(200));		
	
	}
	
	  @GetMapping("/{citizenId}")
	    public ResponseEntity<CitizenEntity> viewCitizen(@PathVariable Long citizenId) {
	        CitizenEntity citizen = ctznRepo.findById(citizenId)
	                .orElse(null);

	        if (citizen != null) {
	            return new ResponseEntity<>(citizen, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	// Add a new citizen
	    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<CitizenEntity> addCitizen(@RequestBody CitizenEntity citizen) {
	        // Check if the phone number is 10 digits long
	        if (isValidPhoneNumber(citizen.getPhoneNo())) {
	            CitizenEntity savedCitizen = ctznRepo.save(citizen);
	            return new ResponseEntity<>(savedCitizen, HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }

	    // Update an existing citizen
	    @PutMapping(path = "/update/{citizenId}", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<CitizenEntity> updateCitizen(
	            @PathVariable Long citizenId,
	            @RequestBody CitizenEntity updatedCitizen) {

	        // Check if the phone number is 10 digits long
	        if (isValidPhoneNumber(updatedCitizen.getPhoneNo())) {
	            CitizenEntity existingCitizen = ctznRepo.findById(citizenId)
	                    .orElse(null);

	            if (existingCitizen != null) {
	                // Update the fields of the existing citizen
	                existingCitizen.setName(updatedCitizen.getName());
	                existingCitizen.setAddress(updatedCitizen.getAddress());
	                existingCitizen.setPhoneNo(updatedCitizen.getPhoneNo());
	                existingCitizen.setEmail(updatedCitizen.getEmail());
	                existingCitizen.setGender(updatedCitizen.getGender());
	                existingCitizen.setDob(updatedCitizen.getDob());

	                CitizenEntity updatedCitizenEntity = ctznRepo.save(existingCitizen);
	                return new ResponseEntity<>(updatedCitizenEntity, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } else {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	 // Delete a citizen by ID
	    @DeleteMapping("/delete/{citizenId}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteCitizen(@PathVariable Long citizenId) {
	        // Find the citizen with the given ID
	        CitizenEntity existingCitizen = ctznRepo.findById(citizenId).orElse(null);

	        if (existingCitizen != null) {
	            // Delete the citizen
	            ctznRepo.delete(existingCitizen);
	        }
	    }

	    // Utility method to check if the phone number is 10 digits long
	    private boolean isValidPhoneNumber(Long phoneNo) {
	        return phoneNo != null && String.valueOf(phoneNo).length() == 10;
	    }
	
}
