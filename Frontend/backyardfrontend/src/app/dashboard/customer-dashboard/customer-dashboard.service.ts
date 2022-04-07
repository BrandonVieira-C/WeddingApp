import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Backyard } from 'src/app/shared/entity/Backyard';

@Injectable({
  providedIn: 'root'
})
export class CustomerDashboardService {
  private baseUrl = "http://localhost:8080/wedding";

  constructor(private http: HttpClient) { }

  getAllBackyards(): Observable<Backyard[]> {
    return this.http.get<Backyard[]>(this.baseUrl+"/getallbackyards");
  }


}
