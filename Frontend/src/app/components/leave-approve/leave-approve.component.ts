import { Component } from '@angular/core';
import { LeaveRequest } from '../../model/leave-request.model';
import { ManagerDashboardService } from '../manager-dashboard/manager-dashboard.service';
import { NotificationResponse } from '../../model/notification-response';
import { status } from '../../model/request-status.model';

@Component({
  selector: 'app-leave-approve',
  templateUrl: './leave-approve.component.html',
  styleUrl: './leave-approve.component.css'
})
export class LeaveApproveComponent {

  leaveRequests: LeaveRequest[] = [];
  status = status;
  constructor(private managerDashboardService: ManagerDashboardService){
    this.fetchLeaveRequests();
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

}
