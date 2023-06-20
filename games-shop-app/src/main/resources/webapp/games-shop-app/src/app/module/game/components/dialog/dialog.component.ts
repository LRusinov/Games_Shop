import { Component, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { GameService } from '../../services/game.service';
import { Game } from 'src/app/model/Game';
import { CreateGameComponent } from '../create-game/create-game.component';
import { PlatformService } from 'src/app/module/platform/services/platform.service';
import { GenreService } from 'src/app/module/genre/services/genre.service';
import { PublisherService } from 'src/app/module/publisher/services/publisher.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
export interface DialogData {
  animal: string;
}
@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css'],
})
export class DialogComponent extends CreateGameComponent {
  constructor(
    router: Router,
    private readonly snackBar_: MatSnackBar,
    private readonly platformService_: PlatformService,
    private readonly genreService_: GenreService,
    private readonly publisherService_: PublisherService,
    private readonly gameService_: GameService,
    private readonly formBuilder_: FormBuilder,
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public game: Game
  ) {
    super(
      router,
      snackBar_,
      platformService_,
      genreService_,
      publisherService_,
      gameService_,
      formBuilder_
    );
  }

  override ngOnInit(): void {
    this.initHelper();
    this.form = this.formBuilder.group({
      name: [
        { value: this.game.name, disabled: true },
        [Validators.required, Validators.maxLength(50)],
      ],
      price: [
        this.game.price,
        [
          Validators.required,
          Validators.pattern('^\\d{1,4}$|(?=^.{1,5}$)^\\d+.\\d{0,2}$'),
        ],
      ],
      description: [this.game.description, [Validators.required]],
      releaseDate: [this.game.releaseDate, [Validators.required]],
      publisher: [this.game.publisher, [Validators.required]],
      pictureUrl: [this.game.pictureUrl, [Validators.required]],
    });
    this.checkedPlatforms = this.game.platforms;
    this.checkedGenres = this.game.genres;
  }

  override onSubmit(): void {
    if (this.form.invalid) {
      return;
    }
    this.gameService
      .editGame(
        this.name?.value,
        this.price?.value,
        this.checkedPlatforms,
        this.checkedGenres,
        this.description?.value,
        this.releaseDate?.value,
        this.publisher?.value,
        this.pictureUrl?.value
      )
      .subscribe();
    this.dialogRef.close();
    // window.location.reload();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
