import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game } from 'src/app/model/Game';
import { Platform } from '@angular/cdk/platform';
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
    platform: string,
    genres: string[],
    description: string,
    releaseDate: Date,
    publisher: string,
    picture: string
  ): Observable<Game> {
    return this.httpClient.post<Game>(
      'http://localhost:8080/games-shop/games',
      {
        name,
        price,
        platform,
        genres,
        description,
        releaseDate,
        publisher,
        picture,
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
    platform: string,
    genres: string[],
    description: string,
    releaseDate: Date,
    publisher: string,
    picture: string
  ): Observable<Game> {
    return this.httpClient.put<Game>(
      `http://localhost:8080/games-shop/games/${name}`,
      {
        name,
        price,
        platform,
        genres,
        description,
        releaseDate,
        publisher,
        picture,
      }
    );
  }
}
