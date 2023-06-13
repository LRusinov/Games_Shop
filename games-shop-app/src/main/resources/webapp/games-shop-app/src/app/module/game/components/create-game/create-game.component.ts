import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GameService } from '../../services/game.service';
import { Publisher } from 'src/app/model/Publisher';
import { Platform } from 'src/app/model/Platform';
import { Genre } from 'src/app/model/Genre';
import { GenreService } from 'src/app/module/genre/services/genre.service';
import { PlatformService } from 'src/app/module/platform/services/platform.service';
import { PublisherService } from 'src/app/module/publisher/services/publisher.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

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
  checkedPlatforms: string[] = [];
  checkedGenres: string[] = [];

  constructor(
    private router: Router,
    public readonly snackBar: MatSnackBar,
    public readonly platfromService: PlatformService,
    public readonly genreService: GenreService,
    public readonly publisherService: PublisherService,
    public readonly gameService: GameService,
    public readonly formBuilder: FormBuilder
  ) {}
  ngOnInit(): void {
    this.initHelper();
    this.form = this.formBuilder.group({
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
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
        this.checkedPlatforms,
        this.checkedGenres,
        this.description?.value,
        this.releaseDate?.value,
        this.publisher?.value,
        this.picture?.value
      )
      .subscribe({
        next: (response) => {
          this.snackBar
            .open('Game created successfully!', 'Okay')
            .afterDismissed()
            .subscribe(() => {
              this.router.navigate(['/game']);
            });
        },
        error: (err: HttpErrorResponse) => {
          if (err.status == 409) {
            this.snackBar.open('Game with this name already exists.', 'Okay');
          } else {
            this.snackBar.open(
              'An error occured. Game was not created',
              'Okay'
            );
          }
        },
      });
  }
  onCancel() {
    window.history.back();
  }

  isPlatformChecked(platform: string): boolean {
    return this.checkedPlatforms.includes(platform);
  }

  onPlatformCheckboxChange(event: any, platform: string) {
    if (this.checkedPlatforms.indexOf(platform) == -1) {
      this.checkedPlatforms.push(platform);
    } else {
      const index = this.checkedPlatforms.indexOf(platform);
      if (index >= 0) {
        this.checkedPlatforms.splice(index, 1);
      }
    }
  }

  isGenreChecked(genre: string): boolean {
    return this.checkedGenres.includes(genre);
  }

  onGenreCheckboxChange(event: any, genre: string) {
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
