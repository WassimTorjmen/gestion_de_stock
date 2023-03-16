import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProduitParRegionComponent } from './produit-par-region.component';

describe('ProduitParRegionComponent', () => {
  let component: ProduitParRegionComponent;
  let fixture: ComponentFixture<ProduitParRegionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProduitParRegionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProduitParRegionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
