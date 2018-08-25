import {inject, TestBed} from '@angular/core/testing';

import {ActivityService} from './activity.service';
import {HttpClient, HttpHandler} from '@angular/common/http';

describe('ActivityService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ActivityService, HttpClient, HttpHandler]
    });
  });

  it('should be created', inject([ActivityService], (service: ActivityService) => {
    expect(service).toBeTruthy();
  }));
});
