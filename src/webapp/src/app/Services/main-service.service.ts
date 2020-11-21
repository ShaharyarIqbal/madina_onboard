import { environment } from './../../environments/environment';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class MainServiceService {

  private registerUserURL = environment.baseUrl+"/api/v1/auth/register";
  private loginUserURL = environment.baseUrl+"/api/v1/auth/login";

  constructor(private http :HttpClient) { }

  public registerUser(user:Object):Observable<any>{
    return this.http.post(this.registerUserURL,user);
  }

  public loginUser(loginDetails:Object):Observable<any>{
    return this.http.post(this.loginUserURL,loginDetails);
  }

  loggedIn(){
    return !!sessionStorage.getItem('token')
  }

  userRole():boolean{
    if(sessionStorage.getItem('role') == 'USER'){
      return true;
    }
    else{
      return false;
    }
  }
}
