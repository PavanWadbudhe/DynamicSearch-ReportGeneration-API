import { TestBed } from '@angular/core/testing';

import { InsuranceOperationService } from './insurance-operation.service';

describe('InsuranceOperationService', () => {
  let service: InsuranceOperationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InsuranceOperationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
