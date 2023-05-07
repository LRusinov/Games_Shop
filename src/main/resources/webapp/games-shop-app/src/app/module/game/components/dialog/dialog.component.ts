import { Component, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { GameService } from '../../services/game.service';
import { Game } from 'src/app/model/Game';
export interface DialogData {
  animal: string;
}
@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})

export class DialogComponent {
  constructor(
    private readonly gameService: GameService,
    private readonly formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public game: Game) {}

    public editGameForm!: FormGroup;
    publishers: string[] = [];
    platforms: string[] = [];
    genres: string[] = [];
    checkedGenres: string[] = [];
    checked = false;
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
      this.editGameForm = this.formBuilder.group({
        name: [this.game.name, [Validators.required]],
        price: [this.game.price, [Validators.required]],
        platform: [this.game.platform, [Validators.required]],
        description: [this.game.description, [Validators.required]],
        releaseDate: [this.game.releaseDate, [Validators.required]],
        publisher: [this.game.publisher, [Validators.required]],
        picture: [this.game.picture, [Validators.required]],
        genres: this.checkedGenres
      });
      this.checkedGenres = this.game.genres;
    }
  
    get name() {
      return this.editGameForm.get('name');
    }
    get price() {
      return this.editGameForm.get('price');
    }
    get platform() {
      return this.editGameForm.get('platform');
    }
    get description() {
      return this.editGameForm.get('description');
    }
    get publisher() {
      return this.editGameForm.get('publisher');
    }
    get picture() {
      return this.editGameForm.get('picture');
    }
    get releaseDate() {
      return this.editGameForm.get('releaseDate');
    }
  
    onSubmit(): void {
      if (this.editGameForm.invalid) {
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
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isChecked(genre: string): boolean {
    return this.checkedGenres.includes(genre);
  }

  onCheckboxChange(event: any, genre: string) {
    if (this.checkedGenres.indexOf(genre)==-1) {
      console.log("AAA")
      this.checkedGenres.push(genre);
    } else {
      const index = this.checkedGenres.indexOf(genre);
      if (index >= 0) {
        this.checkedGenres.splice(index, 1);
      }
    }
  }
}
