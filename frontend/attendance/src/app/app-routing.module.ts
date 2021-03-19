import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AttendanceListComponent} from './attendance-list/attendance-list.component';
import {ActivityListComponent} from './activity-list/activity-list.component';
import {NewStudentComponent} from './new-student/new-student.component';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {LogInComponent} from './log-in/log-in.component';
import {RegisterComponent} from "./register/register.component";
import {LoggedInAuthGuard} from './logged-in-auth-guard/logged-in-auth-guard';
import {AuthGuard} from './auth-guard/auth-guard';

const routes: Routes = [
  {
    path: 'students',
    component: AttendanceListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'activities',
    component: ActivityListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'newstudent',
    component: NewStudentComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '',
    component: NavBarComponent
  },
  {
    path: 'login',
    component: LogInComponent,
    canActivate: [LoggedInAuthGuard]
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
