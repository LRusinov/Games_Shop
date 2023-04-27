import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GameService } from '../../services/game.service';

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.css'],
})
export class CreateGameComponent implements OnInit {
  public createGameForm!: FormGroup;
  publishers: string[] = [];
  platforms: string[] = [];
  genres: string[] = [];
  checkedGenres: string[] = [];
  checked = false;

  constructor(
    private readonly gameService: GameService,
    private readonly formBuilder: FormBuilder
  ) {}
  ngOnInit(): void {
    this.gameService.getPlatforms().subscribe((response) => {
      this.platforms = response;
    });
    this.gameService.getPublishers().subscribe((response) => {
      this.publishers = response;
    });
    this.gameService.getGenres().subscribe((response) => {
      this.genres = response;
    });
    this.createGameForm = this.formBuilder.group({
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      platform: [null, [Validators.required]],
      description: [null, [Validators.required]],
      releaseDate: [null, [Validators.required]],
      publisher: [null, [Validators.required]],
      picture: [null, [Validators.required]],
      genres: [this.genres.length != 0],
    });
  }

  get name() {
    return this.createGameForm.get('name');
  }
  get price() {
    return this.createGameForm.get('price');
  }
  get platform() {
    return this.createGameForm.get('platform');
  }
  get description() {
    return this.createGameForm.get('description');
  }
  get publisher() {
    return this.createGameForm.get('publisher');
  }
  get picture() {
    return this.createGameForm.get('picture');
  }
  get releaseDate() {
    return this.createGameForm.get('releaseDate');
  }

  onSubmit(): void {
    if (this.createGameForm.invalid) {
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

  isChecked(genre: string): boolean {
    return this.checkedGenres.includes(genre);
  }

  onCheckboxChange(event: any, genre: string) {
    if (event.target.checked) {
      this.checkedGenres.push(genre);
    } else {
      const index = this.checkedGenres.indexOf(genre);
      if (index >= 0) {
        this.checkedGenres.splice(index, 1);
      }
    }
  }
}
