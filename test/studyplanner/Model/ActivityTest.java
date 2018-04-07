/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studyplanner.Model;

import java.util.ArrayList;
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
public class ActivityTest {
    
    public ActivityTest() {
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
     * Test of updateCriterion method, of class Activity.
     */
    @Test
    public void testUpdateCriterion() {
        System.out.println("updateCriterion");
        String name = "test name";
        Criterion c = new Criterion(name);
        
        Activity instance = new Activity();
        instance.add(c);
        
        instance.updateCriterion(c);
        Boolean expResult = true;
        assertEquals(expResult, instance.getCriteria().get(0).isMet());
    }

    /**
     * Test of getName method, of class Activity.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String name = "test name";
        String desc = "test description";
        Double value = 2.0;
        Activity instance = new Activity(name,  desc,  value);
        String expResult = "test name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Activity.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "test name";
        Activity instance = new Activity();
        instance.setName(name);
        String expResult = instance.getName();
        assertEquals(expResult, name);
    }

    /**
     * Test of getDescription method, of class Activity.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String name = "test name";
        String desc = "test description";
        Double value = 2.0;
        Activity instance = new Activity(name, desc, value);
        String expResult = "test description";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Activity.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "test description";
        Activity instance = new Activity();
        instance.setDescription(description);
        String expResult = instance.getDescription();
        assertEquals(expResult, description);
    }

    /**
     * Test of getValue method, of class Activity.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        String name = "test name";
        String desc = "test description";
        Double value = 2.0;
        Activity instance = new Activity(name, desc, value);
        Double expResult = value;
        Double result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of setValue method, of class Activity.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        Double value = 2.0;
        Activity instance = new Activity();
        instance.setValue(value);
        Double expResult = instance.getValue();
        assertEquals(expResult, value);
    }

    /**
     * Test of getCriteria method, of class Activity.
     */
    @Test
    public void testGetCriteria() {
        System.out.println("getCriteria");
        
        ArrayList<Criterion> criteria = new ArrayList<>();
        
        String name = "test name";
        String uom = "test uom";
        Double value = 2.0;
        criteria.add( new Criterion( name,  value,  uom));
        
        Activity instance = new Activity();
        instance.add(criteria);
        
        ArrayList<Criterion> expResult = criteria;
        ArrayList<Criterion> result = instance.getCriteria();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class Activity.
     */
    @Test
    public void testAdd_Criterion() {
        System.out.println("add");
       
        String name = "test name";
        String uom = "test uom";
        Double value = 2.0;
        
        Criterion criteria = new Criterion( name,  value,  uom);
        Activity instance = new Activity();
        instance.add(criteria);
        
        Criterion expResult = instance.getCriteria().get(0);
        assertEquals(expResult, criteria);
    }

    /**
     * Test of add method, of class Activity.
     */
    @Test
    public void testAdd_ArrayList() {
        System.out.println("add");
        ArrayList<Criterion> criteria = new ArrayList<>();
        
        String name = "test name";
        String uom = "test uom";
        Double value = 2.0;
        criteria.add( new Criterion( name,  value,  uom));
        
        Activity instance = new Activity();
        instance.add(criteria);
        
        ArrayList<Criterion> expResult = instance.getCriteria();
        
        assertEquals(expResult, criteria);
    }
    
}
