import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManagerDashboardComponent } from './components/manager-dashboard/manager-dashboard.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { EditEmployeeComponent } from './components/edit-employee/edit-employee.component';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { LeaveApproveComponent } from './components/leave-approve/leave-approve.component';
import { LeaveRequestComponent } from './components/leave-request/leave-request.component';
import { LoginComponent } from './components/login/login.component';
import { ServiceComponent } from './components/service/service.component';

const routes: Routes = [
  {
    path: 'manager-dashboard',
    component: ManagerDashboardComponent,
    pathMatch: 'full'
  },
{
  path: 'service',
      component: ServiceComponent,
      pathMatch: 'full'
 },

  {
    path: 'employee-dashboard',
    component: EmployeeDashboardComponent,
    pathMatch: 'full'
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    pathMatch: 'full'
  },
  {
    path: 'edit-employee/:id',
    component: EditEmployeeComponent,
    pathMatch: 'full'
  },
  {
    path: 'create-employee',
    component: CreateEmployeeComponent,
  },
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path : 'approve-leave',
    component: LeaveApproveComponent,
    pathMatch: 'full'
  },

  {
    path : 'leave-request',
    component: LeaveRequestComponent,
    pathMatch: 'full'
  },
  {
    path : 'login',
    component: LoginComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
