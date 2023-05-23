import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { GameService } from 'src/app/module/game/services/game.service';
import { PublisherService } from '../../services/publisher.service';

@Component({
  selector: 'app-create-publisher',
  templateUrl: './create-publisher.component.html',
  styleUrls: ['./create-publisher.component.css']
})
export class CreatePublisherComponent {
  public form!: FormGroup;

  constructor(
    protected readonly publisherService: PublisherService,
    protected readonly formBuilder: FormBuilder
  ) {}
  
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: [null, [Validators.required]],
      yearOfCreation: [null, [Validators.required]],
      logoPictureUrl: [null, [Validators.required]],
      description: [null, [Validators.required]],
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

    this.publisherService.createPublisher( this.name?.value,
      this.yearOfCreation?.value,
      this.description?.value,
      this.logoPictureUrl?.value).subscribe()

  }
  onCancel() {
    window.history.back();
  }
}
