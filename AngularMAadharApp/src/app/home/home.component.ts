import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { SharedDataService } from '../shared-data.service';
import * as $ from 'jquery';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  citizenId: number | null = null;
  aadharData: any;
  citizenData: any;
  hasAadharData: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private sharedDataService: SharedDataService
  ) {}

  ngOnInit(): void {
    // Get the citizen ID from the shared service
    this.citizenId = this.sharedDataService.getCitizenId();

    if (this.citizenId) {
      this.checkAadharData();
      this.getCitizenData();
    }
  }

  checkAadharData(): void {
    // Make an HTTP request to check if there is any Aadhar data for the citizen ID
    // Replace 'http://localhost:3000/AadharApp/aadhar/' with the correct API endpoint
    this.http.get<any>(`http://localhost:3000/AadharApp/aadhar/${this.citizenId}`)
      .subscribe(
        (data) => {
          if (data) {
            this.aadharData = data;
            this.hasAadharData = true;
          }
        },
        (error) => {
          // Handle errors, show an error message or perform any other actions
          console.error('Error occurred while fetching Aadhar data:', error);
        }
      );
  }

  getCitizenData(): void {
    // Make an HTTP request to get the citizen data
    // Replace 'http://localhost:3000/AadharApp/citizens/' with the correct API endpoint
    this.http.get<any>(`http://localhost:3000/AadharApp/citizens/${this.citizenId}`)
      .subscribe(
        (data) => {
          this.citizenData = data;
        },
        (error) => {
          // Handle errors, show an error message or perform any other actions
          console.error('Error occurred while fetching citizen data:', error);
        }
      );
  }

  getGenderString(gender: number | undefined): string {
    if (gender === 1) {
      return 'Male';
    } else if (gender === 2) {
      return 'Female';
    } else if (gender === 3) {
      return 'Other';
    } else {
      return 'Unknown';
    }
  }

  showPopup() {
    // Display a normal browser popup with the message "Aadhar duplicate issued"
    window.alert('Aadhar duplicate issued');
  }
  issueAadhar(): void {
    const currentDate = new Date().toISOString().split('T')[0];
    const applicationData = {
      citizenid: this.citizenId,
      applicationStatus: 1,
      dateOfAppln: currentDate
    };

    // Send the POST request to issue Aadhar
    // Replace 'http://localhost:3000/AadharApp/applications/add' with the correct API endpoint
    this.http.post<any>('http://localhost:3000/AadharApp/applications/add', applicationData)
      .subscribe(
        (data) => {
          // Handle the response if needed
          console.log('Aadhar issued successfully:', data);
        },
        (error) => {
          // Handle errors, show an error message or perform any other actions
          console.error('Error occurred while issuing Aadhar:', error);
        }
      );
  }
}
