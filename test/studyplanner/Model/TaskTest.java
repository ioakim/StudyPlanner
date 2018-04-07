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
public class TaskTest {
    
    public TaskTest() {
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
     * Test of getType method, of class Task.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        String expResult = type;
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Task.
     */
    @Test
    public void testSetType() {
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        type ="test";
        instance.setType(type);
        
        String expResult = type;
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCriteria method, of class Task.
     */
    @Test
    public void testGetCriteria() {
        System.out.println("getCriteria");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        String cName = "test name";
        Criterion criterion = new Criterion(cName);
        
        ArrayList<Criterion> criteriatest = new ArrayList<>();
        criteriatest.add(criterion);
        
        instance.getCriteria().add(criterion);
        
        ArrayList<Criterion> expResult = criteriatest;
        ArrayList<Criterion> result = instance.getCriteria();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCriteria method, of class Task.
     */
    @Test
    public void testSetCriteria() {
        System.out.println("setCriteria");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        String cName = "test name";
        Criterion criterion = new Criterion(cName);
        
        ArrayList<Criterion> criteriatest = new ArrayList<>();
        criteriatest.add(criterion);
        
        instance.setCriteria(criteriatest);
        
        ArrayList<Criterion> expResult = criteriatest;
        ArrayList<Criterion> result = instance.getCriteria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivityHistory method, of class Task.
     */
    @Test
    public void testGetActivityHistory() {
        System.out.println("getActivityHistory");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        String aname = "test name";
        String desc = "test description";
        Double value = 2.0;
        Activity activity = new Activity(aname,  desc,  value);
        ArrayList<Activity> activityHistoryTest = new ArrayList<>();
        activityHistoryTest.add(activity);
        
        instance.getActivityHistory().add(activity);
        
        ArrayList<Activity> expResult = activityHistoryTest;
        ArrayList<Activity> result = instance.getActivityHistory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setActivityHistory method, of class Task.
     */
    @Test
    public void testSetActivityHistory() {
        System.out.println("setActivityHistory");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        String aname = "test name";
        String desc = "test description";
        Double value = 2.0;
        Activity activity = new Activity(aname,  desc,  value);
        ArrayList<Activity> activityHistoryTest = new ArrayList<>();
        activityHistoryTest.add(activity);
        
        instance.setActivityHistory(activityHistoryTest);
        
        ArrayList<Activity> expResult = activityHistoryTest;
        ArrayList<Activity> result = instance.getActivityHistory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDependencies method, of class Task.
     */
    @Test
    public void testGetDependencies() {
        System.out.println("getDependencies");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        ArrayList<Task> dependenciesTest = new ArrayList<>();
        dependenciesTest.add(instance);
        
        instance.getDependencies().add(instance);
        
        ArrayList<Task> expResult = dependenciesTest;
        ArrayList<Task> result = instance.getDependencies();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDependencies method, of class Task.
     */
    @Test
    public void testSetDependencies() {
        System.out.println("setDependencies");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        ArrayList<Task> dependenciesTest = new ArrayList<>();
        dependenciesTest.add(instance);
        
        instance.setDependencies(dependenciesTest);
        
        ArrayList<Task> expResult = dependenciesTest;
        ArrayList<Task> result = instance.getDependencies();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Task.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task instance = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        String expResult = name;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
