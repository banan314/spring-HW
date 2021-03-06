import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

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
import {CookieService} from 'ngx-cookie-service';

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
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    RegisterService,
    ActivityService,
    UserService,
    LogInService,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
