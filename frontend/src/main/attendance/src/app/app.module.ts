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

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    ActivityListComponent,
    AttendanceListComponent,
    NewStudentComponent,
    NavBarComponent,
    NewActivityComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
