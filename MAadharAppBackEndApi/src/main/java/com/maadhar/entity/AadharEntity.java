package com.maadhar.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aadhar")
public class AadharEntity {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "aadhar_no")
	    private Long aadharNo;

	 	
	    @Column(name ="citizenid")
	    private Long citizenId;
	    
	    @Column(name="issueddate")
	    private Date issuedDate;
	  
		public Long getAadharNo() {
			return aadharNo;
		}

		public void setAadharNo(Long aadharNo) {
			this.aadharNo = aadharNo;
		}

		public Long getCitizenId() {
			return citizenId;
		}

		public void setCitizenId(Long citizenId) {
			this.citizenId = citizenId;
		}

		public Date getIssuedDate() {
			return issuedDate;
		}

		public void setIssuedDate(Date issuedDate) {
			this.issuedDate = issuedDate;
		}  
		
		

}
