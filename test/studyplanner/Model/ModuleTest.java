package studyplanner.Model;

import java.util.Date;
import java.util.HashSet;
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
public class ModuleTest {
    
    public ModuleTest() {
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
     * Test of getName method, of class Module.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        String name = "test name";
        String code = "test code";
        Module instance = new Module(name, code);
        
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCode method, of class Module.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        
        String name = "test name";
        String code = "test code";
        Module instance = new Module(name, code);
        
        String expResult = code;
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAssignments method, of class Module.
     */
    @Test
    public void testGetAssignments() {
        System.out.println("getAssignments");
        
        String name = "test name";
        String code = "test code";
        Module instance = new Module(name, code);
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment assignment = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        HashSet<Assignment> assignments = new HashSet<>();
        assignments.add(assignment);
        
        instance.getAssignments().add(assignment);
        
        HashSet<Assignment> expResult = assignments;
        HashSet<Assignment> result = instance.getAssignments();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Module.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        
        String name = "test name";
        String code = "test code";
        Module instance = new Module(name, code);
        String name2 = "test name";
        instance.setName(name2);
        
        String expResult = name2;
        String result = instance.getName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setCode method, of class Module.
     */
    @Test
    public void testSetCode() {
        System.out.println("setCode");
        
        String name = "test name";
        String code = "test code";
        Module instance = new Module(name, code);
        
        String code2 = "test code";
        instance.setCode(code2);
        
        String expResult = code2;
        String result = instance.getCode();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class Module.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        String name = "test name";
        String code = "test code";
        Module instance = new Module(name, code);
        
        double weighting = 30;
        String aName = "test name";
        String aDescription = "test description";
        Date aStart = new Date();
        Date aEnd = new Date();
        
        Assignment assignment = new Assignment(weighting, aName, aDescription, aStart, aEnd);
        
        instance.add(assignment);
        
        assert(instance.getAssignments().contains(assignment));
    }

    /**
     * Test of toString method, of class Module.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
                
        String name = "test name";
        String code = "test code";
        Module instance = new Module(name, code);
        
        String expResult = name;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
