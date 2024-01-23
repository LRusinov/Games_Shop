import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Game } from 'src/app/model/Game';
import { DialogComponent } from 'src/app/module/game/components/dialog/dialog.component';
import { GameService } from 'src/app/module/game/services/game.service';
import { GenreService } from 'src/app/module/genre/services/genre.service';
import { PlatformService } from 'src/app/module/platform/services/platform.service';
import { PublisherService } from 'src/app/module/publisher/services/publisher.service';
import { LoginService } from '../../services/login.service';
import { User } from 'src/app/model/Client';
import { getCookie } from 'typescript-cookie';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm: FormGroup;
  authStatus: string = '';
  user: User = new User();
  constructor(
    private fb: FormBuilder,
    private readonly loginService: LoginService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: [],
      password: [],
    });
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  onLogin() {
    this.user.name = this.username?.value;
    this.user.password = this.password?.value;
    console.log(this.user);

    this.loginService
      .validateLoginDetails(this.user)
      .subscribe((responseData) => {
        window.sessionStorage.setItem(
          'Authorization',
          responseData.headers.get('Authorization')!
        );
        this.user = <any>responseData.body;
        this.user.authStatus = 'AUTH';
        window.sessionStorage.setItem('userdetails', JSON.stringify(this.user));
        let xsrf = getCookie('XSRF-TOKEN')!;
        window.sessionStorage.setItem('XSRF-TOKEN', xsrf);
        this.router.navigate(['game']);
      });
  }
  onNoClick() {}
}
