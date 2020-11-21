import { MainServiceService } from './Services/main-service.service';
import { Injectable } from '@angular/core';
import { CanActivate, CanLoad,Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate, CanLoad {
  constructor( private service: MainServiceService , private route :Router ){}

  canActivate(): boolean {
    if(this.service.loggedIn() && this.service.userRole()){
      return true
    }else{
      this.route.navigate([''])
      return false
    }
  }

  canLoad():boolean  {
    return true;
  }
}
