import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Publisher } from 'src/app/model/Publisher';

@Injectable({
  providedIn: 'root',
})
export class PublisherService {
  constructor(private readonly httpClient: HttpClient) {}

  getPublishers(): Observable<Publisher[]> {
    return this.httpClient.get<Publisher[]>(
      'http://localhost:8080/games-shop/publishers'
    );
  }

  createPublisher(
    name: string,
    yearOfCreation: number,
    description: string,
    logoPictureUrl: string
  ): Observable<Publisher> {
    return this.httpClient.post<Publisher>(
      'http://localhost:8080/games-shop/publishers',
      {
        name,
        yearOfCreation,
        description,
        logoPictureUrl,
      }
    );
  }
}
