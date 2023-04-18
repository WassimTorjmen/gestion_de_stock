import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentePardistComponent } from './vente-pardist.component';

describe('VentePardistComponent', () => {
  let component: VentePardistComponent;
  let fixture: ComponentFixture<VentePardistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VentePardistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VentePardistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
