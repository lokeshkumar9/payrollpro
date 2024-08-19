import { Component, OnInit } from '@angular/core';
import { Employee } from '../../model/employee.model';
import { UserRole } from '../../model/user-role.model';
import { AdminDashboardService } from './admin-dashboard.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
})


export class AdminDashboardComponent implements OnInit{
  employees: Employee[] = []; // Placeholder data
  errorMessage: string = '';

  constructor(private adminDashboardService: AdminDashboardService, private router: Router) { }

  ngOnInit(): void {
    this.getAllEmployeesData();
  }

  getAllEmployeesData() {
    this.adminDashboardService.getAllEmployees().subscribe(
      (response) => {
        console.log("Employees Data Fetched Successfully:  ", response)
        this.employees = response
      },
      (error) => {
        this.employees = [];
        this.errorMessage = 'Error fetching employees.';
      }
    );
  }

     // navigation
    navigateToEditEmployee(id: number) {
      this.router.navigate([`/edit-employee/${id.toString()}`]);
    };

    goToCreateEmployee(){
      this.router.navigate(['/create-employee']);
      // console.log("Navigated to Create Employee Page");
    };
}
