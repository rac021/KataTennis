
package com.rac021.kata.tennis.service ;

import org.junit.Test ;
import java.util.List ;
import org.junit.Rule ;
import org.junit.After ;
import org.junit.Before ;
import org.junit.AfterClass ;
import org.junit.BeforeClass ;
import static org.junit.Assert.* ;
import com.rac021.kata.tennis.ui.Ui ;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.is ;
import com.rac021.kata.tennis.core.entities.Player ;
import org.junit.contrib.java.lang.system.ExpectedSystemExit ;
import static org.hamcrest.collection.IsEmptyCollection.empty ;


/**
 *
 * @author ryahiaoui
 */

public class PlayTest      {
    
    private Player federer ;
    private Player nadal   ;
    
    private Play   play    ;
    
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none() ;
     
    public PlayTest()                  { }
    
    @BeforeClass
    public static void setUpClass()    { }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp()                { 
    
      federer = new Player("Federer")  ;
      nadal   = new Player("Nadal")    ;
      
      play  = new Play()               ;
      play.whenWinPoint( Player.class  ,
                         play::incrementScore ) ; 
    
    }
    
    @After
    public void tearDown() { }

    @Test
    public void shouldBeLoveAll()                  {
        
        String result = play.tennis(federer, nadal )
                            .getScore()            ;           
        String expResult = "Love -  ALL "          ;
        assertThat(expResult, is(result))          ;
    }
    
    @Test (expected = IllegalStateException.class   )
    public void shouldRaiseNullPointerExceptionUi() {
        
        play.tennis(federer, nadal )
            .winPoint(federer)                       
            .getScore()                             ;           
    }
    
    @Test( expected = IllegalStateException.class   )
    public void shouldStartGameBeforeWinPoint()     {
        
        String result = play.tennis(federer, nadal  )
                            .ui( new Ui() )
                            .winPoint(federer)                       
                            .getScore()            ;           
        String expResult = "Fifteen -  ALL "       ;
        assertThat(expResult, is(result))          ;
    }
    
    @Test
    public void shouldDisplayFifteenAll()           {
        
        String result = play.tennis(federer, nadal  )
                            .ui(new Ui())
                            .newGame()
                            .winPoint(federer)                       
                            .winPoint(nadal)                       
                            .getScore()            ;           
        String expResult = "Fifteen -  ALL "       ;
        assertThat(expResult, is(result))          ;
    }
    
    @Test
    public void shouldFedererWin()                 {
        
        String result = play.tennis(federer, nadal )
                            .ui(new Ui())
                            .newGame()
                            .winPoint(federer)                       
                            .winPoint(federer)                       
                            .winPoint(federer)                       
                            .winPoint(nadal)                       
                            .winPoint(federer)                       
                            .getScore()            ;           
         String expResult = "Winner is "           +
                            federer.getName()      ;
        assertThat(expResult, is(result))          ;
    }
    
    @Test
    public void shouldNadalWin()                   {
        
        String result = play.tennis(federer, nadal )
                            .ui(new Ui())
                            .newGame()
                            .winPoint(nadal)                       
                            .winPoint(nadal)                       
                            .winPoint(nadal)                       
                            .winPoint(nadal)                       
                            .winPoint(federer)                       
                            .getScore()            ;
        
        String expResult = "Winner is "            +
                            nadal.getName()        ;
        
        assertThat(expResult, is(result))          ;
    }
    
    @Test
    public void shouldCurrentGameBeOne()              {
        
        int currentGame = play.tennis(federer, nadal  ) 
                              .ui(new Ui())
                              .newGame()
                              .winPoint(nadal)
                              .getCurrentGame()       ;
        int expResult  = 1                            ;         
                            nadal.getName()           ;
        assertThat(expResult, is(currentGame))        ;
    }
    
    
    @Test( expected = IllegalStateException.class )
    public void shouldRaiseExceptionWhenTryingStartNewGameAndCurrentGameIsAlreadyNotFinished() {
        
        play.tennis(federer, nadal )
            .ui(new Ui())
            .newGame()
            .winPoint(federer)
            .newGame()  // Start new Game while current Game is not already finished
            .winPoint(nadal)       ;           
    }
    
