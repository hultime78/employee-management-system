package org.hlab.ems.repo;


import org.hlab.ems.db.EmployeeDBConnection;
import org.hlab.ems.model.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeRepoImpl implements EmployeeRepo{

    EmployeeDBConnection conn;

    public EmployeeRepoImpl(EmployeeDBConnection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Employee employee) {
        boolean isSaved=false;
        Employee em=conn.getEmployeeByFirstName(employee.getFirstName());
        if(em==null) isSaved=conn.insertEmployee(employee);
        conn.disconnect();
        return isSaved ;

    }

    @Override
    public Optional<Employee> findOne(int id) {
        Employee employee=conn.getEmployeeById(id);
        conn.disconnect();
        if(employee!=null)
            return Optional.of(employee);
        else
            return Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList;
        employeeList=conn.getEmployees();
        conn.disconnect();
        return employeeList;
    }

    @Override
    public int deleteEmployeeByID(int employeeID) {
        Employee employee=conn.getEmployeeById(employeeID);
        if (employee!=null) {
            conn.deleteEmployee(employeeID);
            conn.disconnect();
            return 0;
        }
        else{
            return 1;
        }

    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if(conn.getEmployeeById(employee.getId())!=null){
            conn.updateEmployee(employee);
            conn.disconnect();
        }
        return null;
    }
}
