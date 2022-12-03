package org.hlab.ems.service;

import org.hlab.ems.db.EDBPostgreSQL;
import org.hlab.ems.model.Employee;
import org.hlab.ems.repo.EmployeeRepo;
import org.hlab.ems.repo.EmployeeRepoImpl;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepo employeeRepo=new EmployeeRepoImpl(new EDBPostgreSQL());

    @Override
    public boolean createEmployee(Employee employee) {
        employeeRepo.save(employee);
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        return null;
    }
}
