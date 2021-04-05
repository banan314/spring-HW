import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';

import {RouterTestingModule} from '@angular/router/testing';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {FooterComponent} from './footer/footer.component';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule, FormsModule],
      declarations: [ AppComponent, FooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

  it(`should have as title 'attendance'`, waitForAsync(() => {
    console.log(component.title.toLowerCase());
    expect(component.title.toLowerCase()).toContain('attendance');
  }));
});
