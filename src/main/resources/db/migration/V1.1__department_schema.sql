CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_description VARCHAR(255) NOT NULL
);

ALTER TABLE employee
    ADD department_id SERIAL REFERENCES department(id);
