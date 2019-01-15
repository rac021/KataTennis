
package com.rac021.kata.tennis.service ;

import java.util.List ;
import java.util.HashMap ;
import java.util.logging.Level ;
import java.util.logging.Logger ;
import java.util.function.Consumer ;
import com.rac021.kata.tennis.ui.Ui ;
import com.rac021.kata.tennis.core.entities.Player ;

/**
 *
 * @author ryahiaoui
 */

 /**
  * When Player win a point,the method winPoint is called
  * The score operations are delegate to the class ScoreService 
 *  Nb : Only Exceptions are Logged .
 */

public class Play           {

    private Player PLAYER_1 ;
    
    private Player PLAYER_2 ;
    
    private Ui     ui       ;
    
    private final ScoreService scoreService = new ScoreService() ;
    
    /* Save The Function tha will be called on each Win Point   */
    private final HashMap<Class<Player>, Consumer<Player>> INCREMENT_SCORE_FUNCTION = new HashMap<>() ;
    
    private static final Logger LOGGER = Logger.getLogger(Play.class.getName()) ;

    /**
     * 
     * @param player1
     * @param player2
     * @return Play
     */
    public Play tennis( Player player1, Player player2 ) {
        
        this.PLAYER_1 = player1        ;
        this.PLAYER_2 = player2        ;
        return this                    ;
    }
    
    /**
     * 
     * @param type
     * @param fun
     * @return Play after registering the function of calculating score
     */
    public Play whenWinPoint( Class<Player> type , Consumer< Player> fun) {
      INCREMENT_SCORE_FUNCTION.put(type, fun::accept ) ;
      return this                                      ;
    }
    
    /**
     * 
     * @param player
     * @return Play after player win POINT
     */
    public Play winPoint( Player player )        {
        
        if( scoreService.getCurrentGame() == 0 ) {
            
            LOGGER.log( Level.SEVERE ,
                        "Begin a new Game before Start Playing" ) ;
            
            throw new IllegalStateException     (
                      " Begin a new Game before Start Playing " ) ;
        }
      
        /* Match Ends => Exit */
        if ( scoreService.isEndMatch() )                     {

            ui.printEndOfTheMatch()                          ;
            printScoreGames()                                ;
            ui.printNewLine()                                ; 
            System.exit( 0 )                                 ;
        }
          
        /* IsTaeBreak || Game not End => Increment Score    */
        if ( ! scoreService.isEndGame () || 
               scoreService.isTieBreak()  )                  {
            
            Consumer<Player> consumer = INCREMENT_SCORE_FUNCTION.get( Player.class ) ;
            consumer.accept( player )                                                ;
                        
        } else                       { 
            
          printFinishedGames()       ;
        }
        
        /** DISPLAYING The Last Score of the Match. */
       
        /* Display TieBreak Score */
        
        if ( scoreService.isEndMatch() && scoreService.isTieBreak() )  {
            
            String score   = scoreService.get( PLAYER_1.getName()      ,
                                               PLAYER_2.getName() )    ;
            ui.print(score)                                            ;
            ui.printNewLine()                                          ;
            ui.printEndOfTheMatch()                                    ;
            return this                                                ;
        }
        
        /* Display "Simple" Score */
         
        else if ( scoreService.isEndMatch() )                          {
          
            String score   = scoreService.get( PLAYER_1.getName()      ,
                                               PLAYER_2.getName() )    ;
       
            ui.printCurrentScore ( getCurrentGame() , score )          ;
            printScoreGames()                                          ;
            ui.printEndOfTheMatch()                                    ;
        
        }   

        /* Check AND Update TieBreak */
     
        if ( ! scoreService.isTieBreak() ) {
            
          /* Enable tieBreak if p1ScoreGames == p2ScoreGames == 6 */
        
          Boolean tieBreak = scoreService.checkTieBreak ( PLAYER_1.getName() ,
                                                          PLAYER_2.getName() ) ;
          if ( tieBreak )                                                    {
              printScore()                                                   ;
              scoreService.releaseScores()                                   ;
              ui.printEnableTieBreak()                                       ;
              scoreService.senableTieBreak( )                                ;
          }

        }
        
        return this  ;
    }
    
