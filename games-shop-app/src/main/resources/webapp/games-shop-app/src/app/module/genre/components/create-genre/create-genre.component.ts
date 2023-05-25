import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PublisherService } from 'src/app/module/publisher/services/publisher.service';
import { GenreService } from '../../services/genre.service';

@Component({
  selector: 'app-create-genre',
  templateUrl: './create-genre.component.html',
  styleUrls: ['./create-genre.component.css']
})
export class CreateGenreComponent {
  public form!: FormGroup;

  constructor(
    protected readonly genreService: GenreService,
    protected readonly formBuilder: FormBuilder
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

    this.genreService.createGenre( this.name?.value).subscribe()
    window.location.reload();
  }
  onCancel() {
    window.history.back();
  }
}
