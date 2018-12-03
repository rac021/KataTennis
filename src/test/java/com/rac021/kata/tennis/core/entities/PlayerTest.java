
package com.rac021.kata.tennis.core.entities ;

import org.junit.Test ;
import org.junit.After ;
import org.junit.Before ;
import org.junit.AfterClass ;
import org.junit.BeforeClass ;
import static org.junit.Assert.* ;

/**
 *
 * @author ryahiaoui
 */

public class PlayerTest   {

    private Player player ;
    
    public PlayerTest()   {
    }
    
    @BeforeClass
    public static void setUpClass()    { }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @After
    public void tearDown()             { }
    
    @Before
    public void setUp()             {
      player = new Player("Nadal")  ;
    }
    
    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void shouldGetNameNadal()     {
        String expResult = "Nadal"       ;
        String result = player.getName() ;
        assertEquals(expResult, result)  ;
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString()               {
        String expResult = "Nadal"           ;
        String result    = player.toString() ;
        assertEquals(expResult, result)      ;
    }
    
}
