import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  private citizenId: number | null = null;

  setCitizenId(citizenId: number): void {
    this.citizenId = citizenId;
  }

  getCitizenId(): number | null {
    return this.citizenId;
  }
}
