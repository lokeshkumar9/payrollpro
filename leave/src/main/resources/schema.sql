create TABLE if NOT EXISTS `leave` (
    `leave_id` int AUTO_INCREMENT PRIMARY KEY,
    `employee_id` int NOT NULL,
    `total_leaves` int,
    `remaining_leaves` int,
    `lwp` int,
    `created_at` date NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(30) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `leave_request` (
    `leave_request_id` int AUTO_INCREMENT PRIMARY KEY,
    `employee_id` int NOT NULL,
    `start_date` date NOT NULL,
    `end_date` date NOT NULL,
    `remaining_leaves` int,
    `lwp` int,
    `status` ENUM('APPROVED', 'PENDING', 'DECLINED') NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(30) DEFAULT NULL
);