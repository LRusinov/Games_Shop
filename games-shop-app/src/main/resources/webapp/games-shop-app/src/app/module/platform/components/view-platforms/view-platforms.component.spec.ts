import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPlatformsComponent } from './view-platforms.component';

describe('ViewPlatformsComponent', () => {
  let component: ViewPlatformsComponent;
  let fixture: ComponentFixture<ViewPlatformsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPlatformsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewPlatformsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
