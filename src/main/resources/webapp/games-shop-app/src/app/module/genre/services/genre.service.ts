import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Genre } from 'src/app/model/Genre';
import { Publiher } from 'src/app/model/Publisher';

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(private readonly httpClient: HttpClient) {}

  getGenres(): Observable<Genre[]> {
    return this.httpClient.get<Genre[]>(
      'http://localhost:8080/games-shop/genres'
    );
  }

  createGenre(
    name: string,
  ): Observable<Genre> {
    return this.httpClient.post<Genre>(
      'http://localhost:8080/games-shop/genres',
      {
        name,
      }
    );
  }
}
