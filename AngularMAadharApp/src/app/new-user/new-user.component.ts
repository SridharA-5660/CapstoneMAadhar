import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent {
  newUser: any = {
    name: '',
    address: '',
    phoneNo: null,
    email: '',
    gender: 0,
    dob: ''
  };

  constructor(private http: HttpClient, private router: Router) { }

  addNewUser(): void {
    // Make a POST request to the add citizen API URL
    this.http.post('http://localhost:3000/AadharApp/citizens/add', this.newUser)
      .subscribe(
        (data) => {
          // Handle successful response, e.g., show a success message
          console.log('New user registered successfully:', data);
          // Redirect to the login page after successful registration
          this.router.navigate(['/login']);
        },
        (error) => {
          // Handle errors, show an error message or perform any other actions
          console.error('Error occurred during registration:', error);
        }
      );
  }
  redirectToUserLogin(): void {
    // Navigate to the user login page when the "User Login" button is clicked
    this.router.navigate(['/login']);
  }
}
