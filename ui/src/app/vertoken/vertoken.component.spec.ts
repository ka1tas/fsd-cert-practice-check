import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VertokenComponent } from './vertoken.component';

describe('VertokenComponent', () => {
  let component: VertokenComponent;
  let fixture: ComponentFixture<VertokenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VertokenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VertokenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
