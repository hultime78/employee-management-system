package org.hlab.ems.dao;

import org.hlab.ems.model.Employee;

import java.util.List;

public interface EmployeeDBConnection {

    boolean insertEmployee(Employee employee);

    List<Employee> getEmployees();

    Employee getEmployeeById(int id);

    Employee getEmployeeByFirstName(String firstName);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(int id);

    boolean deleteEmployee(String name);

    void connect();

    void disconnect();


}
