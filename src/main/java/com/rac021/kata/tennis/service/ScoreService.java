
package com.rac021.kata.tennis.service ;

import java.util.Map ;
import java.util.List ;
import java.util.Arrays ;
import java.util.Iterator ;
import java.util.ArrayList ;
import java.util.logging.Level ;
import java.util.logging.Logger ;
import java.util.function.Function ;
import java.util.stream.Collectors ;
import com.rac021.kata.tennis.core.entities.Point ;

/**
 * Calculate Scores and register a list of the Games won by players
 *  Nb : Only Exceptions are Logged .
 */

/**
 *
 * @author ryahiaoui
 */

public class ScoreService  {
    
    private static Logger LOGGER = Logger.getLogger(ScoreService.class.getName())    ;
    
    private static final String   WINNER    =  ScoreService.WINNER_IS + "{{player}}" ;
    
    private static final String   VANTAGE   = "Advantage {{player}}" ;

    private static final String   WINNER_IS = "Winner is "           ;
    
    private       int             current_game                       ;
    
    private final List<String>    WINNER_GAMES                       ;
    
    private final Integer[]       scores                             ;
     
    private       Boolean         endGame       = true               ;
    private       Boolean         endMatch      = false              ;
    
    private       Boolean         tieBreak      = false              ;

    
    public ScoreService()              {
        
      scores  = new Integer[2]         ;
      Arrays.setAll(scores, p -> 0 )   ;
      WINNER_GAMES = new ArrayList<>() ;
      current_game = 0                 ;
    }
    
    /**
     * 
     * @param pOneName
     * @param pTwoName
     * @return score
     */
    public String get ( String  pOneName   ,
                        String  pTwoName ) {
        
       Integer pOnePoint = scores[0] ; 
       
       Integer pTwoPoint = scores[1] ; 
     
       if( tieBreak ) return tieBreakScore( pOneName, pTwoName )               ;
       
       if ( pOnePoint.equals(pTwoPoint ) ) return deuce(pOnePoint)             ;
        
       if ( pOnePoint > 3 || pTwoPoint > 3 ) 
            return winOrAdvantage ( pOnePoint, pTwoPoint, pOneName, pTwoName ) ;
       
       return defaultScore( pOnePoint, pTwoPoint )                             ;
    }

    /**
     * 
     * @param point
     * @return deuce
     */
    private static String deuce ( Integer point )     {
        
        return ( point < 3 ) ?
               getScoresInLetters(point) + " -  ALL " :
               "Deuce"                                ;
    }

    /**
     * 
     * @param playerOnePoint
     * @param playerTwoPoint
     * @param playerOneName
     * @param playerTwoName
     * @return score win or advantage
     */
    private String winOrAdvantage( Integer playerOnePoint , 
                                   Integer playerTwoPoint , 
                                   String  playerOneName  , 
                                   String  playerTwoName  ) {
        
       return ( Math.abs ( playerOnePoint - playerTwoPoint) >= 2 ) ?
         win       ( playerOnePoint,  playerTwoPoint, playerOneName , playerTwoName ) :
         advantage ( playerOnePoint,  playerTwoPoint, playerOneName , playerTwoName ) ;
    }

    /**
     * 
     * @param pOnePoint
     * @param pTwoPoint
     * @param pOneName
     * @param pTwoName
     * @return score Win
     */
    private  String win( Integer pOnePoint , 
                         Integer pTwoPoint ,
                         String  pOneName  ,
                         String  pTwoName  ) {
        
        endGame = true ;
        
        return ( pOnePoint > pTwoPoint ) ?
                 WINNER.replace("{{player}}", pOneName) :
                 WINNER.replace("{{player}}", pTwoName) ;
    }

    /**
     * 
     * @param pOnePoint
     * @param pTwoPoint
     * @param pOneName
     * @param pTwoName
     * @return score Advantage
     */
    private String advantage( Integer pOnePoint, 
                              Integer pTwoPoint, 
                              String  pOneName , 
                              String  pTwoName ) {
        
        return ( pOnePoint > pTwoPoint ) ?
               VANTAGE.replace ( "{{player}}", pOneName )  :
               VANTAGE.replace ( "{{player}}", pTwoName  ) ;
    }

    /**
     * 
     * @param pOnePoint
     * @param pTwoPoint
     * @return the score of the two players
     */
    private static String defaultScore( Integer pOnePoint , 
                                        Integer pTwoPoint )  {
        
        return getScoresInLetters(pOnePoint) +   " - "       +
               getScoresInLetters(pTwoPoint )                ;
    }
    
    /**
     * 
     * @param point
     * @return score In Numbers 
     */
    private static int getScoresInNumbers(int point)         {
       return Arrays.asList (Point.values())
                    .get( point ).getNumVal() ;
    }
    
    /**
     * Can be used if we want to display the Score in 
     * letters instead of numbers 
     * @param point
     * @return Score in Letters 
     */
    private static String getScoresInLetters(int point)      {
       return Arrays.asList (Point.values())
                    .get( point ).name()   ;
    }
    
    private void releaseGame() {
        this.endGame = false   ;
    }
    
    /**
     * 
     * @return finishedGame
     */
    public boolean isFinishedGame() {
        return this.endGame         ;
    }

