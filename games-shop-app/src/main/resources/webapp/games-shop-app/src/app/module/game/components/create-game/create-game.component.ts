import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GameService } from '../../services/game.service';
import { Publisher } from 'src/app/model/Publisher';
import { Platform } from 'src/app/model/Platform';
import { Genre } from 'src/app/model/Genre';
import { GenreService } from 'src/app/module/genre/services/genre.service';
import { PlatformService } from 'src/app/module/platform/services/platform.service';
import { PublisherService } from 'src/app/module/publisher/services/publisher.service';

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.css'],
})
export class CreateGameComponent implements OnInit {
  public form!: FormGroup;
  publishers: Publisher[] = [];
  platforms: Platform[] = [];
  genres: Genre[] = [];
  checkedGenres: string[] = [];
  checked = false;

  constructor(
    protected readonly platfromService: PlatformService,
    protected readonly genreService: GenreService,
    protected readonly publisherService: PublisherService,
    protected readonly gameService: GameService,
    protected readonly formBuilder: FormBuilder
  ) {}
  ngOnInit(): void {
    this.initHelper();
    this.form = this.formBuilder.group({
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      platform: [null, [Validators.required]],
      description: [null, [Validators.required]],
      releaseDate: [null, [Validators.required]],
      publisher: [null, [Validators.required]],
      picture: [null, [Validators.required]],
    });
  }
  initHelper() {
    this.platfromService.getPlatforms().subscribe((response) => {
      this.platforms = response;
    });
    this.publisherService.getPublishers().subscribe((response) => {
      this.publishers = response;
    });
    this.genreService.getGenres().subscribe((response) => {
      this.genres = response;
    });
  }

  get name() {
    return this.form.get('name');
  }
  get price() {
    return this.form.get('price');
  }
  get platform() {
    return this.form.get('platform');
  }
  get description() {
    return this.form.get('description');
  }
  get publisher() {
    return this.form.get('publisher');
  }
  get picture() {
    return this.form.get('picture');
  }
  get releaseDate() {
    return this.form.get('releaseDate');
  }

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }
    this.gameService
      .createGame(
        this.name?.value,
        this.price?.value,
        this.platform?.value,
        this.checkedGenres,
        this.description?.value,
        this.releaseDate?.value,
        this.publisher?.value,
        this.picture?.value
      )
      .subscribe();
  }
  onCancel() {
    window.history.back();
  }

  isChecked(genre: string): boolean {
    return this.checkedGenres.includes(genre);
  }

  onCheckboxChange(event: any, genre: string) {
    if (this.checkedGenres.indexOf(genre) == -1) {
      this.checkedGenres.push(genre);
    } else {
      const index = this.checkedGenres.indexOf(genre);
      if (index >= 0) {
        this.checkedGenres.splice(index, 1);
      }
    }
  }
}
