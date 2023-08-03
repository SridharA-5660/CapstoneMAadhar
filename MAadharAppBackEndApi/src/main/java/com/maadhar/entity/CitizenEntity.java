package com.maadhar.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "citizen")
public class CitizenEntity {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "citizenid")
	    private Long citizenId;
	
	    @Column(name = "name")
	    private String name;
	    
	 	@Column(name = "address")
	    private String address;

	    @Column(name = "phoneno", unique = true)
	    private Long phoneNo;

	    @Column(name = "email")
	    private String email;

	    @Column(name = "gender")
	    private Integer gender;

	    @Column(name = "dob")
	    private Date dob;

		public Long getCitizenId() {
			return citizenId;
		}

		public void setCitizenId(Long citizenId) {
			this.citizenId = citizenId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Long getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(Long phoneNo) {
			this.phoneNo = phoneNo;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getGender() {
			return gender;
		}

		public void setGender(Integer gender) {
			this.gender = gender;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}
	    
	    

}
