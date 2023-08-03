package com.maadhar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maadhar.entity.AadharEntity;
import com.maadhar.repository.AadharRepository;

@RestController
@RequestMapping("/AadharApp/aadhar")
public class AadharController {
	@Autowired
    private AadharRepository aadharRepository;

    @PostMapping("/add")
    public ResponseEntity<AadharEntity> addAadhar(@RequestBody AadharEntity aadharEntity) {
        AadharEntity savedAadhar = aadharRepository.save(aadharEntity);
        return new ResponseEntity<>(savedAadhar, HttpStatus.CREATED);
    }

    @GetMapping("/{citizenId}")
    public ResponseEntity<AadharEntity> viewAadharByCitizenId(@PathVariable Long citizenId) {
        AadharEntity aadhar = aadharRepository.findByCitizenId(citizenId);
        if (aadhar != null) {
            return new ResponseEntity<>(aadhar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<AadharEntity>> viewAllAadhar() {
        List<AadharEntity> aadharList = aadharRepository.findAll();
        if (!aadharList.isEmpty()) {
            return new ResponseEntity<>(aadharList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}