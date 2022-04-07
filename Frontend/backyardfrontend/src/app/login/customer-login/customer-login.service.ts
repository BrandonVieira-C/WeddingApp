import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/shared/entity/Customer';
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CustomerLoginService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  constructor(private http: HttpClient) { }

  public login(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>((environment.customerApiUrl+"/customerlogin"), customer, {headers: this.headers});

  }
}
