import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/shared/entity/Customer';
import { Events } from 'src/app/shared/entity/Event';
import { CustomerCreateEventService } from './customer-create-event.service';

@Component({
  selector: 'app-customer-create-event',
  templateUrl: './customer-create-event.component.html',
  styleUrls: []
})
export class CustomerCreateEventComponent implements OnInit {
  successMsg: string;
  errMsg: string;
  loggedInCustomer: Customer;
  newEvent: Events;
  customerId: number;
  createEventForm: FormGroup;
  chosenBackyardID: number;


  constructor(private fb: FormBuilder, private customerCreateService: CustomerCreateEventService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const routeParam = this.route.snapshot.paramMap;
    this.chosenBackyardID = Number(routeParam.get("backyardId"));

    this.getLoggedInCustomer();
    this.createEventFormMethod();
  }

  createEventFormMethod() {
  this.createEventForm = this.fb.group({
    eventName: ['', Validators.required],
    eventDate: ['', Validators.required],
    backyardId: [this.chosenBackyardID, Validators.required]
  });
}

  public getLoggedInCustomer() {
    this.loggedInCustomer = JSON.parse(sessionStorage.getItem("loggedInCustomer") || '{}'); //some bug here in name 'loggedInPartner'

  }
  createEvent() {
    this.successMsg = '';
    this.errMsg = '';
    this.newEvent = this.createEventForm.value as Events;
    this.newEvent.customerId = this.loggedInCustomer.customerId;
    this.customerCreateService.addEventForCustomer(this.newEvent).subscribe(
      (success) => {
        this.successMsg = success;
      },
      (error) => { this.errMsg = error }
    );
    this.createEventForm.reset();


  }

}
