
 package com.rac021.kata.tennis.ui ;

 import java.util.List ;
 import java.util.logging.Logger ;

 /**
 *  Ui is responsible for displaying messages ( It can be a GUI )
 *  Nb : Only Exceptions are Logged .
 */
 
 /**
  *
  * @author ryahiaoui
 */
 
 public class Ui {

    private static Logger LOGGER = Logger.getLogger(Ui.class.getName()) ;
   
    public Ui() {
      System.out.println(" \n  **** Tennis Game ***** " ) ;
    }

    /**
     * 
     * @param message 
     */
    public void print(String message )    {
       System.out.println(message)        ;
    }
    
    /**
     * 
     * @param message 
     */
    public void println(String message ) {
        printNewLine()                   ;
        System.out.print( message  )     ;
        printNewLine()                   ;
    }

    public void printEndOfTheMatch()  {
        
      print( " ******************** " ) ;
      print( " *** END_OF_MATCH *** " ) ;
      print( " ******************** " ) ;
    }
    
    /**
     * 
     * @param games 
     */
    public void printNotStartedGame ( List games )               {
        
       print( " \n--------------------------------------    "  ) ;
       print( " Game Not Started. Please Begin a new Game    " ) ;
       print( " Score Game :  " + games                        ) ;
       print( " -------------------------------------       "  ) ;
    }
    
    /**
     * 
     * @param games 
     */
    public void printFinishedGame ( List games )                 {
        
       print( " \n--------------------------------------    "  ) ;
       print( " Game finished. Please Begin a new Game      "  ) ;
       print( " Score Games :  " + games                       ) ;
       print( " -------------------------------------       "  ) ;
    }
    
     /**
     * 
     * @param currentGame 
     */
    public void printStartingNewGameInfo( int currentGame )      {
        
     print ( " \n Starting a new Game : " + currentGame + "\n" ) ;
      
    }
    
    /**
     * 
     * @param currentGame
     * @param score 
     */
    public void printCurrentScore( int currentGame , String score )  {
        
        print( " ## " + " Score { Game : " + currentGame  + " } -- " +
               score + " -- "            )                           ;
    }
    
    /**
     * 
     * @param playerOne
     * @param PlayerTwo 
     */
    public void printPlayers( String playerOne, String PlayerTwo )    {
        
      print ( "  ------------------------ "                         ) ;
      print ( " / " + playerOne + " ** VS ** " + PlayerTwo + " \\ " ) ;
      print ( " ---------------------------  "                      ) ;
     
    }
     
    public void printEnableTieBreak()                             {
      System.out.println ("\n ################################" ) ;
      print              (" ## XXXX TieBreak Enabled XXXX ##"   ) ;  
      System.out.println (" ################################\n" ) ;
    }
    /**
     * 
     * @param games 
     */
    public void printGame( List games )            {
        
      print( " \n **************************** " ) ;
      print( " ++  GAMES : " + games + " ++   "  ) ;
      print( " ****************************  \n" ) ;
    }
   
    public void printBeginNewGame()                {
      print( " \n Please begin a new Game  \n " )  ;
    }
    
    public void printNewLine()                     {
      print( " \n                            "  )  ;
    }

    public void printStars() {
        System.out.println(" *******************************  ") ;
    }

 }
