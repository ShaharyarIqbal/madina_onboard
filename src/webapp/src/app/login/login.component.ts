import { MainServiceService } from './../Services/main-service.service';
import { User } from './User';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { login } from './login';
import { ToastrService } from 'ngx-toastr';
 


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(
    private service : MainServiceService,
    private router : Router,
    private toastr: ToastrService
    
  ) { }

  user : User = new User();
  loginUserDetails : login = new login();
  login = true;
  register = false;

  ngOnInit() {
    sessionStorage.clear();
  }

  goToLogin(){
    this.register = false;
    this.login = true;
  }

  goToRegister(){
    this.login = false;
    this.register = true;
  }

  erasingRegisterFields(){
    this.user.userName="";
    this.user.password="";
    this.user.email="";
    this.user.firstName="";
    this.user.age=null;
  }

  erasingLoginFields(){
    this.loginUserDetails.userName="";
    this.loginUserDetails.password="";
  }

  registerUser(){
  console.log("UserBody",this.user)
  this.service.registerUser(this.user).subscribe(
    res=>{
    this.toastr.success("USER SUCCESSFULLY REGISTER")
    this.erasingRegisterFields();
   },
   error=>{
     console.log("Response",error.status)
     this.toastr.error("USER ALREADY EXIST")
   })
  }

  loginUser(){
    console.log("LoginDetails", this.loginUserDetails)
    this.service.loginUser(this.loginUserDetails).subscribe(
      res=>{
      if(res){
        console.log("Response",res)
        this.erasingLoginFields();
        sessionStorage.setItem('token',res.accessToken);
        if(res.roles){
          res.roles.map(d=>{
            sessionStorage.setItem('role',d.authority)
            if(d.authority=="USER"){
              this.toastr.success("LOGIN SUCCESSFULLY ")
              this.router.navigate(['home'])
            }
          })
        }
      }
    },
    error=>{
      console.log("Response",error.status)
      this.toastr.error("LOGIN FAILED")
    })
  }

}
