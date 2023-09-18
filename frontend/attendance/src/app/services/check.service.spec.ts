import {inject, TestBed} from '@angular/core/testing';

// import {HttpClient, HttpHandler} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {CheckService} from "./check.service";

describe('CheckService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [CheckService]
    });
  });

  it('should be created', inject([CheckService], (service: CheckService) => {
    expect(service).toBeTruthy();
  }));
});
