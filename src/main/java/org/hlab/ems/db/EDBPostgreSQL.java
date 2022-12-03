package org.hlab.ems.db;

import org.hlab.ems.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class EDBPostgreSQL implements EmployeeDBConnection {

    Logger log=Logger.getLogger(EmployeeDBConnection.class.getName());

    String dtbUrl="jdbc:postgresql://localhost:5432/ems",user="postgres",password="hultime";
    Connection conn;
    Statement state;

    public EDBPostgreSQL(){
        connect();
    }

    @Override
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection(dtbUrl,user,password);
            state=conn.createStatement();
            log.info("Connected to the database");

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            throw new DBException("Could not connect to the database",e);
        }
    }

    @Override
    public boolean insertEmployee(Employee employee) {
        boolean resp;
        try {
            state=conn.createStatement();
            resp=state.execute("INSERT INTO public.employee (firstname,lastname,age)" +
                    " VALUES ('"+employee.getFirstName()+"','"+employee.getLastName()+"',"+employee.getAge()+"); ")
            ;
            log.info("Inserted new employee: "+employee);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Could not insert a new employee" ,e);
        }
        return resp;
    }

    @Override
    public List<Employee> getEmployees() {

        List<Employee> employeeList=new ArrayList<>();
        try {
            ResultSet reset=state.executeQuery("SELECT * FROM public.employee ;");
            while (reset.next()){
                employeeList.add(new Employee(reset.getInt(1),reset.getString(2),
                        reset.getString(3),reset.getInt(4)));
            }
            employeeList.forEach(item->log.info(item.toString()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DBException("Could not get the list of employee",throwables);
        }
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try {
            ResultSet reset=state.executeQuery("SELECT * FROM public.employee WHERE id="+id+" ;");
            while (reset.next()){
                employee=new Employee(reset.getInt(1),reset.getString(2),
                        reset.getString(3),reset.getInt(4));
            }
            log.info("Found : "+employee);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DBException("could not retrieve the employee",throwables);
        }
        return employee;
    }

    @Override
    public Employee getEmployeeByFirstName(String firstName) {
        Employee employee = null;
        try {
            ResultSet reset=state.executeQuery("SELECT * FROM public.employee WHERE firstname='"+firstName+"' ;");
            while (reset.next()){
                employee=new Employee(reset.getInt(1),reset.getString(2),
                        reset.getString(3),reset.getInt(4));
            }
            log.info("Found : "+employee);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Could not get the employee",e);
        }
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        boolean resp;
        try{
            resp=state.execute("UPDATE public.employee" +
                    " SET firstname='"+employee.getFirstName()+"',lastname='"
                +employee.getLastName()+"'', age="+employee.getAge()+""
                   + "WHERE id="+employee.getId()+";");
            log.info("Updated : "+employee);
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBException("Could not update employee",e);
        }
        return resp;
    }

    @Override
    public boolean deleteEmployee(int id) {
        boolean resp;
        try {
            resp=state.execute("DELETE FROM public.employee WHERE id='"+id+"'");
            log.info("deleted employee id : "+id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DBException("Couldn't delete the employe",throwables);
        }
        return resp;
    }

    @Override
    public void disconnect(){
        try {
            if(conn!=null){
                conn.close();
                log.info("Connection closed");
            }
            if (state!=null){
                state.close();
                log.info("State closed");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DBException("Couldn't close the connection ",throwables);
        }
    }
}
