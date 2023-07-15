import {inject, TestBed} from '@angular/core/testing';

import {HttpClient, HttpHandler} from '@angular/common/http';
import {DatabaseService} from './database.service';

describe('DatabaseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DatabaseService, HttpClient, HttpHandler]
    });
  });

  it('should be created', inject([DatabaseService], (service: DatabaseService) => {
    expect(service).toBeTruthy();
  }));
});
