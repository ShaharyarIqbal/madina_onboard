import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrayerTimeComponent } from './prayer-time.component';

describe('PrayerTimeComponent', () => {
  let component: PrayerTimeComponent;
  let fixture: ComponentFixture<PrayerTimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrayerTimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrayerTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
