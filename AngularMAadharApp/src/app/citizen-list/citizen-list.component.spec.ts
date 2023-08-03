import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CitizenListComponent } from './citizen-list.component';

describe('CitizenListComponent', () => {
  let component: CitizenListComponent;
  let fixture: ComponentFixture<CitizenListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CitizenListComponent]
    });
    fixture = TestBed.createComponent(CitizenListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