    /**
     * 
     * @return True of new Game Authorized
     */
    public boolean tryNewGame()   {
    
      if( endGame == true  )      {
       
        if(  endMatch() ) {
            return  false ;
        }
        releaseGame()     ;
        releaseScores()   ;
        current_game ++   ;
        return true       ;
        
      }
      else {
          LOGGER.log( Level.SEVERE, "Current Game not already finished '{' {0} '}'", 
                      current_game) ;
          
          throw new IllegalStateException ( 
                    "Current Game not already finished { " +
                    current_game + " }" )                  ;
       }
    }
    
    /**
     * 
     * @return currentGame
     */
    public int getCurrentGame()  {
       return current_game ;
    }

    /**
     * 
     * @return List Win_Games
     */
    public List<String> getWinerGames()  {
        return WINNER_GAMES              ;
    }
    
    /**
     * 
     * @param p1
     * @param p2
     * @return List Win_Games
     */
    public String getScoreWinerGames( String p1, String p2 )        {
        
      Map<String, Long> scoresGames = WINNER_GAMES.stream().collect (
                  Collectors.groupingBy (
                        Function.identity(), Collectors.counting()
                )
      ) ;
      
      return " ||   " + p1  +  " "                                  + 
             getScorePlayer(scoresGames, p1 )                       +
             "  -  " + p2 + " "                                     +
             getScorePlayer(scoresGames, p2 )                       +
             "  || "                                                ;
    }
    
    public void incrementScorePlayerOne() {
        scores[0] = ++scores[0]     ;
    }
    
    public void incrementScorePlayerTwo() {
        scores[1] = ++scores[1]     ;
    }
    
    public void releaseScores()     {
        scores[0] = scores[1] = 0   ;
    }

    void saveWinnerGame(String player) {
        this.WINNER_GAMES.add(player)  ;
    }

    
    /**
     * @return END_MATCH where 
     * TieBreak Activated 
     * AND ScoreAnyPlayer >= 6 & dif ( Abs ( Score_Player_1 - Score_Player_2 ) >= 2
     * OR
     * NOT TieBreak & Total_GAMES >= 6 &  
     * dif ( Abs ( Games_Won_By_Player_1 - Games_Won_By_Player_2 )  >= 6 ) .
     */
    public boolean endMatch()         {
        
        if( endMatch ) return true    ;
        
        Map<String, Long> scoresGames = WINNER_GAMES.stream().collect (
                Collectors.groupingBy (
                        Function.identity(), Collectors.counting()
                )
        ) ;
        
        if( tieBreak )                                             {
          
            int scoreOne =  scores[0]                              ;
            int scoreTwo =  scores[1]                              ;
            
            endMatch = ( Math.abs ( scoreOne - scoreTwo ) >= 2 ) &&
                       ( scoreOne >= 6  ||  scoreTwo >= 6     )    ;
            return endMatch                                        ;
        }
         
        if( scoresGames.values().isEmpty() ) return false    ;
        
        /* On Player won 6 Games , Second Player won 0 Game */
        if( scoresGames.values().size()  == 1 &&
            scoresGames.values().iterator().hasNext() ) {
            
           endMatch =  scoresGames.values().iterator().next() >= 6  && 
                       isFinishedGame() ;
        }
              
        if( scoresGames.values().size()  == 2 ) {
           
            Iterator<Long> iter = scoresGames.values().iterator();
            
            Long scoreOne = iter.next() ;
            Long scoreTwo = iter.next() ;
            
           endMatch = ( Math.abs( scoreOne - scoreTwo ) >= 2 ) &&
                      ( scoreOne >= 6  ||  scoreTwo >= 6     ) && 
                      isFinishedGame()                          ;
        }
       
       return endMatch && isFinishedGame()  ;
    }
    
    /**
     * 
     * @return finishedMatch
     */
    public boolean isFinishedMatch() {
      return endMatch ;  
    }
    
    /**
     * 
     * @return scorePlayerOne
     */
    public int getScorePlayerOne() {
        return scores[0] ;
    }
    
    /**
     * 
     * @return scorePlayerTwo
     */
    public int getScorePlayerTwo() {
        return scores[1]           ;
    }
    
    /**
     * 
     * @param scoresGames
     * @param player
     * @return The score Game of the player 
     */
    private String getScorePlayer(  Map<String, Long> scoresGames , 
                                    String            player    ) {
        
       return  scoresGames.get( WINNER_IS + player ) != null  ?
                     String.valueOf ( 
                        scoresGames.get( WINNER_IS + player ) )   :
                     "0"                                          ;  
    }
    
    private String tieBreakScore( String player1 , String player2 )  {
        return " __TieBreak__ ###  " + player1 + " " + scores[0]     +
               " - "  + player2  +  " "       +   scores[1]          ;
    }

    public Boolean checkTieBreak( String player1, String player2)   {
        
      if( tieBreak ) return true                                    ; 
      
      Map<String, Long> scoresGames = WINNER_GAMES.stream().collect (
                  Collectors.groupingBy (
                        Function.identity(), Collectors.counting()
                )
      ) ;
      
      int scorePlayer1 = Integer.parseInt(getScorePlayer(scoresGames, player1 )) ; 
      int scorePlayer2 = Integer.parseInt(getScorePlayer(scoresGames, player2 )) ; 
      
      return ( ( scorePlayer1 == scorePlayer2 ) &&  scorePlayer2 == 6 )          ;

    }

    public Boolean isTieBreak() {
        return tieBreak         ;
    }

    public void setTieBreak(Boolean tieBreak) {
        this.tieBreak = tieBreak              ;
    }
    
    
}

