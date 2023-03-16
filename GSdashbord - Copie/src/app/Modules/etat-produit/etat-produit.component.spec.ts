import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtatProduitComponent } from './etat-produit.component';

describe('EtatProduitComponent', () => {
  let component: EtatProduitComponent;
  let fixture: ComponentFixture<EtatProduitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EtatProduitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EtatProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
