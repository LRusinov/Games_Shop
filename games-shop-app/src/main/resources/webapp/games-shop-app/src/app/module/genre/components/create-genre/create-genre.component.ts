import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PublisherService } from 'src/app/module/publisher/services/publisher.service';
import { GenreService } from '../../services/genre.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-genre',
  templateUrl: './create-genre.component.html',
  styleUrls: ['./create-genre.component.css'],
})
export class CreateGenreComponent {
  public form!: FormGroup;

  constructor(
    public readonly snackBar: MatSnackBar,
    public readonly genreService: GenreService,
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

    this.genreService.createGenre(this.name?.value).subscribe({
      next: (response) => {
        this.snackBar.open('Genre created successfully!', 'Okay');
        window.location.reload();
      },
      error: (err: HttpErrorResponse) => {
        if (err.status == 409) {
          this.snackBar.open('Genre with this name already exists.', 'Okay');
        } else {
          this.snackBar.open('An error occured. Genre was not created', 'Okay');
        }
      },
    });
  }
  onCancel() {
    window.history.back();
  }
}
