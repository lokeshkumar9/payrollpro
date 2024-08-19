import { Component, OnInit } from '@angular/core';
import { ManagerDashboardService } from './manager-dashboard.service';
import { LeaveRequest } from '../../model/leave-request.model';
import {NotificationResponse} from '../../model/notification-response'
import { status } from '../../model/request-status.model';
import { Leave } from '../../model/leave.model';
import {   FormBuilder, FormControl, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrls: ['./manager-dashboard.component.css']
})
export class ManagerDashboardComponent implements OnInit {

  leaveRequestCount: number = 0;
  remainingLeaves:number=0;
  lwpCount:number=0;
  errorMessage: string = '';
  leaveRequests: LeaveRequest[] = [];
  leave: Partial<Leave> = {};


  constructor(private managerDashboardService: ManagerDashboardService ){}

  ngOnInit(): void {

    this.fetchLeaveRequestCount();
    this.fetchLeaveRequests();

    this.leave = {
      employeeId: undefined,
      startDate: new Date(),
      endDate: new Date(),
      managerId: undefined
    };
  }

  fetchLeaveRequestCount() {
    this.managerDashboardService.getLeaveRequestCount(2).subscribe(
      (response) => {
        console.log(response);
        this.leaveRequestCount = response.totalLeaves;
        this.remainingLeaves = response.remainingLeaves;
        this.lwpCount = response.lwp;
        this.errorMessage = '';
      },
      (error) => {
        this.leaveRequestCount = 0;
        this.errorMessage = 'Error fetching leave request count.';
      }
    );
  }

  fetchLeaveRequests() {
    // Replace '1' with the actual manager ID
    this.managerDashboardService.fetchLeaveRequests(1001).subscribe(
      (response) => {
        this.leaveRequests = response;
        console.log('Full Response:', response);
      },
      (error) => {
        console.error('Error fetching leave requests:', error);
      }
    );
  }

  approveLeave(leaveRequestId: number , employeeId: number) {
    console.log("Approve function Enetred")

    if (leaveRequestId) {
      const notificationResponseDto: NotificationResponse = {
        leaveRequestId: leaveRequestId,
        status: status.APPROVED,
        employeeId: employeeId
      };

      this.managerDashboardService.approveLeave(notificationResponseDto).subscribe(
        (response) => {
          console.log(response.message);
          // Refresh the leave requests
          this.fetchLeaveRequests();
        },
        (error) => {
          console.error('Error approving leave:', error);
        }
      );
    }
  }

  declineLeave(leaveRequestId: number , employeeId: number) {
    if (leaveRequestId) {
      const notificationResponseDto: NotificationResponse = {
        leaveRequestId: leaveRequestId,
        status: status.DECLINED,
        employeeId: employeeId
      };

      this.managerDashboardService.approveLeave(notificationResponseDto).subscribe(
        (response) => {
          console.log(response.message);
          // Refresh the leave requests
          this.fetchLeaveRequests();
        },
        (error) => {
          console.error('Error declining leave:', error);
        }
      );
    }
  }

  
    onSubmit(){
      console.log('Leave :', this.leave);
      if(this.leave.startDate && this.leave.endDate){
        this.managerDashboardService.createLeaveRequest(this.leave).subscribe(
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