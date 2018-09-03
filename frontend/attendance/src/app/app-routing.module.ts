import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AttendanceListComponent} from './attendance-list/attendance-list.component';
import {ActivityListComponent} from './activity-list/activity-list.component';
import {NewStudentComponent} from './new-student/new-student.component';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {LogInComponent} from './log-in/log-in.component';
import {RegisterComponent} from "./register/register.component";

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
  },
  {
    path: 'login',
    component: LogInComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
