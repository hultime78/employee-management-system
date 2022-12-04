package org.hlab.ems.repo;


import org.hlab.ems.dao.EmployeeDBSQL;
import org.hlab.ems.dao.EmployeeDBConnection;
import org.hlab.ems.model.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeRepoSQL implements EmployeeRepo{

    private EmployeeDBConnection conn;


    public EmployeeRepoSQL(EmployeeDBConnection edb){
        this.conn=edb;
    }

    @Override
    public boolean save(Employee employee) {
       return conn.insertEmployee(employee);

    }

    @Override
    public Optional<Employee> findOne(int id) {
       return Optional.of(conn.getEmployeeById(id));
    }

    @Override
    public Optional<Employee> findOne(String firstName) {
        Employee employee=conn.getEmployeeByFirstName(firstName);
        return employee!=null?Optional.of(employee):Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        return conn.getEmployees();
    }

    @Override
    public int deleteEmployeeByID(int employeeID) {
        return conn.deleteEmployee(employeeID)?0:1;

    }

    @Override
    public int deleteEmployeeByFN(String firstName) {
        return conn.deleteEmployee(firstName)?0:1;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return conn.updateEmployee(employee)?employee:null;
    }
}
