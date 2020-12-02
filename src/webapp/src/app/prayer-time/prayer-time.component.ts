import { prayertime } from './prayertime';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { MainServiceService } from './../Services/main-service.service';
import { Component, OnInit } from '@angular/core';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-prayer-time',
  templateUrl: './prayer-time.component.html',
  styleUrls: ['./prayer-time.component.css']
})
export class PrayerTimeComponent implements OnInit {

  constructor(
    private service: MainServiceService,
    private router: Router,
    private toastar: ToastrService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.gettingQueryParam();
    if (this.queryId) {
      this.getPrayerTimeByID();
    }
    
    
  }
  queryId;
  prayerDetails: prayertime = new prayertime();

  onSubmit() {

    if(!this.queryId)
    {
    this.prayerDetails.clientId = parseInt(sessionStorage.getItem('ClientId'));
    console.log("PrayerDetails", this.prayerDetails);
    this.service.postPrayerTimmings(this.prayerDetails).subscribe(res => {

      console.log(res);
      this.toastar.success("Successfully Submit");
      this.erasingFields();

    },
      error => {
        this.toastar.error("Something Went Wrong");
      }
    );
    }
    else{
      this.prayerDetails.clientId = parseInt(sessionStorage.getItem('ClientId'));
      this.service.updatePrayerTimmings(this.queryId,   this.prayerDetails).subscribe(d=>{
        this.toastar.success("Updated Sucesfully");
        this.erasingFields();
        this.router.navigate(["listprayertime"])
      })
    }
  }

  gettingQueryParam() {
    this.route.queryParams
      .subscribe(params => {
        console.log(params);
        this.queryId = params.id;

      })
  }



  erasingFields() {

    this.prayerDetails.asrAdhaan = "";
    this.prayerDetails.asrIqamah = "";
    this.prayerDetails.dhuhrAdhaan = "";
    this.prayerDetails.dhuhrIqamah = "";
    this.prayerDetails.fajrAdhaan = "";
    this.prayerDetails.fajrIqamah = "";
    this.prayerDetails.maghribAdhaan = "";
    this.prayerDetails.maghribIqamah = "";
    this.prayerDetails.ishaAdhaan = "";
    this.prayerDetails.ishaIqamah = "";
    this.prayerDetails.date = "";

  }

  getPrayerTimeByID() {

    this.service.getPrayerById(this.queryId).subscribe(res => {
      console.log("Response", res);
      this.prayerDetails.asrAdhaan=res.asr_adhaan;
      this.prayerDetails.asrIqamah=res.asr_iqamah;
      this.prayerDetails.dhuhrAdhaan=res.dhuhr_adhaan;
      this.prayerDetails.dhuhrIqamah= res.dhuhr_iqamah;
      this.prayerDetails.fajrAdhaan= res.fajr_adhaan;
      this.prayerDetails.fajrIqamah=res.fajr_iqamah;
      this.prayerDetails.ishaAdhaan= res.isha_adhaan;
      this.prayerDetails.ishaIqamah= res.isha_iqamah;
      this.prayerDetails.maghribAdhaan= res.maghrib_adhaan;
      this.prayerDetails.maghribIqamah= res.maghrib_iqamah;
      this.prayerDetails.clientId=sessionStorage.getItem("ClientId");
      this.prayerDetails.date=res.date;
    })

  }



}
