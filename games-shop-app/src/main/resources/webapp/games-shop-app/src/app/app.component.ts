import { Component, OnInit } from '@angular/core';
import { User } from './model/Client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'games-shop-app';
  user = new User();

  constructor() {}

  ngDoCheck() {
    if (sessionStorage.getItem('userdetails')) {
      this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    }
    console.log('AUTH:   ' + this.user.authStatus);
  }
}
