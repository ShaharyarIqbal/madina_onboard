import { Component, OnInit } from '@angular/core';
import { ClientSetting } from './ClientSetting';

@Component({
  selector: 'app-client-setting',
  templateUrl: './client-setting.component.html',
  styleUrls: ['./client-setting.component.css']
})
export class ClientSettingComponent implements OnInit {

  constructor() { }
clientSetting = new ClientSetting();
  ngOnInit() {
  }

}
