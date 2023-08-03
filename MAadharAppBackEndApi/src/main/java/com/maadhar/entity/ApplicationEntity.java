package com.maadhar.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "application")
public class ApplicationEntity {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applnid")
	private long applnId;
	
    @Column(name = "citizenid")
    private long citizenid;

    @Column(name = "application_status")
    private Integer applicationStatus;
    
    @Column(name="dateofappln")
    private Date dateOfAppln;

	

	public long getApplnId() {
		return applnId;
	}

	public void setApplnId(long applnId) {
		this.applnId = applnId;
	}

	public long getCitizenid() {
		return citizenid;
	}

	public void setCitizenid(long citizenid) {
		this.citizenid = citizenid;
	}

	public Integer getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(Integer applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public Date getDateOfAppln() {
		return dateOfAppln;
	}

	public void setDateOfAppln(Date dateOfAppln) {
		this.dateOfAppln = dateOfAppln;
	}
    
	
    

}
