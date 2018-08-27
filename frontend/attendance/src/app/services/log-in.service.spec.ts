import {TestBed, inject, async} from '@angular/core/testing';

import { LogInService } from './log-in.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {UserDTO} from '../dto/user-dto';

describe('LogInService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ LogInService ]
    });
  });

  it('should be created', inject([LogInService], (service: LogInService) => {
    expect(service).toBeTruthy();
  }));

  it('test user successfully logs in', async(inject([LogInService], (service: LogInService) => {
    spyOn(service, 'login').and.callThrough();

    service.login('test', 'test').subscribe(resp => {
      expect(resp.ok).toBe(false);
    });
  })));
});
