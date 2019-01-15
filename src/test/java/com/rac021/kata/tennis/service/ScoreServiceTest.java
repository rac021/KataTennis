
package com.rac021.kata.tennis.service ;

import org.junit.Test ;
import java.util.List ;
import org.junit.After ;
import org.junit.Before ;
import org.junit.AfterClass ;
import org.junit.BeforeClass ;
import static org.junit.Assert.* ;
import static org.hamcrest.CoreMatchers.is ;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty ;

/**
 *
 * @author ryahiaoui
 */

public class ScoreServiceTest                 {

    private ScoreService scoreService         ;
    
    private final String nadale   = "Nadale"  ;
    private final String federer = "Federer"  ;
    
    public ScoreServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass()    {  }
    
    @AfterClass
    public static void tearDownClass() {  }
    
    @Before
    public void setUp() {
        
      scoreService = new ScoreService()   ;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class ScoreService.
     */
    @Test
    public void shouldGetLoveAll()                        {
        
        String expResult = "Love -  ALL "                 ;
        String result = scoreService.get(nadale, federer) ;
        assertThat( expResult, is(result) )               ;
    }

    /**
     * Test of isFishedGame method, of class ScoreService.
     */
    @Test
    public void shoudBeFinishedGameIfNotNewGameWasStarted() {

        boolean finishedGame = true                         ;
        boolean result       = scoreService.isEndGame()     ;
        assertThat( finishedGame, is(result ) )             ;
    }

    /**
     * Test of tryNewGamr method, of class ScoreService.
     */
    @Test
    public void shouldStartNewGameIfNotAlreadyOneExisting() {

        boolean expResult = true                            ;
        boolean result    = scoreService.tryNewGame()       ;
        assertThat(expResult, is(result))                   ;
    }

    /**
     * Test of getCurrentGame method, of class ScoreService.
     */
    @Test
    public void shouldCurrentGameBeZeroWhenStartingGame()  {

        int expResult = 0                                 ;
        int result = scoreService.getCurrentGame()        ;
        assertThat(expResult, is(result) )                ;
    }

    /**
     * Test of getWinnerGame method, of class ScoreService.
     */
    @Test
    public void shouldWinnerGameBeEmptyWhenStartingGame() {
       List<String> result = scoreService.getWinerGames() ;
       assertThat(result, is(empty()))                    ;
    }

    /**
     * Test of incrementScorePlayerOne method, of class ScoreService.
     */
    @Test
    public void shouldIncrementScorePlayerOne()               {
        scoreService.incrementScorePlayerOne()                ;
        int expected = 1                                      ;
        int scorePlayerOne = scoreService.getScorePlayerOne() ;
        assertThat(scorePlayerOne, is(expected))              ;
    }

     /**
     * Test of incrementScorePlayerOne method, of class ScoreService.
     */
    @Test
    public void shouldIncrementScorePlayerTwo()               {
        scoreService.incrementScorePlayerTwo()                ;
        int expected = 1                                      ;
        int scorePlayerTwo = scoreService.getScorePlayerTwo() ;
        assertThat(scorePlayerTwo, is(expected))              ;
    }


    /**
     * Test of releaseScores method, of class ScoreService.
     */
    @Test
    public void shouldIncrementAndThenReleaseScorePlayerOne() {
        int expected       = 0                                ;
        scoreService.incrementScorePlayerOne()                ;
        scoreService.incrementScorePlayerOne()                ;
        scoreService.releaseScores()                          ;
        int scorePlayerOne = scoreService.getScorePlayerOne() ;
        assertThat(scorePlayerOne, is(expected))              ;
    }
    
    /**
     * Test of releaseScores method, of class ScoreService.
     */
    @Test
    public void shouldIncrementAndThenReleaseScorePlayerTwo() {
        int expected       = 0                                ;
        scoreService.incrementScorePlayerTwo()                ;
        scoreService.incrementScorePlayerTwo()                ;
        scoreService.releaseScores()                          ;
        int scorePlayerTwo = scoreService.getScorePlayerTwo() ;
        assertThat(scorePlayerTwo, is(expected))              ;
    }

    /**
     * Test of saveWinnerGame method, of class ScoreService.
     */
    @Test
    public void shouldSavePlayerInTheWinnerGame() {
        String player = "player"                  ;
        scoreService.saveWinnerGame(player)       ;
        assertThat( scoreService.getWinerGames()  , 
                    is(not(empty())))             ;
    }

    /**
     * Test of endGame method, of class ScoreService.
     */
    @Test
    public void shouldEndGameBeFalseBeforeStartingGame() {
        boolean expResult = false                        ;
        boolean result = scoreService.isEndMatch()       ;
        assertEquals(expResult, result)                  ;
    }

    /**
     * Test of isFinishedGame method, of class ScoreService.
     */
    @Test
    public void shouldFinishedGameBeFalseWhenStartingGame() {
        boolean expResult = true                            ;
        boolean result    = scoreService.isEndGame()        ;
        assertEquals(expResult, result)                     ;
    }
    
    /**
     * Test of isFinishedGame method, of class ScoreService.
     */
    @Test
    public void shouldCheckTieBreakReturnTrueAfter6GamesWonByEachPlayer() {
        
        scoreService.saveWinnerGame("Winner is Federer") ;
        scoreService.saveWinnerGame("Winner is Federer") ;
        scoreService.saveWinnerGame("Winner is Federer") ;
        scoreService.saveWinnerGame("Winner is Federer") ;
        scoreService.saveWinnerGame("Winner is Federer") ;
        scoreService.saveWinnerGame("Winner is Nadal")   ;
        scoreService.saveWinnerGame("Winner is Nadal")   ;
        scoreService.saveWinnerGame("Winner is Nadal")   ;
        scoreService.saveWinnerGame("Winner is Nadal")   ;
        scoreService.saveWinnerGame("Winner is Nadal")   ;
        scoreService.saveWinnerGame("Winner is Federer") ;
        scoreService.saveWinnerGame("Winner is Nadal")   ;
        
        boolean expResult = true                         ;
        boolean result = scoreService
                          .checkTieBreak( "Nadal",
                                          "Federer")     ;
        
        assertEquals(expResult, result)                  ;
    }
    
}
