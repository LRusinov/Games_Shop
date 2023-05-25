import { Component, ViewChild } from '@angular/core';
import { GenreService } from '../../services/genre.service';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Genre } from 'src/app/model/Genre';

@Component({
  selector: 'app-view-genres',
  templateUrl: './view-genres.component.html',
  styleUrls: ['./view-genres.component.css']
})
export class ViewGenresComponent {
  genres: Genre[] = [];
  displayedColumns: string[] = ['name'];

  public dataSource = new MatTableDataSource<Genre>();
  @ViewChild(MatSort)
  sort: MatSort = new MatSort();

  constructor(
    private readonly genreService: GenreService,
  ) {}

  ngOnInit() {
    this.genreService.getGenres().subscribe((response) => {
      this.genres = response;
      this.dataSource.data = this.genres;
      this.dataSource.sort = this.sort;
    });
  }
}
