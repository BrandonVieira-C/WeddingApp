import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Customer } from "../shared/entity/Customer";
import { Partner } from "../shared/entity/Partner";


@Injectable({
  providedIn: 'root'
})

export class HomeService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' }) // for passing err/success message from backend to html

  constructor(private http: HttpClient) {}

  public getAllPartner(): Observable<Partner[]> {
    let url: string = environment.partnerApiUrl + "/getallpartner";
    return this.http.get<Partner[]>(url);
  }

  public deletePartner(partnerId: number): Observable<string> {
    let url: string = environment.partnerApiUrl + '/deletepartner/' + partnerId;
    return this.http.delete<string>(url, { headers: this.headers, responseType: 'text' as 'json' });
  }

  public getAllCustomers():Observable<Customer[]> {
    return this.http.get<Customer[]>(environment.customerApiUrl+'/getAllCustomers');
  }

  public deleteCustomer(customerId: number): Observable<string> {
    return this.http.delete<string>((environment.customerApiUrl+"/deletecustomer/"+customerId), { headers: this.headers, responseType: 'text' as 'json' });
  }
}