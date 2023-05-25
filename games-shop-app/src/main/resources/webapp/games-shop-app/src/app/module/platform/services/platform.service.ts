import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Genre } from 'src/app/model/Genre';
import { Platform } from 'src/app/model/Platform';

@Injectable({
  providedIn: 'root'
})
export class PlatformService {

  constructor(private readonly httpClient: HttpClient) {}

  getPlatforms(): Observable<Platform[]> {
    return this.httpClient.get<Platform[]>(
      'http://localhost:8080/games-shop/platforms'
    );
  }

  createPlatform(
    name: string,
  ): Observable<Platform> {
    return this.httpClient.post<Platform>(
      'http://localhost:8080/games-shop/platforms',
      {
        name,
      }
    );
  }
}
