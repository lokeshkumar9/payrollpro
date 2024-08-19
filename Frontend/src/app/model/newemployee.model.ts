import { UserRole } from "./user-role.model";

export interface newEmployee {
    firstName: string;
    lastName: string;
    email: string;
    jobTitle: string;
    managerId: number;
    phoneNumber: string;
    department: string;
    employeeRoles: UserRole[];
}