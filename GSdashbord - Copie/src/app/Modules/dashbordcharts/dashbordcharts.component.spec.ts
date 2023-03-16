import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashbordchartsComponent } from './dashbordcharts.component';

describe('DashbordchartsComponent', () => {
  let component: DashbordchartsComponent;
  let fixture: ComponentFixture<DashbordchartsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashbordchartsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashbordchartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
