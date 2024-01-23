import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Game } from 'src/app/model/Game';
import { GameService } from '../../services/game.service';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { DialogComponent } from '../dialog/dialog.component';
import { ClientService } from 'src/app/module/client/services/client.service';
import { User } from 'src/app/model/Client';

@Component({
  selector: 'app-view-games',
  templateUrl: './view-games.component.html',
  styleUrls: ['./view-games.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition(
        'expanded <=> collapsed',
        animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')
      ),
    ]),
  ],
})
export class ViewGamesComponent implements OnInit {
  games: Game[] = [];
  columnsToDisplay = [
    'name',
    'price',
    'platform',
    'genres',
    'publisher',
    'releaseDate',
    'expand',
    'edit',
    'delete',
    'cart',
    'addGame',
  ];
  public dataSource = new MatTableDataSource<Game>();
  expandedElement: Game | null = null;
  @ViewChild(MatSort)
  sort: MatSort = new MatSort();
  user = new User();

  constructor(
    private readonly gameService: GameService,
    private readonly clientService: ClientService,
    public dialog: MatDialog
  ) {}
  ngOnInit(): void {
    this.gameService.getGames().subscribe((response) => {
      this.games = response;
      this.dataSource.data = this.games;
      this.dataSource.sort = this.sort;
    });
    this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
  }
  onDeleteClick(name: string): void {
    this.gameService.deleteGame(name).subscribe();
    window.location.reload();
  }

  onCartClick(name: string): void {
    this.clientService.addToShoppingCart(name, 'user').subscribe();
  }

  openDialog(game: Game): void {
    this.dialog.open(DialogComponent, {
      data: {
        name: game.name,
        price: game.price,
        platforms: game.platforms,
        description: game.description,
        releaseDate: game.releaseDate,
        publisher: game.publisher,
        pictureUrl: game.pictureUrl,
        genres: game.genres,
      },
    });
  }
}
