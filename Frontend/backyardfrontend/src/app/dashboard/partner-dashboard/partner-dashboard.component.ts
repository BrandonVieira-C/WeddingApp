import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { Router } from "@angular/router";
import { Backyard } from "src/app/shared/entity/Backyard";
import { Partner } from "src/app/shared/entity/Partner";
import { PartnerDashboardService } from "./partner-dashboard.service";


@Component({
  selector: "partner-dashboard",
  templateUrl: './partner-dashboard.component.html'
})

export class PartnerDashboardComponent implements OnInit {

  backyardsInDB: Backyard[];
  constructor(private partnerDashboardService: PartnerDashboardService) {}

  ngOnInit(): void {
    this.getLoggedInPartner();
    this.getPartnerBackyards();
  }

  loggedInPartner: Partner;

  public getLoggedInPartner() {
    this.loggedInPartner = JSON.parse(sessionStorage.getItem("loggedInPartner") || '{}') //some bug here in name 'loggedInPartner'
  }

  public getPartnerBackyards(): void {
    this.partnerDashboardService.getPartnerBackyards(this.loggedInPartner.partnerId).subscribe(
      response => {
        this.backyardsInDB = response;
        // sessionStorage.setItem("backyard", JSON.stringify(this.backyardsInDB));
      }
    )

  }

  
}