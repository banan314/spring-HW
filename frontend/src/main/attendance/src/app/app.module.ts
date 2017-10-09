import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { FooterComponent } from './views/footer/footer.component';
import { ActivityListComponent } from './views/activity-list.component';
import { AttendanceListComponent } from './views/attendance-list.component';

import { RoutingModule } from './routing/routing.module';
import { NewStudentComponent } from './views/new-student/new-student.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { NewActivityComponent } from './views/new-activity/new-activity.component';
import {LogInService} from "./services/log-in.service";
import { LogInComponent } from './log-in/log-in.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    ActivityListComponent,
    AttendanceListComponent,
    NewStudentComponent,
    NavBarComponent,
    NewActivityComponent,
    LogInComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RoutingModule
  ],
  providers: [LogInService],
  bootstrap: [AppComponent]
})
export class AppModule { }
