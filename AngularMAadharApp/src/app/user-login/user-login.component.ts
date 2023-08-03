import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SharedDataService } from '../shared-data.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {

  citizenId!: string;
  password!: string;
  loginError: boolean = false;

  constructor(private router: Router, private http: HttpClient, private sharedDataService: SharedDataService) { }

  login(): void {
    // Make an HTTP request to your Spring Boot API to validate the Citizen ID and password
    // Replace 'http://localhost:6000/AadharApp/citizens/' with the correct API endpoint
    this.http.get<any>(`http://localhost:3000/AadharApp/citizens/${this.citizenId}`)
      .subscribe(
        (data) => {
          // If the API call is successful and the Citizen ID exists, check the password (phoneNo)
          if (data && data.phoneNo && data.phoneNo.toString() === this.password) {
            // Navigate to the home page when the login is successful
            const citizenIdNumber = parseInt(this.citizenId, 10);
            this.sharedDataService.setCitizenId(citizenIdNumber);
            this.router.navigate(['/home']);
          } else {
            // Handle login failure, show an error message
            this.loginError = true;
            console.log('Login failed');
          }
        },
        (error) => {
          // Handle errors, show an error message
          this.loginError = true;
          console.error('Error occurred during login:', error);
        }
      );
  }

  redirectToAdminLogin(): void {
    // Navigate to the admin login page when the "Admin Login" button is clicked
    this.router.navigate(['/admin-login']);
  }

  redirectToNewUser(): void {
    // Navigate to the new user register page when the "New User" button is clicked
    this.router.navigate(['/new-user']);
  }
}
