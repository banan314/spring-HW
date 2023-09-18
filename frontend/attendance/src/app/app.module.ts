import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ActivityListComponent} from './activity-list/activity-list.component';
import {NewStudentComponent} from './new-student/new-student.component';
import {NewActivityComponent} from './new-activity/new-activity.component';
import {FooterComponent} from './footer/footer.component';
import {AttendanceListComponent} from './attendance-list/attendance-list.component';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {LogInComponent} from './log-in/log-in.component';
import {RegisterComponent} from './register/register.component';
import {RegisterService} from './services/register.service';
import {ActivityService} from './services/activity.service';
import {UserService} from './services/user.service';
import {LogInService} from './services/log-in.service';
import {BackendService} from './services/backend.service';
import {DatabaseService} from './services/database.service';
import {RouterModule} from '@angular/router';
import {AuthInterceptorService} from './services/auth-interceptor.service';
import { LandingComponent } from './landing/landing.component';
import { AppHeaderComponent } from './app-header/app-header.component';
import { ActivityCardComponent } from './activity-list/activity-card/activity-card.component';
import {CheckService} from "./services/check.service";

const httpInterceptors = [{
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptorService,
  multi: true
}];

@NgModule({
  declarations: [
    AppComponent,
    ActivityListComponent,
    NewStudentComponent,
    NewActivityComponent,
    FooterComponent,
    AttendanceListComponent,
    NavBarComponent,
    LogInComponent,
    RegisterComponent,
    LandingComponent,
    AppHeaderComponent,
    ActivityCardComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    BackendService,
    DatabaseService,
    RegisterService,
    LogInService,
    ActivityService,
    CheckService,
    UserService,
    httpInterceptors
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
