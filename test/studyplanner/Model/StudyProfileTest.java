package studyplanner.Model;

import java.io.File;
import java.util.ArrayList;
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
public class StudyProfileTest {
    
    public StudyProfileTest() {
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
     * Test of getName method, of class StudyProfile.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        StudyProfile instance = new StudyProfile();
        instance.setName("test name");
        String expResult = "test name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getModules method, of class StudyProfile.
     */
    @Test
    public void testGetModules() {
        System.out.println("getModules");
        StudyProfile instance = new StudyProfile();
        instance.setName("test name");
        
        
        String name = "test name";
        String code = "test code";
        Module module = new Module(name, code);
        
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(module);
        
        instance.getModules().add(module);
        
        ArrayList<Module> expResult = modules;
        ArrayList<Module> result = instance.getModules();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of setName method, of class StudyProfile.
     */
    @Test
    public void testSetName() {
        System.out.println("getName");
        StudyProfile instance = new StudyProfile();
        instance.setName("test name");
        String expResult = "test name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class StudyProfile.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        StudyProfile instance = new StudyProfile();        
        instance.setName("test name");
        
        String expResult = "test name";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
