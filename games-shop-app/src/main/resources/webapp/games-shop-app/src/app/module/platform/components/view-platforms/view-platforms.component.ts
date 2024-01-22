import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Platform } from 'src/app/model/Platform';
import { PlatformService } from '../../services/platform.service';

@Component({
  selector: 'app-view-platforms',
  templateUrl: './view-platforms.component.html',
  styleUrls: ['./view-platforms.component.css']
})
export class ViewPlatformsComponent implements OnInit{
  platforms: Platform[] = [];
  displayedColumns: string[] = ['name'];

  public dataSource = new MatTableDataSource<Platform>();
  @ViewChild(MatSort)
  sort: MatSort = new MatSort();

  constructor(
    private readonly platformService: PlatformService,
  ) {}

  ngOnInit(): void {
    this.platformService.getPlatforms().subscribe((response) => {
      this.platforms = response;
      this.dataSource.data = this.platforms;
      this.dataSource.sort = this.sort;
    });
  }
}
