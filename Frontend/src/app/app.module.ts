import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ManagerDashboardComponent } from './components/manager-dashboard/manager-dashboard.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { EditEmployeeComponent } from './components/edit-employee/edit-employee.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule, MatFormField, MatLabel } from '@angular/material/input';
import { MatOption } from '@angular/material/core';
import { provideHttpClient, withFetch } from "@angular/common/http";
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { LeaveApproveComponent } from './components/leave-approve/leave-approve.component';
import { LeaveRequestComponent } from './components/leave-request/leave-request.component';
import { ServiceComponent } from './components/service/service.component';


@NgModule({
  declarations: [
    AppComponent,
    ManagerDashboardComponent,
    AdminDashboardComponent,
    EmployeeDashboardComponent,
    HomeComponent,
    ServiceComponent,
    LeaveApproveComponent,
    LeaveRequestComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatFormField,
    MatInputModule,
    MatLabel,
    MatOption
  ],
  exports:[
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
