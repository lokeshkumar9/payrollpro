import { UserRole } from "./user-role.model";

export interface Employee {
    employeeId: number;
    firstName: string;
    lastName: string;
    email: string;
    jobTitle: string;
    managerId: number;
    phoneNumber: string;
    department: string;
    employeeRoles: UserRole[];
}