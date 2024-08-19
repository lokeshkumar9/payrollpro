CREATE TABLE IF NOT EXISTS `Employee` (
    `employee_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `phone_number` VARCHAR(15) NOT NULL,
    `job_title` VARCHAR(50) NOT NULL,
    `department` VARCHAR(50) NOT NULL,
    `manager_id` BIGINT,
    CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES Employee(employee_id)
);

CREATE TABLE IF NOT EXISTS `Employee_Role` (
    `employee_role_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `employee_id` BIGINT NOT NULL,
    `role` ENUM('Manager', 'Employee', 'Admin') NOT NULL,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

--CREATE TABLE IF NOT EXISTS `EmployeeRole` (
--    `employee_id` BIGINT NOT NULL,
--    `role_id` BIGINT NOT NULL,
--    PRIMARY KEY (`employee_id`, `role_id`),
--    CONSTRAINT fk_employee FOREIGN KEY (`employee_id`) REFERENCES `Employee`(`employee_id`),
--    CONSTRAINT fk_role FOREIGN KEY (`role_id`) REFERENCES `Role`(`role_id`)
--);
