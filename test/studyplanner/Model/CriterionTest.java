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
public class CriterionTest {
    
    public CriterionTest() {
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
     * Test of updateTask method, of class Criterion.
     */
    @Test
    public void testUpdateTask() {
        System.out.println("updateTask");
        
        String type = " ";
        ArrayList<Criterion> criteria = new ArrayList<>();
        ArrayList<Activity> activityHistory = new ArrayList<>();
        ArrayList<Task> dependencies = new ArrayList<>();
        String name = "test name";
        String description = "test description";
        Date start = new Date();
        Date end = new Date();
        
        Task task = new Task(type, criteria, activityHistory, dependencies, name, description, start, end);
        
        String cName = "test name";
        Criterion instance = new Criterion(cName);
        
        task.getCriteria().add(instance);
        
        instance.updateTask(task);
        
        boolean expResult = false;
        boolean result = task.isDone();
        
        assertEquals(expResult,result);
        
    }

    /**
     * Test of getName method, of class Criterion.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        String cName = "test name";
        Criterion instance = new Criterion(cName);
        
        String expResult = cName;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Criterion.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        
        String initName = "initialise name";
        String cName = "test name";
        Criterion instance = new Criterion(initName);
        instance.setName(cName);
        
        String expResult = cName;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Criterion.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        String cName = "test name";
        Criterion instance = new Criterion(cName);
        
        CriterionType expResult = CriterionType.Boolean;
        CriterionType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Criterion.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        CriterionType type = CriterionType.Boolean;
        
        String cName = "test name";
        Criterion instance = new Criterion(cName);
        
        instance.setType(type);
        
        CriterionType expResult = type;
        CriterionType result = instance.getType();
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isMet method, of class Criterion.
     */
    @Test
    public void testIsMet() {
        System.out.println("isMet");
        String cName = "test name";
        Criterion instance = new Criterion(cName);
        boolean expResult = false;
        boolean result = instance.isMet();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMet method, of class Criterion.
     */
    @Test
    public void testSetMet() {
        System.out.println("setMet");
        boolean met = true;
        String cName = "test name";
        Criterion instance = new Criterion(cName);
        instance.setMet(met);
        boolean expResult = true;
        boolean result = instance.isMet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class Criterion.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        
        String name = "test name";
        String uom = "test uom";
        double value = 3;
        
        Criterion instance = new Criterion(name, value, uom);
        double expResult = value;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setValue method, of class Criterion.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        String name = "test name";
        String uom = "test uom";
        double value = 3;
        
        Criterion instance = new Criterion(name, value, uom);
        double testValue = 4;
        instance.setValue(testValue);
        
        double expResult = testValue;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getUnitOfMeasure method, of class Criterion.
     */
    @Test
    public void testGetUnitOfMeasure() {
        System.out.println("getUnitOfMeasure");
        String name = "test name";
        String uom = "test uom";
        double value = 3;
        
        Criterion instance = new Criterion(name, value, uom);
        String expResult = uom;
        String result = instance.getUnitOfMeasure();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUnitOfMeasure method, of class Criterion.
     */
    @Test
    public void testSetUnitOfMeasure() {
        System.out.println("setUnitOfMeasure");
        String name = "test name";
        String uom = "test uom";
        double value = 3;
        
        Criterion instance = new Criterion(name, value, uom);
        
        String unitOfMeasure = "uom test";
        instance.setUnitOfMeasure(unitOfMeasure);
        
        String expResult = unitOfMeasure;
        String result = instance.getUnitOfMeasure();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Criterion.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        System.out.println("setUnitOfMeasure");
        String name = "test name";
        String uom = "test uom";
        double value = 3;
        
        Criterion instance = new Criterion(name, value, uom);
        
        String expResult = name;
        String result = instance.toString();
        
        assertEquals(expResult, result);
    }
    
}
