package studyplanner.Model;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ioakim
 */
public class MilestoneTest {
    
    public MilestoneTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTasks method, of class Milestone.
     */
    @Test
    public void testGetTasks() {
        System.out.println("getTasks");
        
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Milestone instance = new Milestone(tasks, name, description, start, end);
        
        ArrayList<Task> expResult = tasks;
        ArrayList<Task> result = instance.getTasks();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTasks method, of class Milestone.
     */
    @Test
    public void testSetTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Milestone instance = new Milestone(tasks, name, description, start, end);
        
        ArrayList<Task> tasks2 = new ArrayList<>();
        instance.setTasks(tasks2);
        
        
        ArrayList<Task> expResult = tasks2;
        ArrayList<Task> result = instance.getTasks();
        assertEquals(expResult, result);
    }

    /**
     * Test of addTask method, of class Milestone.
     */
    @Test
    public void testAddTask() {
        System.out.println("addTask");
        
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Milestone instance = new Milestone(tasks, name, description, start, end);
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String tname = "test name";
        String tdescription = "test description";
        Date tstart = new Date();
        Date tend = new Date();
        
        Task task = new Task(type, criteria, activityHistory, dependencies, tname, tdescription, tstart, tend);
        
        
        boolean expResult = true;
        boolean result = instance.addTask(task);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeTask method, of class Milestone.
     */
    @Test
    public void testRemoveTask() {
        System.out.println("removeTask");
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Milestone instance = new Milestone(tasks, name, description, start, end);
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String tname = "test name";
        String tdescription = "test description";
        Date tstart = new Date();
        Date tend = new Date();
        
        Task task = new Task(type, criteria, activityHistory, dependencies, tname, tdescription, tstart, tend);
        
        instance.addTask(task);
        
        boolean expResult = true;
        boolean result = instance.removeTask(task);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class Milestone.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Milestone instance = new Milestone(tasks, name, description, start, end);
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String tname = "test name";
        String tdescription = "test description";
        Date tstart = new Date();
        Date tend = new Date();
        
        Task task = new Task(type, criteria, activityHistory, dependencies, tname, tdescription, tstart, tend);
        
        instance.addTask(task);
        
        instance.update();
        
        boolean expResult = false;
        boolean result = instance.getTasks().get(0).isDone();
        
        assertEquals(expResult, result);
    }
    
}
