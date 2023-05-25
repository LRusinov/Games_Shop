import { Component, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Publisher } from 'src/app/model/Publisher';
import { PublisherService } from '../../services/publisher.service';

@Component({
  selector: 'app-view-publisher',
  templateUrl: './view-publisher.component.html',
  styleUrls: ['./view-publisher.component.css']
})
export class ViewPublisherComponent {
publishers:Publisher[]=[];
displayedColumns:string[]=[    'name','yearOfCreation','description','logoPictureUrl'];
public dataSource = new MatTableDataSource<Publisher>();
  @ViewChild(MatSort)
  sort: MatSort = new MatSort();

  constructor(
    private readonly publisherService: PublisherService,
  ) {}

  ngOnInit(): void {
    this.publisherService.getPublishers().subscribe((response) => {
      this.publishers = response;
      this.dataSource.data = this.publishers;
      this.dataSource.sort = this.sort;
    });
  }
}