    @Test
    public void shouldDisplayCurrentGameIs2 () {
        
        int game= play.tennis(federer, nadal  )
                      .ui(new Ui())
                      .newGame()
                      .winPoint(federer)                       
                      .winPoint(federer)                       
                      .winPoint(federer)                       
                      .winPoint(federer)                       
                      .newGame()
                      .getCurrentGame() ;
        
        int curGame = 2                 ;
        assertThat( game, is(curGame))  ;
    }
    
    
    @Test
    public void shouldFederWins5Games ()  {
        
        List<String> score = play.tennis( federer, nadal )
                                 .ui(new Ui())
                                 .newGame() // Start_Game_1
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .newGame() // Start_Game_2
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .newGame() // Start_Game_3
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .newGame() // Start_Game_4
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .newGame() // Start_Game_5
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .newGame() // Start_Game_6
                                 .winPoint(federer)
                                 .winPoint(federer)
                                 .getGames() ;

        String expResult = "[Winner is Federer, " +
                            "Winner is Federer, " +
                            "Winner is Federer, " +
                            "Winner is Federer, " +
                            "Winner is Federer]"; 
                         
        assertThat( expResult, is(score.toString())) ;
    }
    
   @Test 
   public void shouldNadalWinsTheMatchAfter6Games ()      {
        
        List<String> score = play.tennis( federer, nadal )
                                 .ui(new Ui())
                                 .newGame() // Start_Game_1
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .newGame() // Start_Game_2
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .newGame() // Start_Game_3
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .newGame() // Start_Game_4
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .newGame() // Start_Game_5
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .newGame() // Start_Game_6
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .winPoint(nadal)
                                 .printScore()
                                 .getGames() ;
        
        String expResult = "[Winner is Nadal, " +
                            "Winner is Nadal, " +
                            "Winner is Nadal, " +
                            "Winner is Nadal, " +
                            "Winner is Nadal, " +
                            "Winner is Nadal]"  ;
                         
        assertThat( expResult, is(score.toString()))  ;
    }
   
   @Test 
   public void shouldNadalWinsTheMatchAfter6GamesAndExitMatchIfCallingNewGame () {
        
        exit.expectSystemExit() ;
        
        Ui ui = mock(Ui.class ) ;
        
        List<String> scoreGame = play.tennis( federer, nadal )
                                     .ui(ui)
                                     .newGame() // Start_Game_1
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_2
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_3
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_4
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_5
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_6
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .getGames()     ;
        
        verify(ui, times(1)).printEndOfTheMatch()    ;
        
        String expResult = "[Winner is Nadal, " +
                            "Winner is Nadal, " +
                            "Winner is Nadal, " +
                            "Winner is Nadal, " +
                            "Winner is Nadal, " +
                            "Winner is Nadal]"  ;
                         
        assertThat( expResult, is(scoreGame.toString())) ;
        
        /* Calling new Game Should call System Exit  */  
        play.newGame()  ;
      
    }
   
   @Test 
   public void shouldMatchLast8GamesAndNadalWinsTheMatchAndNadanWins6GamesAndFedererWins2Games () {
        
        Ui ui = mock(Ui.class ) ;
        
        List<String> scoreGame = play.tennis( federer, nadal )
                                     .ui(ui)
                                     .newGame() // Start_Game_1
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_2
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_3
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_4
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_5
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_6
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_7
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_8
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .getGames()    ;
        
        verify(ui, times(1)).printEndOfTheMatch()   ;
        
        String expResult = "[Winner is Nadal, "   +
                            "Winner is Nadal, "   +
                            "Winner is Nadal, "   +
                            "Winner is Federer, " +
                            "Winner is Nadal, "   +
                            "Winner is Nadal, "   +
                            "Winner is Federer, " +
                            "Winner is Nadal]"    ;
                         
        assertThat( expResult, is(scoreGame.toString())) ;
        
    }
   
   @Test 
   public void shouldMatchLast8GamesAndNadalWinsTheMatchAndNadanWins6GamesAndFedererWins2GamesAndExitMatchIfCallingNewGame () {
        
        exit.expectSystemExit() ;
        
        Ui ui = mock(Ui.class ) ;
        
        List<String> scoreGame = play.tennis( federer, nadal )
                                     .ui(ui)
                                     .newGame() // Start_Game_1
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_2
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_3
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_4
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_5
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_6
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .newGame() // Start_Game_7
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_8
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .winPoint(nadal)
                                     .getGames()    ;
        
        verify(ui, times(1)).printEndOfTheMatch()   ;
        
        String expResult = "[Winner is Nadal, "   +
                            "Winner is Nadal, "   +
                            "Winner is Nadal, "   +
                            "Winner is Federer, " +
                            "Winner is Nadal, "   +
                            "Winner is Nadal, "   +
                            "Winner is Federer, " +
                            "Winner is Nadal]"    ;
                         
        assertThat( expResult, is(scoreGame.toString())) ;
        
        /* Calling new Game Should call System Exit  */  
        play.newGame()  ;
      
    }

