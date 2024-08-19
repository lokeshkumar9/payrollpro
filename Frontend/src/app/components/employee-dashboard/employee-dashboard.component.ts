import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LeaveRequest } from '../../model/leave-request.model';
import { EmployeeDashboardService } from './employee-dashboard.service';
import { SalaryInfo } from '../../model/salaryInfo.model';


@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.css']
})
export class EmployeeDashboardComponent implements OnInit {
  leaveRequestCount : number = 0;
  leaveRequest :LeaveRequest|null =null;
  errorMessage: string ='';
  salaryInfo: SalaryInfo | null = null;
  leaveRequest1: Partial<LeaveRequest> = {};

  constructor(
    private employeeDashboardService: EmployeeDashboardService
  ) {}

  ngOnInit() {

     this.getLeave();
     this.getSalary();
     this.leaveRequest1 = {
       employeeId: undefined,
       startDate: new Date(),
       endDate: new Date(),
       managerId: undefined
     };

  }

   getLeave(){
    this.employeeDashboardService.getLeave(1).subscribe(
      (response) =>{
        console.log("leave data fetch successfully: ",response)
        this.leaveRequest = response
      },
      (error)=>{
        // this.leaveRequest=null;
        this.errorMessage='error fetching leaves';
      }
    );
   }

   getSalary(){
    this.employeeDashboardService.getSalary(1).subscribe(
      (response) =>{
        console.log("salary data fetch successfully: ",response)
        this.salaryInfo = response
      },
      (error)=>{
        // this.leaveRequest=null;
        this.errorMessage='error fetching salary info';
      }
    );
   }

  onSubmit(){
    console.log('Leave request:', this.leaveRequest1);
    if(this.leaveRequest1.startDate && this.leaveRequest1.endDate){
      this.employeeDashboardService.submitLeaveRequest(this.leaveRequest1).subscribe(
        (response) => {
          console.log('Leave request submitted successfully:', response);
        },
        (error) => {
          console.error('Error submitting leave request:', error);
        }
      );
    }
  }
}
