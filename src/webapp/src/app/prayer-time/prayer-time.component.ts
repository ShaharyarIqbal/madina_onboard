import { prayertime } from './prayertime';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { MainServiceService } from './../Services/main-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-prayer-time',
  templateUrl: './prayer-time.component.html',
  styleUrls: ['./prayer-time.component.css']
})
export class PrayerTimeComponent implements OnInit {

  constructor(
    private service : MainServiceService,
    private router : Router,
    private toastar : ToastrService
  ) { }

  ngOnInit() {
    this.getPrayerByClientID();
  }

  prayerDetails: prayertime = new prayertime();

  onSubmit(){
    this.prayerDetails.clientId = parseInt(sessionStorage.getItem('ClientId'));
    console.log("PrayerDetails",this.prayerDetails);
    this.service.postPrayerTimmings(this.prayerDetails).subscribe(res=>{
      console.log(res);
      this.toastar.success("Successfully Submit");
    },
    error=>{
       this.toastar.error("Something Went Wrong");
    }
    );
  
  }

  getPrayerByClientID(){
    let clientId=parseInt(sessionStorage.getItem('ClientId'));
    this.service.getPrayerTimmingsById(clientId).subscribe(res=>{
      console.log("Response",res);
    })

  }

  

}
