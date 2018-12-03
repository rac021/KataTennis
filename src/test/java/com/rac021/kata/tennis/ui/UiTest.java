
package com.rac021.kata.tennis.ui ;

import java.util.List ;
import org.junit.After ;
import org.junit.Test ;
import org.junit.Before ;
import java.util.ArrayList ;
import java.io.PrintStream ;
import org.junit.AfterClass ;
import org.junit.BeforeClass ;
import static org.junit.Assert.* ;
import java.io.ByteArrayOutputStream ;
import static org.hamcrest.CoreMatchers.containsString ;

/**
 *
 * @author ryahiaoui
 */

public class UiTest {

    private Ui ui   ;
    
    public UiTest() {
    }
    
    @BeforeClass
    public static void setUpClass()    { }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp() {
       ui = new Ui()    ;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of print method, of class Ui.
     */
    @Test
    public void shouldPrintGameOver()                               {
       String shouldContains = "END_OF_MATCH"                       ;
       String result = captureOutputRunner(ui::printEndOfTheMatch ) ;
       assertThat(result, containsString(shouldContains) )          ;
    }
    
    /**
     * Test of print method, of class Ui.
     */
    @Test
    public void shouldPrintFinishedMatch()                          {
       String shouldContains = "END_OF_MATCH"                       ;
       String result = captureOutputRunner(ui::printEndOfTheMatch ) ;
       assertThat(result, containsString(shouldContains) )          ;
    }

    /**
     * Test of printFinishedGame method, of class Ui.
     */
    @Test
    public void testPrintFinishedGame() {
        String shouldprint = "Game finished. Please Begin a new Game" ;
        List   games       =  new ArrayList()                         ;
        String output      =   captureOutputRunner( () -> 
                                       ui.printFinishedGame(games))   ;
        assertThat(output, containsString(shouldprint) )              ;
    }

    /**
     * Test of printBeginNewGames method, of class Ui.
     */
    @Test
    public void testPrintBeginNewGame()                        {
        String shouldprint = "Please begin a new Game"         ;
        String output      =   captureOutputRunner( () -> 
                                       ui.printBeginNewGame()) ;
        assertThat(output, containsString(shouldprint) )       ;
    }
   

    /**
     * Test of printStartingNewGameInfo method, of class Ui.
     */
    @Test
    public void testPrintStartingNewGameInfo()                    {
        String shouldprint = "2"                                  ;
        int    games       =  2                                   ;
        String output      =  captureOutputRunner( () -> 
                              ui.printStartingNewGameInfo(games)) ;
        assertThat(output, containsString(shouldprint) )          ;
    }

    /**
     * Test of printGame method, of class Ui.
     */
    @Test
    public void testPrintGames() {
        String shouldprint = "++  GAMES : "               ;
        List   games       =  new ArrayList()             ;
        String output      =  captureOutputRunner( () -> 
                              ui.printGame(games))        ;
        assertThat(output, containsString(shouldprint) )  ;
    }

    /**
     * Test of printCurrentScore method, of class Ui.
     */
    @Test
    public void shouldPrintCurrentScore()                              {
        
        String shouldprint = "##  Score { Game : 2 } -- "
                             + " Federer Wins  --"                     ;
        int    currentGame  = 2                                        ;
        String score       = " Federer Wins "                          ;
        String output      = captureOutputRunner( () -> 
                              ui.printCurrentScore(currentGame,score)) ;
        assertThat(output, containsString(shouldprint) )               ;

    }

    /**
     * Test of printPlayers method, of class Ui.
     */
    @Test
    public void testPrintPlayers()                         {
        
        String p1 = "Federer"                              ;
        String p2 = "Nadal"                                ;
        
        String shouldprint = "/ Federer ** VS ** Nadal \\" ;
        String output  = captureOutputRunner( () -> 
                          ui.printPlayers(p1,p2))          ;

        assertThat(output, containsString(shouldprint) )   ;
    }
    

    /* Capture Output Data */
    public String captureOutputRunner ( Runnable runner )             {
        
        PrintStream originalOut      = System.out                     ;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100) ;
            PrintStream capture      = new PrintStream(os)            ;
            System.setOut(capture)                                    ;
            runner .run()                                             ;
            capture.flush()                                           ;
            return os.toString()                                      ;
        } finally {
            System.setOut(originalOut);
        }
    }
    
}
