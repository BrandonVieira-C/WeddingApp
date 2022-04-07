import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Events } from 'src/app/shared/entity/Event';

@Injectable({
  providedIn: 'root'
})
export class CustomerCreateEventService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' }); // for passing err/success message from backend to html

  private baseUrl = "http://localhost:8080/wedding";

  constructor(private http: HttpClient) { }

  public addEventForCustomer(event: Events): Observable<string> {
    return this.http.post<string>((this.baseUrl+"/addevent"), event);
  }
}
