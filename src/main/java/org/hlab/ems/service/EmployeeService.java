package org.hlab.ems.service;

import org.hlab.ems.model.Employee;

import java.util.List;

public interface EmployeeService {


    boolean createEmployee(String firstName,String lastName,int age);

    boolean deleteEmployee(int id);

    Employee updateEmployee(int id,String firstName,String lastName,int age);

    List<Employee> getEmployees();

    Employee getByID(int id);

    Employee getBYFN(String firstName);

}
