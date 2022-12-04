package org.hlab.ems.repo;

import org.hlab.ems.dao.EmployeeDBConnection;
import org.hlab.ems.dao.EmployeeDBException;
import org.hlab.ems.model.Employee;
import org.hlab.ems.repo.db.FakeDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepoTest {

    private static Logger log=Logger.getLogger(EmployeeRepoTest.class.getName());

    private static EmployeeDBConnection conn;
    private static EmployeeRepo repo;

    @BeforeAll
    public static void prepare(){
        conn=new FakeDB();
        repo=new EmployeeRepoSQL(conn);
    }

    @BeforeEach
    public void setUp(){

        //inserting a record so we can test update/findone
        repo.save(new Employee(0,"mandiangu","gilu",45));
    }

    @AfterEach
    public void tearDown(){
        log.info("Employee deletion resp: "+repo.deleteEmployeeByFN("mandiangu"));
    }

    @Test
    public void testFindOneExisting(){
        Optional<Employee> expected=repo.findOne("mandiangu");
        assertTrue(expected.isPresent());

    }

    @Test
    public void testFindOneNonExisting(){
        EmployeeDBException thrown=assertThrows(EmployeeDBException.class,()->{
            repo.findOne("Spreader");
        },"Employee doesn't exists");
        assertTrue(thrown.getMessage().contains("Employee not found"));
    }

    @Test
    public void testFindAll(){assertEquals(1,repo.findAll().size());}

    @Test
    public void testInsert(){
        Employee expected=new Employee(0,"Elena","johan",24);
        repo.save(expected);
        Employee actual=repo.findOne(expected.getFirstName()).get();
        assertEquals(expected,actual);
    }

    @Test
    public void testUpdate(){
        Employee existing=conn.getEmployeeByFirstName("mandiangu");
        int originalAge=existing.getAge();
        existing.setAge(44);
        assertTrue(originalAge!=existing.getAge());

    }

    @Test
    public void testDeleteExistence() {
        assertEquals(0,repo.deleteEmployeeByFN("mandiangu"));
    }

    @Test
    public void testDeleteNonExistence(){assertEquals(1,repo.deleteEmployeeByID(0));}

    public static void cleanUp(){
        conn=null;
        log.info("all done");
    }

}
