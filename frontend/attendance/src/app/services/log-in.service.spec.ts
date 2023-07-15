import {inject, TestBed, waitForAsync} from '@angular/core/testing';

import {LogInService} from './log-in.service';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';

describe('LogInService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ BrowserModule, HttpClientModule ],
      providers: [ LogInService ]
    });
  });

  it('should be created', inject([LogInService], (service: LogInService) => {
    expect(service).toBeTruthy();
  }));

  it('test user successfully logs in', waitForAsync(inject([LogInService], (service: LogInService) => {
    spyOn(service, 'login').and.callThrough();

    service.login('test', 'test').subscribe(resp => {
      expect(resp.ok).toBe(true);
    }, (error) => {
      console.log(error);
    });
  })));
});
