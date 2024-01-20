import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewShoppingCartComponent } from './view-shopping-cart.component';

describe('ViewShoppingCartComponent', () => {
  let component: ViewShoppingCartComponent;
  let fixture: ComponentFixture<ViewShoppingCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewShoppingCartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewShoppingCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
