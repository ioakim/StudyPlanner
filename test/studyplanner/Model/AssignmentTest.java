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
public class AssignmentTest {
    
    public AssignmentTest() {
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
     * Test of addTask method, of class Assignment.
     */
    @Test
    public void testAddTask() {
        System.out.println("addTask");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task task = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        
        instance.addTask(task);
        Task expResult = instance.getTasks().get(0);
        assertEquals(expResult, task);
    }

    /**
     * Test of addMilestone method, of class Assignment.
     */
    @Test
    public void testAddMilestone() {
        System.out.println("addMilestone");
        
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Milestone milestone = new Milestone(tasks, name, description, start, end);
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        instance.addMilestone(milestone);
        
        Milestone expResult = instance.getMilestones().get(0);
        
        assertEquals(expResult, milestone);
        
    }

    /**
     * Test of getWeighting method, of class Assignment.
     */
    @Test
    public void testGetWeighting() {
        System.out.println("getWeighting");
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        double expResult = 30;
        
        double result = instance.getWeighting();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of setWeighting method, of class Assignment.
     */
    @Test
    public void testSetWeighting() {
        System.out.println("setWeighting");
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        instance.setWeighting(weighting);
        
        double result = instance.getWeighting();
        double expResult = weighting;
        
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getTasks method, of class Assignment.
     */
    @Test
    public void testGetTasks() {
        System.out.println("getTasks");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task task = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        instance.addTask(task);
        
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        
        ArrayList<Task> expResult = tasks;
        ArrayList<Task> result = instance.getTasks();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTasks method, of class Assignment.
     */
    @Test
    public void testSetTasks() {
        System.out.println("setTasks");
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task task = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        
        instance.setTasks(tasks);
        
        ArrayList<Task> expResult = tasks;
        ArrayList<Task> result = instance.getTasks();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getMilestones method, of class Assignment.
     */
    @Test
    public void testGetMilestones() {
        System.out.println("getMilestones");
        
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Milestone milestone = new Milestone(tasks, name, description, start, end);
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        instance.addMilestone(milestone);
        
        ArrayList<Milestone> milestones = new ArrayList<>();
        milestones.add(milestone);
        
        ArrayList<Milestone> expResult = milestones;
        ArrayList<Milestone> result = instance.getMilestones();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMilestones method, of class Assignment.
     */
    @Test
    public void testSetMilestones() {
        System.out.println("setMilestones");
        
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Milestone milestone = new Milestone(tasks, name, description, start, end);
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        
        
        ArrayList<Milestone> milestones = new ArrayList<>();
        milestones.add(milestone);
        
        instance.setMilestones(milestones);
        
        ArrayList<Milestone> expResult = milestones;
        ArrayList<Milestone> result = instance.getMilestones();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Assignment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment instance = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        
        String expResult = aName;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
