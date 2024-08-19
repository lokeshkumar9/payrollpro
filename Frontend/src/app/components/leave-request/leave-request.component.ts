import { Component } from '@angular/core';
import { ManagerDashboardService } from '../manager-dashboard/manager-dashboard.service';
import { Leave } from '../../model/leave.model';

@Component({
  selector: 'app-leave-request',
  templateUrl: './leave-request.component.html',
  styleUrl: './leave-request.component.css'
})
export class LeaveRequestComponent {

  leave: Partial<Leave> = {};

  constructor(private managerDashboardService: ManagerDashboardService ){}

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
