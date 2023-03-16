import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentProduitComponent } from './vent-produit.component';

describe('VentProduitComponent', () => {
  let component: VentProduitComponent;
  let fixture: ComponentFixture<VentProduitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VentProduitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VentProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
