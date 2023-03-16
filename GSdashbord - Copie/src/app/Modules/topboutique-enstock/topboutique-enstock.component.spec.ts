import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopboutiqueEnstockComponent } from './topboutique-enstock.component';

describe('TopboutiqueEnstockComponent', () => {
  let component: TopboutiqueEnstockComponent;
  let fixture: ComponentFixture<TopboutiqueEnstockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopboutiqueEnstockComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TopboutiqueEnstockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
