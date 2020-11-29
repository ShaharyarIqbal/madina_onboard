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
  private createClientSettingURL = environment.baseUrl+"/api/v1/clientsetting/"
  private getClientSettingURL = environment.baseUrl+"/api/v1/clientsetting/"
  private getCurrencyListURL  = environment.baseUrl+"/api/v1/currency";


  constructor(private http :HttpClient) { }

  public registerUser(user):Observable<any>{
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

  public saveClientSetting(clientSettingObj){
    return this.http.post(this.createClientSettingURL,clientSettingObj);
  }

  public getClientSettingById(id){
    return this.http.get(this.createClientSettingURL+id);
  }

  public updateClientSetting(id,clientSettingObj){
    return this.http.put(this.createClientSettingURL+id,clientSettingObj);
  }

  public getCurrencyList()
  {
    return this.http.get(this.getCurrencyListURL);
  }
}

