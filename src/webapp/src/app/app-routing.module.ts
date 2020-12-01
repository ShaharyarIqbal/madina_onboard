import { PrayerTimeComponent } from './prayer-time/prayer-time.component';
import { AuthguardGuard } from './authguard.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientSettingComponent } from './client-setting/client-setting.component';

const routes: Routes = [
  {path:"",component: LoginComponent},
  {path:"home",component: HomeComponent, canActivate:[AuthguardGuard]},
  {path:"clientsetting",component:ClientSettingComponent, canActivate:[AuthguardGuard]},
  {path:"prayertime",component:PrayerTimeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
