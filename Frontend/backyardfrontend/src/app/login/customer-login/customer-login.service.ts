import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/shared/entity/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerLoginService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private baseUrl = "http://localhost:8080/wedding";
  
  constructor(private http: HttpClient) { }

  public login(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>((this.baseUrl+"/customerlogin"), customer, {headers: this.headers});

  }
}
