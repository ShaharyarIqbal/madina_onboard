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
    private service: MainServiceService,
    private router: Router,
    private toastr: ToastrService

  ) { }

  user: User = new User();
  loginUserDetails: login = new login();
  login = true;
  register = false;

  ngOnInit() {
    sessionStorage.clear();
  }

  goToLogin() {
    this.register = false;
    this.login = true;
  }

  goToRegister() {
    this.login = false;
    this.register = true;
  }

  erasingRegisterFields() {
    this.user.userName = "";
    this.user.password = "";
    this.user.city = "";
    this.user.country = "";
    this.user.masjidName = ""
    this.user.website = "";
    this.user.zip = "";
    this.user.timezone = "";
    this.user.contactNumber = "";
    this.user.state = "";
    this.user.street="";
  }

  erasingLoginFields() {
    this.loginUserDetails.userName = "";
    this.loginUserDetails.password = "";
  }

  registerUser() {
    this.user.status = "1";
    console.log("UserBody", this.user)
    this.service.registerUser(this.user).subscribe(
      res => {
        this.toastr.success("USER SUCCESSFULLY REGISTER")
        this.erasingRegisterFields();
      },
      error => {
        console.log("Response", error.status)
        this.toastr.error("USER ALREADY EXIST")
      })
  }

  loginUser() {
    console.log("LoginDetails", this.loginUserDetails)
    this.service.loginUser(this.loginUserDetails).subscribe(
      res => {
        if (res) {
          console.log("Response", res)
          this.erasingLoginFields();
          sessionStorage.setItem('token', res.accessToken);
          sessionStorage.setItem('ClientId',res.id);
          if (res.roles) {
            res.roles.map(d => {
              sessionStorage.setItem('role', d.authority)
              if (d.authority == "USER"|| d.authority =="ADMIN") {
                this.toastr.success("LOGIN SUCCESSFULLY ")
                this.router.navigate(['clientsetting'])
              }
            })
          }
        }
      },
      error => {
        console.log("Response", error.status)
        this.toastr.error("LOGIN FAILED")
      })
  }

}
