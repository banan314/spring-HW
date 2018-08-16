import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ActivityListComponent } from './activity-list/activity-list.component';
import { NewStudentComponent } from './new-student/new-student.component';
import { NewActivityComponent } from './new-activity/new-activity.component';
import { FooterComponent } from './footer/footer.component';
import { AttendanceListComponent } from './attendance-list/attendance-list.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LogInComponent } from './log-in/log-in.component';

@NgModule({
  declarations: [
    AppComponent,
    ActivityListComponent,
    NewStudentComponent,
    NewActivityComponent,
    FooterComponent,
    AttendanceListComponent,
    NavBarComponent,
    LogInComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
