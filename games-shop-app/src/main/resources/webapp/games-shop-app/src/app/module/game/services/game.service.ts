import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game } from 'src/app/model/Game';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root',
})
export class GameService {
  constructor(private readonly httpClient: HttpClient) {}

  getGames() {
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
        withCredentials: true,
      }
    );
  }
  deleteGame(name: string): Observable<Game> {
    return this.httpClient.delete<Game>(
      `http://localhost:8080/games-shop/games/${name}`,
      { withCredentials: true }
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
        withCredentials: true,
      }
    );
  }
}
