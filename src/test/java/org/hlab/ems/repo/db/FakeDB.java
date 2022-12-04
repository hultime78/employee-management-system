package org.hlab.ems.repo.db;

import org.hlab.ems.dao.EmployeeDBConnection;
import org.hlab.ems.dao.EmployeeDBException;
import org.hlab.ems.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FakeDB implements EmployeeDBConnection {


    Logger log=Logger.getLogger(FakeDB.class.getName());

    /**
     * pseudo-data base with firsName as pk
     */
    Map<String,Employee> db=new HashMap<>();

    @Override
    public boolean insertEmployee(Employee employee) {
        log.info("Trying to insert a new employee");
        if(db.containsKey(employee.getFirstName())){
            throw new EmployeeDBException("Employee "+employee+" already exists");
        }else {
            db.put(employee.getFirstName(),employee);
            log.info("Employee "+employee+" inserted") ;
            return true;
        }

    }

    @Override
    public List<Employee> getEmployees() {
        ArrayList<Employee> employees=new ArrayList<>();
        employees.addAll(db.values());
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        //not tested
        return null;
    }


    @Override
    public Employee getEmployeeByFirstName(String firstName) {
        if(db.get(firstName)==null){
            throw new EmployeeDBException("Employee not found");
        }
        else return db.get(firstName);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
       return db.replace(employee.getFirstName(), employee) != null;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }

    @Override
    public boolean deleteEmployee(String name) {
        return db.remove(name)!=null;
    }

    @Override
    public void connect() {
    //no need to test
    }

    @Override
    public void disconnect() {
    //no need to test
    }
}
