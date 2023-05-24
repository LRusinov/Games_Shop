import { Component } from '@angular/core';
import { PlatformService } from '../../services/platform.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-platform',
  templateUrl: './create-platform.component.html',
  styleUrls: ['./create-platform.component.css']
})
export class CreatePlatformComponent {
  public form!: FormGroup;

  constructor(
    protected readonly platformService: PlatformService,
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

    this.platformService.createPlatform(this.name?.value).subscribe()

  }
  onCancel() {
    window.history.back();
  }
}
