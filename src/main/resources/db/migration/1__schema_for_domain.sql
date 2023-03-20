CREATE TABLE employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE permission (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE employee_role (
    employee_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (employee_id, role_id),
    CONSTRAINT fk_employee_role_employee FOREIGN KEY (employee_id) REFERENCES employee (id),
    CONSTRAINT fk_employee_role_role FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE role_permission (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_role_permission_role FOREIGN KEY (role_id) REFERENCES role (id),
    CONSTRAINT fk_role_permission_permission FOREIGN KEY (permission_id) REFERENCES permission (id)
);