   @Test 
   public void shouldFedererWinsTheMatchAfter6GamesAndExitMatchIfCallingAnotherWinPoint () {
        
        exit.expectSystemExit() ;
        
        Ui ui = mock(Ui.class ) ;
        
        List<String> scoreGame = play.tennis( federer, nadal )
                                     .ui(ui)
                                     .newGame() // Start_Game_1
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_2
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_3
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_4
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_5
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .newGame() // Start_Game_6
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .winPoint(federer)
                                     .getGames()     ;
        
        verify(ui, times(1)).printEndOfTheMatch()    ;
        
        String expResult = "[Winner is Federer, " +
                            "Winner is Federer, " +
                            "Winner is Federer, " +
                            "Winner is Federer, " +
                            "Winner is Federer, " +
                            "Winner is Federer]"  ;
                         
        assertThat( expResult, is(scoreGame.toString())) ;
        
        /* Calling WinPoint Should call System Exit  */  
        play.winPoint(nadal) ;
      
    }

    
    @Test
    public void shouldTieBreakActivatesAfter6GamesWonByEachPlayer() {
        
       String score =   
               
       play.ui(new Ui())
           .tennis( federer, nadal )
           .newGame()           // Start_Game_1
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)   // Federer Wins
           .printScore()
           .printScoreGames()
           .newGame()           // Start_Game_2
           .winPoint(nadal)
           .printScore()
            .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)     // Nadal Wins
           .printScore()
           .printScoreGames()
           .newGame()           // Start_Game_3
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)   // Federer Wins
           .printScore()
           .newGame()           // Start_Game_4
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)     // Nadal Wins
           .printScore()
           .printScoreGames()
           .newGame()           // Start_Game_5
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)   // Federer Wins
           .printScore()
           .newGame()           // Start_Game_6
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)     // Nadal Wins
           .printScore()
           .printScoreGames()
           .newGame()           // Start_Game_7
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)   // Federer Wins
           .printScore()
           .newGame()           // Start_Game_8
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)     // Nadal Wins
           .printScore()
           .printScoreGames()
           .newGame()           // Start_Game_9
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)   // Federer Wins
           .printScore()
           .newGame()           // Start_Game_10
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)     // Nadal Wins
           .printScore()
           .printScoreGames()
           .newGame()           // Start_Game_11
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)     // Nadal Wins
           .printScore()
           .printScoreGames()
           .newGame()           // Start_Game_12
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)   // Federer Wins
           .printScore()
           .newGame()           // Start_TieBreak
           .winPoint(federer)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(nadal)
           .printScore()
           .winPoint(federer)
           .printScore()
           .winPoint(federer)   // END_MATCH_Federer_Wins
           .getScore() ;
       
        String expected = " __TieBreak__ ###  Federer 7 - Nadal 5" ;
        
        assertThat(score, is(expected) )                           ;
    }
 
    /**
     * Test of incrementScore method, of class Play.
     */
    
    @Test(expected = IllegalStateException.class )
    public void shouldRaiseExceptionBecauseOfIncrementingScoreBeforStartingNewGame()  {
        
        play.tennis(federer, nadal)
            .ui(new Ui())
            .incrementScore(nadal)                ;
        
        String score = play.getScore()            ;
    }
    
    @Test(expected = IllegalStateException.class )
    public void shouldRaisExceptionBecauseOfIncrementingScoreBeforStartingNewGame()  {
        
        play.tennis(federer, nadal)
            .ui(new Ui())
            .incrementScore(nadal)                ;
        
        String score = play.getScore()            ;
    }
    
    @Test
    public void shouldIncerementScoreAfterStartingNewGameAndNadalWinPoint() {
        
        play.tennis(federer, nadal)
            .ui(new Ui())
            .newGame()
            .incrementScore(nadal)                ;
        
        String score = play.getScore()            ;
        
        String expected = "Love - Fifteen"        ;
        
        assertThat( score, is(expected))          ;
    }

    /**
     * Test of newGame method, of class Play.
     */
    @Test
    public void shouldGameBeEmpty()                        {
        
        List<String> newGame = play.tennis(federer, nadal  )
                                  .ui(new Ui())
                                  .newGame().getGames()    ;
        
        assertThat(newGame, is(empty()))                   ;
    }
}
