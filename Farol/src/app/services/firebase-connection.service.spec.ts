import { TestBed } from '@angular/core/testing';

import { FirebaseConnectionService } from './firebase-connection.service';

describe('FirebaseConnectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FirebaseConnectionService = TestBed.get(FirebaseConnectionService);
    expect(service).toBeTruthy();
  });
});
