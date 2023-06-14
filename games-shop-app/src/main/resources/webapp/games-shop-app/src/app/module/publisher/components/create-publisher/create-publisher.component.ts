import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { GameService } from 'src/app/module/game/services/game.service';
import { PublisherService } from '../../services/publisher.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create-publisher',
  templateUrl: './create-publisher.component.html',
  styleUrls: ['./create-publisher.component.css'],
})
export class CreatePublisherComponent {
  public form!: FormGroup;

  constructor(
    public readonly snackBar: MatSnackBar,
    public readonly publisherService: PublisherService,
    public readonly formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: [null, [Validators.required, Validators.maxLength(40)]],
      yearOfCreation: [null, [Validators.required]],
      logoPictureUrl: [null, [Validators.required]],
      description: [null, [Validators.required, Validators.maxLength(350)]],
    });
  }

  get name() {
    return this.form.get('name');
  }
  get yearOfCreation() {
    return this.form.get('yearOfCreation');
  }
  get description() {
    return this.form.get('description');
  }
  get logoPictureUrl() {
    return this.form.get('logoPictureUrl');
  }

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    this.publisherService
      .createPublisher(
        this.name?.value,
        this.yearOfCreation?.value,
        this.description?.value,
        this.logoPictureUrl?.value
      )
      .subscribe({
        next: (response) => {
          this.snackBar
            .open('Publisher added successfully!', 'Okay')
            .afterDismissed()
            .subscribe(() => {
              window.location.reload();
            });
        },
        error: (err: HttpErrorResponse) => {
          if (err.status == 409) {
            this.snackBar.open(
              'Publisher with this name already exists.',
              'Okay'
            );
          } else {
            this.snackBar.open(
              'An error occured. Publisher was not created',
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
