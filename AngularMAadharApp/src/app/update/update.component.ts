// update.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SharedDataService } from '../shared-data.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  citizenData: any = {};
  
  constructor(private route: ActivatedRoute, private http: HttpClient, private sharedDataService: SharedDataService) { }

  ngOnInit(): void {
    // Get the citizenId from the SharedDataService
    const citizenId = this.sharedDataService.getCitizenId();
    
    // Fetch the citizen data based on the citizenId from the API
    this.http.get<any>(`http://localhost:3000/AadharApp/citizens/${citizenId}`)
      .subscribe(
        (data) => {
          this.citizenData = data;
        },
        (error) => {
          console.error('Error occurred while fetching citizen data:', error);
        }
      );
  }

  updateCitizen(): void {
    // Send the updated citizen data to the API
    const citizenId = this.sharedDataService.getCitizenId();
    this.http.put<any>(`http://localhost:3000/AadharApp/citizens/update/${citizenId}`, this.citizenData)
      .subscribe(
        (data) => {
          console.log('Citizen data updated successfully:', data);
        },
        (error) => {
          console.error('Error occurred while updating citizen data:', error);
        }
      );
  }
}
