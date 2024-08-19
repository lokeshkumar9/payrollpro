import { status } from "./request-status.model";

export interface LeaveRequest {
    employeeId: number;
    leaveRequestId:number;
    startDate: Date;
    endDate: Date;
    remainingLeaves: number;
    lwp: number;
    managerId: number;
    totalLeaves:number;
    status: status;
}

 