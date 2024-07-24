import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceComponentComponent } from './insurance-component.component';

describe('InsuranceComponentComponent', () => {
  let component: InsuranceComponentComponent;
  let fixture: ComponentFixture<InsuranceComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InsuranceComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InsuranceComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
