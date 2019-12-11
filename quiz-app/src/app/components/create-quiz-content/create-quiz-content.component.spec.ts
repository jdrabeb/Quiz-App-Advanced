import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateQuizContentComponent } from './create-quiz-content.component';

describe('CreateQuizContentComponent', () => {
  let component: CreateQuizContentComponent;
  let fixture: ComponentFixture<CreateQuizContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateQuizContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateQuizContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
