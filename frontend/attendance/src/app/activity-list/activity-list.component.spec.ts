import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ActivityListComponent} from './activity-list.component';
import {FormsModule} from '@angular/forms';
import {RouterTestingModule} from '@angular/router/testing';
import {ActivityService} from '../services/activity.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('ActivityListComponent', () => {
  let component: ActivityListComponent;
  let fixture: ComponentFixture<ActivityListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityListComponent ],
      imports: [ RouterTestingModule, FormsModule, HttpClientTestingModule ],
      providers: [ ActivityService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
