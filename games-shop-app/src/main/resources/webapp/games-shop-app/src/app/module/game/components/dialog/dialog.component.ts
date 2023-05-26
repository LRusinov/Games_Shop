import { Component, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { GameService } from '../../services/game.service';
import { Game } from 'src/app/model/Game';
import { CreateGameComponent } from '../create-game/create-game.component';
import { PlatformService } from 'src/app/module/platform/services/platform.service';
import { GenreService } from 'src/app/module/genre/services/genre.service';
import { PublisherService } from 'src/app/module/publisher/services/publisher.service';
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
    private readonly platformService_: PlatformService,
    private readonly genreService_: GenreService,
    private readonly publisherService_: PublisherService,
    private readonly gameService_: GameService,
    private readonly formBuilder_: FormBuilder,
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public game: Game
  ) {
    super(
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
      name: [{ value: this.game.name, disabled: true }, [Validators.required]],
      price: [this.game.price, [Validators.required]],
      platform: [this.game.platform, [Validators.required]],
      description: [this.game.description, [Validators.required]],
      releaseDate: [this.game.releaseDate, [Validators.required]],
      publisher: [this.game.publisher, [Validators.required]],
      picture: [this.game.picture, [Validators.required]],
      genres: this.checkedGenres,
    });
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
        this.platform?.value,
        this.checkedGenres,
        this.description?.value,
        this.releaseDate?.value,
        this.publisher?.value,
        this.picture?.value
      )
      .subscribe();
    this.dialogRef.close();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
