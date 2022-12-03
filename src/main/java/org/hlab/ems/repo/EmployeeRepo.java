package org.hlab.ems.repo;

import org.hlab.ems.model.Employee;

import java.util.List;
import java.util.Optional;

/**
 * @author Aaron mangbenza
 * @since 1.0
 */
public interface EmployeeRepo {

    /**
     * Add a nem employee to the database
     * @param employee
     * @return true if the employee is successfully added false otherwise
     */
    boolean save(Employee employee);

    /**
     * find an employee by id
     * @param id
     * @return an optional of Employee
     */
    Optional<Employee> findOne(int id);

    Optional<Employee> findOne(String firstName);

    /**
     * Get all records of employee from the database
     * @return a list of all employees
     */

    List<Employee> findAll();

    /**
     * Delete employee by id
     * @param employeeID
     * @return {@code 0} if employee was found and deleted
     * {@code 1} otherwise
     */
    int deleteEmployeeByID(int employeeID);


    Employee updateEmployee(Employee employee);


}
