import { Component } from '@angular/core';
import { PlatformService } from '../../services/platform.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-platform',
  templateUrl: './create-platform.component.html',
  styleUrls: ['./create-platform.component.css'],
})
export class CreatePlatformComponent {
  public form!: FormGroup;

  constructor(
    public readonly snackBar: MatSnackBar,
    public readonly platformService: PlatformService,
    public readonly formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: [null, [Validators.required]],
    });
  }

  get name() {
    return this.form.get('name');
  }

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    this.platformService.createPlatform(this.name?.value).subscribe({
      next: (response) => {
        this.snackBar.open('Platform created successfully!', 'Okay');
        window.location.reload();
      },
      error: (err: HttpErrorResponse) => {
        if (err.status == 409) {
          this.snackBar.open('Platform with this name already exists.', 'Okay');
        } else {
          this.snackBar.open(
            'An error occured. Platform was not created',
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
