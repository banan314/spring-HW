import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { FooterComponent } from './views/footer/footer.component';
import { ActivityListComponent } from './views/activity-list.component';
import { AttendanceListComponent } from './views/attendance-list.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    ActivityListComponent,
    AttendanceListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
