import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { MainServiceService } from './../Services/main-service.service';
import { Component, OnInit } from '@angular/core';
import { ClientSetting } from './ClientSetting';
import * as moment from 'moment';


@Component({
  selector: 'app-client-setting',
  templateUrl: './client-setting.component.html',
  styleUrls: ['./client-setting.component.css']
})
export class ClientSettingComponent implements OnInit {

  constructor(
    private service: MainServiceService,
    private router: Router,
    private toastr: ToastrService

  ) {}

  clientSetting = new ClientSetting();
  currencyList:any=[];
  buttonName = "Save";
  id;

  ngOnInit() {
    // this.getClientSettingById();
    this.getCurrencyList();

  }



  submitClientSetting(){
    if(!this.id){
      this.clientSetting.clientId = parseInt(sessionStorage.getItem('ClientId')) ;
      console.log(this.clientSetting.clientId)
      console.log(this.clientSetting);
      this.service.saveClientSetting(this.clientSetting).subscribe(res=>{
        console.log("res", res);
        this.toastr.success("Successfully Saved")
        this.erasingFields();
      },
      error=>{
        console.log("Response", error.status)
        this.toastr.error("Something Went Wrong");
      }
      )
    }
    else{
      this.service.updateClientSetting(this.id,this.clientSetting).subscribe(res=>{
        console.log(res);

      })
    }

  }

  getClientSettingById(){
    let clientId = parseInt(sessionStorage.getItem('ClientId'));
    console.log(clientId)
    this.service.getClientSettingById(clientId).subscribe((res:any)=>{
        console.log("ClientSetting",res);
        this.id = res.id;
        this.clientSetting.latitude = res.latitude;
        this.clientSetting.longitude = res.longitude;
        this.clientSetting.juristicMethod = res.juristicMethod;
        this.clientSetting.calculationMethod = res.calculationMethod;
        this.clientSetting.currencyId = res.currency.id;
        this.buttonName ="Update"
    },
    error=>{
      console.log("Response", error.status)

    }
    )

  }

  getCurrencyList(){
    this.service.getCurrencyList().subscribe(res=>{
      console.log("List",res);
      this.currencyList = res;
    },
    error=>{
      console.log("Response", error.status)
      this.toastr.error("Something Went Wrong")
    })
  }

  erasingFields(){
    this.clientSetting.calculationMethod="";
    this.clientSetting.juristicMethod="";
    this.clientSetting.latitude=null;
    this.clientSetting.longitude=null;
    this.clientSetting.currencyId=null;
  }

  logout(){
    sessionStorage.clear();
    this.router.navigate([''])
  }

  // disableFields(){
  //   if(this.clientSetting.calculationMethod&&this.clientSetting.currencyId&&this.clientSetting.juristicMethod&&this.clientSetting.latitude&&this.clientSetting.longitude){
  //     this.disable=false;
  // }
}

