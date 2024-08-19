import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminDashboardService } from '../admin-dashboard/admin-dashboard.service';
import { Employee } from '../../model/employee.model';
import { FormBuilder, FormControl, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { newEmployee } from '../../model/newemployee.model';


@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css',
  standalone: true,
  imports: [MatFormFieldModule, MatSelectModule, FormsModule, ReactiveFormsModule, CommonModule],
})


export class CreateEmployeeComponent {

  errorMessage: string = '';
  successMessage: string = '';
  employeeForm: FormGroup;
  
  roles = new FormControl('');
  roleList: string[] = ['EMPLOYEE', 'MANAGER', 'ADMIN'];


  employee: newEmployee = {
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


  ngOnInit(): void {
  }

  createNewEmployee(): void{
    if (this.employeeForm.valid) {
      this.createEmployee(this.employeeForm.value);
    } else {
      console.log('Form is invalid');
    }
  }

  createEmployee(employeeData: newEmployee): void {
    console.log(JSON.stringify(employeeData));
    this.adminDashboardService.createEmployee(employeeData).subscribe(
      (response) => {
        this.successMessage = 'Employee created successfully.';
      },
      (error) => {
        this.errorMessage = 'Some Error Occured.';
      }
    );
  }
}
