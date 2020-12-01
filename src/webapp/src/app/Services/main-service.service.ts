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
  private createClientSettingURL = environment.baseUrl+"/api/v1/clientsetting/";
  private getClientSettingURL = environment.baseUrl+"/api/v1/clientsetting/";
  private getCurrencyListURL  = environment.baseUrl+"/api/v1/currency";
  private postPrayerTimmmingsURL = environment.baseUrl+"/api/v1/prayertime/";
  private getPrayerTimmingsByIDURL = environment.baseUrl+"/api/v1/prayertime/";
  private updatePrayerTimmingsURL = environment.baseUrl+"/api/v1/prayertime/";


  constructor(private http :HttpClient) { }

  header = {Authorization:'Bearer'+" "+sessionStorage.getItem('token')}


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
    return this.http.post(this.createClientSettingURL,clientSettingObj,{headers:this.header});
  }

  public getClientSettingById(id){
    return this.http.get(this.getClientSettingURL+id,{headers:this.header});
  }

  public updateClientSetting(id,clientSettingObj){
    return this.http.put(this.createClientSettingURL+id,clientSettingObj,{headers:this.header});
  }

  public getCurrencyList() {
    console.log(this.header);
    return this.http.get(this.getCurrencyListURL,{headers:this.header});
  }

  public postPrayerTimmings(prayerDetails:Object):Observable<any>{
    
    return this.http.post(this.postPrayerTimmmingsURL,prayerDetails,{headers:this.header});
  }

  public getPrayerTimmingsById(id:any):Observable<any>{
    return this.http.get(this.getPrayerTimmingsByIDURL+id,{headers:this.header});
  }

  public updatePrayerTimmings(id:any,prayerDetailsObject:Object):Observable<any>{
    return this.http.put(this.updatePrayerTimmingsURL+id,prayerDetailsObject,{headers:this.header});
  }
}

