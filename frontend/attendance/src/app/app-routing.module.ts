import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AttendanceListComponent} from './attendance-list/attendance-list.component';
import {ActivityListComponent} from './activity-list/activity-list.component';
import {NewStudentComponent} from './new-student/new-student.component';
import {LogInComponent} from './log-in/log-in.component';
import {RegisterComponent} from './register/register.component';
import {LandingComponent} from './landing/landing.component';

const routes: Routes = [
  {
    path: 'students',
    component: AttendanceListComponent,
  },
  {
    path: 'activities',
    component: ActivityListComponent,
  },
  {
    path: 'newstudent',
    component: NewStudentComponent,
  },
  {
    path: '',
    redirectTo: 'landing',
    pathMatch: 'full'
  },
  {
    path: 'landing',
    component: LandingComponent
  },
  {
    path: 'login',
    component: LogInComponent,
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
