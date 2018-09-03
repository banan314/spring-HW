import { TestBed, inject } from '@angular/core/testing';

import {HttpClient, HttpHandler} from '@angular/common/http';
import {DatabaseService} from './database.service';
import {RouterTestingModule} from '@angular/router/testing';

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
