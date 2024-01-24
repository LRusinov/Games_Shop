import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ClientService } from '../../services/client.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  public form!: FormGroup;

  constructor(
    private router: Router,
    public readonly snackBar: MatSnackBar,
    public readonly clientService: ClientService,
    public readonly formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        username: [null, [Validators.required, Validators.maxLength(20)]],
        password: [null, [Validators.required]],
        confirmPassword: [null, [Validators.required]],
      },
      { validator: this.passwordMatchValidator }
    );
  }

  get username() {
    return this.form.get('username');
  }

  get password() {
    return this.form.get('password');
  }

  get confirmPassword() {
    return this.form.get('confirmPassword');
  }

  checkIfPasswordMatch() {
    if (this.password == undefined || this.confirmPassword == undefined) {
      return true;
    }
    return this.password.value === this.confirmPassword.value;
  }

  passwordMatchValidator(
    control: AbstractControl
  ): { [key: string]: boolean } | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    return password === confirmPassword ? null : { passwordMismatch: true };
  }

  onRegisterClick(): void {
    this.clientService
      .registerClient(this.username?.value, this.password?.value)
      .subscribe({
        next: (response) => {
          this.snackBar
            .open('You have registered successfully!', 'Okay')
            .afterDismissed()
            .subscribe(() => {
              this.router.navigate(['/']);
            });
        },
        error: (err: HttpErrorResponse) => {
          if (err.status == 409) {
            this.snackBar.open(
              'User with this username already exists.',
              'Okay'
            );
          } else {
            this.snackBar.open(
              'An error occured. Registration failed.',
              'Okay'
            );
          }
        },
      });
  }
  onCancel() {
    window.history.back();
  }
}
