import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchasedbookComponent } from './purchasedbook.component';

describe('PurchasedbookComponent', () => {
  let component: PurchasedbookComponent;
  let fixture: ComponentFixture<PurchasedbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchasedbookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchasedbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
