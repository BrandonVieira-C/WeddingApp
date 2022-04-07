import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Backyard } from "src/app/shared/entity/Backyard";
import { environment } from "src/environments/environment";
// test test test test
@Injectable({
  providedIn: 'root'
})

export class PartnerDashboardService {

  constructor(private http: HttpClient) { }

  public getPartnerBackyards(partnerId: number): Observable<Backyard[]> {
    let url: string = environment.partnerApiUrl + '/getallbackyards/' + partnerId;
    return this.http.get<Backyard[]>(url);
  }
  

}