import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";

import { AttendanceListComponent } from '../views/attendance-list.component';
import { ActivityListComponent } from '../views/activity-list.component';
import { NewStudentComponent } from '../new-student/new-student.component';
import {AppComponent} from "../app.component";
import { NavBarComponent } from '../nav-bar/nav-bar.component';

const routes: Routes = [
  {
    path: 'students',
    component: AttendanceListComponent
  },
  {
    path: 'activities',
    component: ActivityListComponent
  },
  {
    path: 'newstudent',
    component: NewStudentComponent
  },
  {
    path: '',
    component: NavBarComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    [RouterModule.forRoot(routes)]
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class RoutingModule { }