    /**
     * 
     * @param player 
     */
    public void incrementScore( Player player )         {
       
       /* If Game already started  */
        
       if( ! scoreService.isEndGame() || 
             scoreService.isTieBreak() )                {
           
            if( player == PLAYER_1 )                    {
                 scoreService.incrementScorePlayerOne() ;
            } else {
                 scoreService.incrementScorePlayerTwo() ;
            }

            String checkIfAplayerWinTheGame = scoreService.get( PLAYER_1.getName()   ,
                                                                PLAYER_2.getName() ) ; 

            if ( scoreService.isEndGame() )             {
                
                /*  Save Winner in the list of the Games  */
                
                scoreService.saveWinnerGame( checkIfAplayerWinTheGame )   ;
            }

       } else {
           
           ui.printBeginNewGame()                                         ;

           LOGGER.log( Level.SEVERE , " You have to Begin A new Game "  ) ;
           throw new IllegalStateException( " Begin A new Game "        ) ;
       }
    }
    
    /**
     * 
     * @return Score
     */
    public String getScore()                          {
        
        return scoreService.get( PLAYER_1.getName()   ,
                                 PLAYER_2.getName() ) ;
    }
    
    public Play ui( Ui ui) {
        this.ui = ui       ;
        return  this       ;
    }
    
    /**
     * 
     * @return Play after creating new Game
     */
    public Play newGame()                              {
        
        if( scoreService.isTieBreak() ) return this    ;
        
        boolean newGame = scoreService.tryNewGame()    ;
        
        if( ! newGame )                                {
            ui.printEndOfTheMatch()                    ;
            System.exit( 0 )                           ;
        }
        
        ui.printStartingNewGameInfo (getCurrentGame()) ;
        
       return  this                                    ;
    }
    
    /**
     * If Match Finished then Print Score Games
     * else Print current Score
     * @return Play after printing score
     */
    public Play printScore()                                    {
        
      if( scoreService.isTieBreak() &&
          ! scoreService.isFinishedMatch())                     {
        String score   = scoreService.get( PLAYER_1.getName()   ,
                                           PLAYER_2.getName() ) ;
        ui.print(score)                                         ;
        return this                                             ;
      }
      
      if( scoreService.isTieBreak() &&
          scoreService.isFinishedMatch())                       {
        String score   = scoreService.get( PLAYER_1.getName()   ,
                                           PLAYER_2.getName() ) ;
        ui.printNewLine()                                       ;
        ui.printStars()                                         ;
        ui.print(score)                                         ;
        ui.printStars()                                         ;
        ui.printNewLine()                                       ;
        return this                                             ;
      }
      
      if( ! scoreService.isFinishedMatch() )                    {
          
        String score   = scoreService.get( PLAYER_1.getName()   ,
                                           PLAYER_2.getName() ) ;
       
        ui.printCurrentScore ( getCurrentGame() , score )       ;
        
      }

      else   /* Print Finished Match + Score Games */           {
            ui.printEndOfTheMatch()                             ;
            printScoreGames()                                   ;
            return this                                         ;
      }
        
        return this                                             ;
    }
    
    /**
     * 
     * @return Play After printing Score Games  
     */
    public Play printScoreGames()                             {

        ui.println (
          scoreService.getScoreWinerGames( PLAYER_1.getName() , 
                                           PLAYER_2.getName() ) 
        )                                                     ;
        
        return this                                           ;
    }
    
    /**
     * 
     * @return Play After printing Finished Games  
     */
    public Play printFinishedGames()                         {

       ui.printFinishedGame(scoreService.getWinerGames()   ) ;
       return this                                           ;
    }
    
    /**
     * 
     * @return List of the Games played 
     */
    public List<String>  getGames()         {
        
       return scoreService.getWinerGames()  ;
    }
    
    /**
     * 
     * @return current Game
     */
    public int getCurrentGame()              {
        return scoreService.getCurrentGame() ;
    }
    
  }
