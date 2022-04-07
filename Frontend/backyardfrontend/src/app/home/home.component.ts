import { Component, OnInit } from '@angular/core';
import { Partner } from '../shared/entity/Partner';
import { HomeService } from './home.service';


@Component({
  selector: 'app-home-page',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private homeService: HomeService) { }
  errMsg: string;
  successMsg: string;

  ngOnInit(): void {
    this.getAllPartner();
  }

  /* ====================================================================================================================================================== */
  // Function to get all registered partners
  partnersInDB: Partner[];

  public getAllPartner(): void {
    this.homeService.getAllPartner().subscribe({
      next: response => {
        this.partnersInDB = response;
      }, error: response => {
        console.error(response);
      }
    })
  }

  /* ====================================================================================================================================================== */
  // Function to delete partner which will also auto update table
  deletePartner: Partner = new Partner;

  public onDeletePartner(deletePartner: Partner): void {
    this.homeService.deletePartner(deletePartner.partnerId).subscribe({
      next: response => {
        this.successMsg = response;
        console.log(response);
        this.getAllPartner();
      }, error: response => {
        console.log(response.message);
        this.errMsg = <any> response;
      }
    })
  }






}