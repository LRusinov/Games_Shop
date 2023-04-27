import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Game } from 'src/app/model/Game';
@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private readonly httpClient: HttpClient) { }

  getGames():Observable<Game[]>{
    return this.httpClient.get<Game[]>('http://localhost:8080/games-shop/games');
  }
}
