
package com.rac021.kata.tennis.core.entities ;

import org.junit.Test ;
import org.junit.After ;
import org.junit.Before ;
import org.junit.AfterClass ;
import org.junit.BeforeClass ;
import static org.junit.Assert.* ;
import static org.hamcrest.CoreMatchers.is ;

/**
 *
 * @author ryahiaoui
 */

public class PointTest {
    
    public PointTest() {
    }
    
    @BeforeClass
    public static void setUpClass()    { }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp()    { }
    
    @After
    public void tearDown() { }

    /**
     * Test of values method, of class Point.
     */
    @Test
    public void testValues()                             {
        
        System.out.println("values")                     ;
        
        Point expResult = Point.Love                     ;
        Point valueOf = Point.valueOf(Point.Love.name()) ;
        
        assertThat( valueOf , is(expResult) )            ;
    } 
  
}
