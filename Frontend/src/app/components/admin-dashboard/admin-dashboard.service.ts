import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Employee } from '../../model/employee.model';
import { responseData } from '../../model/response.model';
import { newEmployee } from '../../model/newemployee.model';
@Injectable({
  providedIn: 'root'
})

export class AdminDashboardService {
  private apiUrl = 'http://localhost:8001/api';

  constructor(private http: HttpClient) {}


  getAllEmployees() : Observable<Employee[]> {{
    return this.http.get<Employee[]>(`${this.apiUrl}/fetch-all`).pipe(
      catchError(this.handleError)
    );
  }}

  getEmployeeById(employeeId: string): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/${employeeId}`).pipe(
      catchError(this.handleError)
    );
  }

  updateEmployeeById(employeeId: number, employee: Employee): Observable<responseData> {
    return this.http.put<responseData>(`${this.apiUrl}/update/${employeeId}`, employee).pipe(
      catchError(this.handleError)
    );
  }

  createEmployee(employee: newEmployee): Observable<responseData> {
    return this.http.post<responseData>(`${this.apiUrl}/create`, employee).pipe(
      catchError(this.handleError)
    );
  }
  

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = '';
    if (error.status === 404) {
      errorMessage = `Employee with ID ${error.url?.split('/').pop()} not found.`;
    } else {
      errorMessage = `An unexpected error occurred: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}


//   fetchLeaveRequests(managerId: number): Observable<any> {
//      console.log("URL-->" , this.http.get<any>(`${this.apiUrl}/fetchleaverequest?managerId=${managerId}`));
//    return this.http.get<any>(`${this.apiUrl}/fetchleaverequest?managerId=${managerId}`);
//   }

//   approveLeave(notificationResponseDto: NotificationResponse): Observable<any> {
//     return this.http.post<any>(`${this.apiUrl}/approveleave`, notificationResponseDto);
//   }