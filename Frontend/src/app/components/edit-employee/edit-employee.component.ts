import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminDashboardService } from '../admin-dashboard/admin-dashboard.service';
import { Employee } from '../../model/employee.model';
import { FormBuilder, FormControl, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrl: './edit-employee.component.css',
  standalone: true,
  imports: [MatFormFieldModule, MatSelectModule, FormsModule, ReactiveFormsModule, CommonModule],
})



export class EditEmployeeComponent {

  id : string = '';
  errorMessage: string = '';
  successMessage: string = '';
  employeeForm: FormGroup;
  
  roles = new FormControl('');
  roleList: string[] = ['EMPLOYEE', 'MANAGER', 'ADMIN'];


  employee: Employee = {
    employeeId: 0,
    firstName: '',
    lastName: '',
    email: '',
    jobTitle: '',
    managerId: 0,
    phoneNumber: '',
    department: '',
    employeeRoles: []
  };

  constructor(private route: ActivatedRoute, private adminDashboardService: AdminDashboardService, private fb: FormBuilder) {
    this.employeeForm = this.fb.group({
      employeeId: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      jobTitle: ['', Validators.required],
      managerId: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      department: ['', Validators.required],
      employeeRoles: [[], Validators.required],
    });
  }

  onSubmit(): void {
    if (this.employeeForm.valid) {
      console.log(this.employeeForm.value);
    } else {
      console.log('Form is not valid');
    }
  }

  setDefaultInputs(): void {
    this.employeeForm = this.fb.group({
      employeeId: [this.employee.employeeId, Validators.required],
      firstName: [this.employee.firstName, Validators.required],
      lastName: [this.employee.lastName, Validators.required],
      email: [this.employee.email, [Validators.required, Validators.email]],
      jobTitle: [this.employee.jobTitle, Validators.required],
      managerId: [this.employee.managerId, Validators.required],
      phoneNumber: [this.employee.phoneNumber, Validators.required],
      department: [this.employee.department, Validators.required],
      employeeRoles: [this.employee.employeeRoles.map((role)=> {
        return role.toString();
      }), Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = String(params.get('id') ? params.get('id') : '') 
      if(this.id!= ''){
        this.getEmployeeById(this.id);
      }
    });
  }

  updateData(): void{
    if (this.employeeForm.valid) {
      this.updateEmployeeData(this.employeeForm.value);
    } else {
      console.log('Form is invalid');
    }
  }

  updateEmployeeData(employeeData: Employee): void {
    console.log("EmployeeData: ", JSON.stringify(employeeData));
    console.log("---------------------------")
    this.adminDashboardService.updateEmployeeById(employeeData.employeeId, employeeData).subscribe(
      (response) => {
        console.log(employeeData);
        this.successMessage = 'Employee updated successfully.';
      },
      (error) => {
        this.errorMessage = 'Some Error Occured.';
      }
    );
  }

  getEmployeeById(employeeId: string) {
    this.adminDashboardService.getEmployeeById(this.id).subscribe(
      (response) => {
        // console.log("Employee Data Fetched Successfully:  ", response)
        this.employee = response;
        this.setDefaultInputs();
      },
      (error) => {
        this.errorMessage = 'Error fetching employees.';
      }
    );
  }
}
