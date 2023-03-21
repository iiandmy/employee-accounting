CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    passwordHash VARCHAR(255) NOT NULL
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE permission (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE employee_role (
    employee_id SERIAL NOT NULL,
    role_id SERIAL NOT NULL,
    PRIMARY KEY (employee_id, role_id),
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee (id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE role_permission (
    role_id SERIAL NOT NULL,
    permission_id SERIAL NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role (id),
    CONSTRAINT fk_permission FOREIGN KEY (permission_id) REFERENCES permission (id)
);