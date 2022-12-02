package org.hlab.ems.db;

import org.hlab.ems.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EDBPostgreSQL implements EmployeeDBConnection {

    String dtbUrl="jdbc:postgresql://localhost:5432/dataset",user="postgres",password="hultime";
    Connection conn;
    Statement state;

    @Override
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection(dtbUrl,user,password);
            state=conn.createStatement();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        try {
            state=conn.createStatement();
            state.execute("INSERT INTO public.employee (firstname,lastname,age)" +
                    " VALUES ('"+employee.getFirstName()+"','"+employee.getLastName()+"',"+employee.getAge()+"); ") ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try{
            state.execute("UPDATE public.employee" +
                    " SET firstname='"+employee.getFirstName()+"',lastname='"
                +employee.getLastName()+"'', age="+employee.getAge()+""
                   + "WHERE id="+employee.getId()+";");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean deleteEmployee(int id) {
        boolean resp=false;
        try {
            resp=state.execute("DELETE FROM public.employee WHERE id='"+id+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resp;
    }
}
