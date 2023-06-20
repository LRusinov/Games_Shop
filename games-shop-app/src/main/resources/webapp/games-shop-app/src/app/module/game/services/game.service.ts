import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game } from 'src/app/model/Game';
@Injectable({
  providedIn: 'root',
})
export class GameService {
  constructor(private readonly httpClient: HttpClient) {}

  getGames(): Observable<Game[]> {
    return this.httpClient.get<Game[]>(
      'http://localhost:8080/games-shop/games'
    );
  }

  createGame(
    name: string,
    price: number,
    platforms: string[],
    genres: string[],
    description: string,
    releaseDate: Date,
    publisher: string,
    pictureUrl: string
  ): Observable<Game> {
    return this.httpClient.post<Game>(
      'http://localhost:8080/games-shop/games',
      {
        name,
        price,
        platforms,
        genres,
        description,
        releaseDate,
        publisher,
        pictureUrl,
      }
    );
  }
  deleteGame(name: string): Observable<Game> {
    return this.httpClient.delete<Game>(
      `http://localhost:8080/games-shop/games/${name}`
    );
  }

  editGame(
    name: string,
    price: number,
    platforms: string[],
    genres: string[],
    description: string,
    releaseDate: Date,
    publisher: string,
    pictureUrl: string
  ): Observable<Game> {
    return this.httpClient.put<Game>(
      `http://localhost:8080/games-shop/games/${name}`,
      {
        name,
        price,
        platforms,
        genres,
        description,
        releaseDate,
        publisher,
        pictureUrl,
      }
    );
  }
}
