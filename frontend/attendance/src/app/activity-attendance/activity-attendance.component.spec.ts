import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityAttendanceComponent } from './activity-attendance.component';

describe('ActivityAttendanceComponent', () => {
  let component: ActivityAttendanceComponent;
  let fixture: ComponentFixture<ActivityAttendanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivityAttendanceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
