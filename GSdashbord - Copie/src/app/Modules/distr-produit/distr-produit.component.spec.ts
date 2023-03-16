import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistrProduitComponent } from './distr-produit.component';

describe('DistrProduitComponent', () => {
  let component: DistrProduitComponent;
  let fixture: ComponentFixture<DistrProduitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DistrProduitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DistrProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
