import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Backyard } from 'src/app/shared/entity/Backyard';
import { environment } from "src/environments/environment";


@Injectable({
  providedIn: 'root'
})
export class CustomerDashboardService {
  

  constructor(private http: HttpClient) { }

  getAllBackyards(): Observable<Backyard[]> {
    return this.http.get<Backyard[]>(environment.customerApiUrl+"/getallbackyards");
  }


}
