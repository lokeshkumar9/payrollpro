import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LeaveRequest} from '../../model/leave-request.model';
import {NotificationResponse} from '../../model/notification-response';
import { Leave } from '../../model/leave.model';
@Injectable({
  providedIn: 'root'
})
export class ManagerDashboardService {
  private apiUrl = 'http://localhost:8000/leave';

  constructor(private http: HttpClient) {}

  getLeaveRequestCount(employeeId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/viewLeaves?employeeId=${employeeId}`).pipe(
      catchError((error) => {
        console.error('Error fetching leave request count:', error);
        return throwError(error);
      })
    );
  }

  fetchLeaveRequests(managerId: number): Observable<any> {
     console.log("URL-->" , this.http.get<any>(`${this.apiUrl}/fetchleaverequest?managerId=${managerId}`));
   return this.http.get<any>(`${this.apiUrl}/fetchleaverequest?managerId=${managerId}`);
  }

  approveLeave(notificationResponseDto: NotificationResponse): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/approveleave`, notificationResponseDto);
  }

  createLeaveRequest(leave: Partial<Leave>): Observable<any> {
    const url = `${this.apiUrl}/leaveRequest`;
    console.log('Creating Leave Request with payload:', leave); // Debug: log payload
    return this.http.post<any>(url, leave);
  }
}