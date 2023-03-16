import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopboutiqueVenduComponent } from './topboutique-vendu.component';

describe('TopboutiqueVenduComponent', () => {
  let component: TopboutiqueVenduComponent;
  let fixture: ComponentFixture<TopboutiqueVenduComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopboutiqueVenduComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TopboutiqueVenduComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
