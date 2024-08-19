create TABLE if NOT EXISTS `salary` (
    `salary_id` int AUTO_INCREMENT PRIMARY KEY,
    `base_salary` int NOT NULL,
    `hra` int NOT NULL,
    `benefits` int NOT NULL,
    `employee_id` int NOT NULL

);

create TABLE if NOT EXISTS `payroll` (
    `employee_id` int AUTO_INCREMENT PRIMARY KEY,
    `payroll_id` int NOT NULL,
    `base_salary` int NOT NULL,
    `hra` int NOT NULL,
    `benefits` int NOT NULL,
    `net_salary` int NOT NULL,
    `deduction` int NOT NULL
);
