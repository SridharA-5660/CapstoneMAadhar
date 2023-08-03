import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {

  adminId!: string;
  password!: string;
  loginError = false;

  constructor(private router: Router) { }

  login(): void {
    // Check if the admin ID and password are correct
    if (this.adminId === 'admin' && this.password === 'admin123@') {
      // Navigate to the admin home page when the login is successful
      this.router.navigate(['/admin-home']);
    } else {
      // Handle login failure, show an error message
      this.loginError = true;
    }
  }

  redirectToUserLogin(): void {
    // Navigate to the user login page when the "User Login" button is clicked
    this.router.navigate(['/login']);
  }
}
