import { Component, OnInit } from '@angular/core';
import { User } from './model/Client';
import { LoginService } from './module/login/services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'games-shop-app';
  user: User = new User();

  constructor(private readonly loginService: LoginService) {}

  ngDoCheck() {
    if (sessionStorage.getItem('userdetails')) {
      this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    }
  }
  onLogoutClick() {
    this.loginService.logout().subscribe(() => {
      window.sessionStorage.removeItem('Authorization');
      window.sessionStorage.removeItem('userdetails');
      window.sessionStorage.removeItem('XSRF-TOKEN');
    });
    this.user = new User();
    // window.location.reload();
  }
}
