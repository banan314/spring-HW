import {TestBed, inject, async} from '@angular/core/testing';

import { LogInService } from './log-in.service';
import {CookieService} from 'ngx-cookie-service';
import {HttpClient, HttpClientModule, HttpHandler} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';

describe('LogInService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ BrowserModule, HttpClientModule ],
      providers: [ LogInService, CookieService ]
    });
  });

  it('should be created', inject([LogInService], (service: LogInService) => {
    expect(service).toBeTruthy();
  }));

  it('test user successfully logs in', async(inject([LogInService], (service: LogInService) => {
    spyOn(service, 'login').and.callThrough();

    service.login('test', 'test').subscribe(resp => {
      expect(resp.ok).toBe(true);
    }, (error) => {
      console.log(error);
    });
  })));
});
