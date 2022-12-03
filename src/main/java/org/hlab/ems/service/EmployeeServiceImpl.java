package org.hlab.ems.service;

import org.hlab.ems.model.Employee;
import org.hlab.ems.repo.EmployeeRepo;
import org.hlab.ems.repo.EmployeeRepoSQL;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }

    @Override
    public boolean createEmployee(String firstName,String lastName,int age) {
        if(employeeRepo.findOne(firstName).isEmpty()){
            employeeRepo.save(new Employee(0,firstName,lastName,age));
            return true;
        }else {
            throw new EmployeeException("Employee "+firstName+" already exists");
        }
    }

    @Override
    public boolean deleteEmployee(int id) {
        if(employeeRepo.findOne(id).isPresent()){
            employeeRepo.deleteEmployeeByID(id);
            return true;
        }else {
            throw new EmployeeException("Employee not found");
        }
    }

    @Override
    public Employee updateEmployee(int id,String firstName,String lastName,int age) {
        Employee emp=new Employee(0,firstName,lastName,age);
        if(employeeRepo.findOne(id).isPresent()){
            employeeRepo.updateEmployee(emp);
            return emp;
        }else{
            throw new EmployeeException("Employee not found");
        }
    }

    @Override
    public List<Employee> getEmployees() {
        var employees=employeeRepo.findAll();
        if (!employees.isEmpty()){
            return employees;
        }else {
            throw new EmployeeException("0 employee found ");
        }

    }

    @Override
    public Employee getByID(int id) {
        return employeeRepo.findOne(id).get();
    }

    @Override
    public Employee getBYFN(String firstName) {
        return employeeRepo.findOne(firstName).get();
    }
}
