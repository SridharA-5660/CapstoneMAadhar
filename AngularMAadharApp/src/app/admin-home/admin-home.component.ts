// admin-home.component.ts
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Application {
  applnId: number;
  citizenid: number;
  applicationStatus: number;
  dateOfAppln: string;
}

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {
  applications: Application[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchApplications();
  }

  fetchApplications(): void {
    // Make an HTTP request to fetch all applications
    // Replace 'http://localhost:3000/AadharApp/applications/all' with the correct API endpoint
    this.http.get<Application[]>('http://localhost:3000/AadharApp/applications/all')
      .subscribe(
        (data) => {
          this.applications = data;
        },
        (error) => {
          console.error('Error occurred while fetching applications:', error);
        }
      );
  }

  getApplicationStatusString(status: number): string {
    if (status === 1) {
      return 'Issue Aadhar';
    } else {
      return 'Unknown';
    }
  }

  issueAadhar(citizenId: number): void {
    // Make a POST request to issue Aadhar for the given citizenId with current date
    // Replace 'http://localhost:3000/AadharApp/aadhar/add' with the correct API endpoint
    const data = {
      citizenId: citizenId,
      issuedDate: this.getCurrentDate()
    };
    this.http.post('http://localhost:3000/AadharApp/aadhar/add', data)
      .subscribe(
        () => {
          console.log('Aadhar issued successfully.');
             // After the Aadhar is issued, make a DELETE request to remove the application record
          // Replace 'http://localhost:3000/AadharApp/applications/delete/' with the correct API endpoint
          this.http.delete(`http://localhost:3000/AadharApp/applications/delete/${citizenId}`)
            .subscribe(
              () => {
                console.log('Application record deleted successfully.');
                // You may refresh the list or update specific application status here if needed
              },
              (error) => {
                console.error('Error occurred while deleting application record:', error);
              }
            );
        },
        (error) => {
          console.error('Error occurred while issuing Aadhar:', error);
        }
      );
  }

  getCurrentDate(): string {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
}
