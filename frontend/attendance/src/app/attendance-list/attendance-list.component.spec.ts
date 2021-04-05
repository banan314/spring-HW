import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';

import {AttendanceListComponent} from './attendance-list.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {UserService} from '../services/user.service';

describe('AttendanceListComponent', () => {
  let component: AttendanceListComponent;
  let fixture: ComponentFixture<AttendanceListComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule, FormsModule, HttpClientTestingModule ],
      declarations: [ AttendanceListComponent ],
      providers: [ UserService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttendanceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
