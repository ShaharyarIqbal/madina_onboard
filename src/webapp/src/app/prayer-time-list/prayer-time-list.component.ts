import { Router } from '@angular/router';
import { MainServiceService } from './../Services/main-service.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-prayer-time-list',
  templateUrl: './prayer-time-list.component.html',
  styleUrls: ['./prayer-time-list.component.css']
})
export class PrayerTimeListComponent implements OnInit {

  constructor(
    private service : MainServiceService,
    private router : Router,
    private toastar : ToastrService
  ) { }

  prayerTimeArray:any=[];

  ngOnInit() {
    this.getPrayerTimeListByClientId();
  }
  
  routeToUpdatePrayerTime(data){
    console.log(data);
    this.router.navigate(['prayertime'],{ queryParams: { id: data.id } })

  }

  getPrayerTimeListByClientId(){
    let clientId=parseInt(sessionStorage.getItem('ClientId'));  
    this.service.getPrayerTimmingsById(clientId).subscribe(res=>{
      console.log(res);
      this.prayerTimeArray = res;
      this.toastar.success("SuccessFully Get")
      
    },
    error=>{
      this.toastar.error("Something Went Wrong");
    })
  }

}
