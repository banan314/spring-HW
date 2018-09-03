import {TestBed, inject, async} from '@angular/core/testing';

import { RegisterService } from './register.service';
import {HttpClient, HttpClientModule, HttpHandler} from '@angular/common/http';
import {UserDTO} from '../dto/user-dto';
import {BrowserModule} from '@angular/platform-browser';

describe('RegisterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [BrowserModule, HttpClientModule],
      providers: [RegisterService]
    });
  });

  it('should be created', inject([RegisterService], (service: RegisterService) => {
    expect(service).toBeTruthy();
  }));

  it('should register a valid user', async(inject([RegisterService], (service: RegisterService) => {
    const userDTO: UserDTO = {
      firstName: 'John',
      lastName: 'Kowalsky',
      username: 'jkow',
      email: 'jkow@gmail.com',
      password: 'pass',
      matchingPassword: 'pass'
    };

    spyOn(service, 'registerUser').and.callThrough();

    service.registerUser(userDTO).subscribe(resp => {
      expect(resp.ok).toBe(true);
    });
  })));
});
