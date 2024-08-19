import { status } from "./request-status.model";

export interface NotificationResponse {
    employeeId: number;
    leaveRequestId: number;
    status: status;
}

 