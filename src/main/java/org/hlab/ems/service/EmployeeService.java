package org.hlab.ems.service;

import org.hlab.ems.model.Employee;

import java.util.List;

public interface EmployeeService {


    boolean createEmployee(Employee employee);

    boolean deleteEmployee(int id);

    Employee updateEmployee(Employee employee);

    List<Employee> getEmployees();


}
