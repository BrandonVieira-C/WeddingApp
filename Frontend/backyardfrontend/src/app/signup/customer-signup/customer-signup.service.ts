import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/shared/entity/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerSignupService {
  private baseUrl = "http://localhost:8080/wedding";
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' }); // for passing err/success message from backend to html

  constructor(private http: HttpClient) { }

  public addCustomer(customer: Customer): Observable<string> {
    return this.http.post<string>((this.baseUrl + "/addcustomer"), customer, { headers: this.headers, responseType: 'text' as 'json' });
  }
}
