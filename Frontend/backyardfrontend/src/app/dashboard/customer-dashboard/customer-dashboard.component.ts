import { Component, OnInit } from '@angular/core';
import { Backyard } from 'src/app/shared/entity/Backyard';
import { Customer } from 'src/app/shared/entity/Customer';
import { CustomerDashboardService } from './customer-dashboard.service';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: []
})
export class CustomerDashboardComponent implements OnInit {
  backyardsInDB: Backyard[];
  loggedInCustomer: Customer;

  constructor(private customerDashboardService: CustomerDashboardService) { }

  ngOnInit(): void {
    this.getLoggedInCustomer();
    this.getAllBackyards();
  }

  public getLoggedInCustomer() {
    this.loggedInCustomer = JSON.parse(sessionStorage.getItem("loggedInCustomer") || '{}') //some bug here in name 'loggedInPartner'

  }

  public getAllBackyards(): void {
    this.customerDashboardService.getAllBackyards().subscribe(
      (success) => {this.backyardsInDB = success }
    );

  }

}
