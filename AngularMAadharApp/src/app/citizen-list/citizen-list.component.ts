import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-citizen-list',
  templateUrl: './citizen-list.component.html',
  styleUrls: ['./citizen-list.component.css']
})
export class CitizenListComponent implements OnInit {
  citizens: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchCitizensData();
  }

  fetchCitizensData(): void {
    // Fetch citizens data from 'localhost:3000/AadharApp/citizens/list'
    this.http.get<any[]>('http://localhost:3000/AadharApp/citizens/list')
      .subscribe(
        (data) => {
          this.citizens = data;
          // Fetch Aadhar numbers for each citizen
          this.fetchAadharNumbers();
        },
        (error) => {
          console.error('Error occurred while fetching citizens data:', error);
        }
      );
  }

  fetchAadharNumbers(): void {
    // Fetch Aadhar numbers for each citizen and update citizens array
    this.citizens.forEach((citizen) => {
      this.http.get<any>(`http://localhost:3000/AadharApp/aadhar/${citizen.citizenId}`)
        .subscribe(
          (data) => {
            citizen.aadharNo = data.aadharNo;
          },
          (error) => {
            console.error(`Error occurred while fetching Aadhar number for Citizen ID ${citizen.citizenId}:`, error);
          }
        );
    });
  }

  deleteCitizen(citizenId: number): void {
    // Send a delete request to 'localhost:3000/AadharApp/citizens/delete/{citizenid}'
    this.http.delete(`http://localhost:3000/AadharApp/citizens/delete/${citizenId}`)
      .subscribe(
        () => {
          // If the delete request is successful, remove the deleted citizen from the citizens array
          this.citizens = this.citizens.filter((citizen) => citizen.citizenId !== citizenId);
        },
        (error) => {
          console.error(`Error occurred while deleting Citizen ID ${citizenId}:`, error);
        }
      );
  }
}
