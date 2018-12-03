
package com.rac021.kata.tennis ;

import com.rac021.kata.tennis.ui.Ui ;
import com.rac021.kata.tennis.service.Play ;
import com.rac021.kata.tennis.core.entities.Player ;


public class Match                              {

  public static void main(String[] args)        {
     
    Player nadal   = new Player("Nadal")        ;
    Player federer = new Player("Federer")      ;
    
    Ui ui = new Ui()                            ;
    
    Play tennis = new Play().tennis( nadal, federer )
                            .ui(ui) ;
    
    /* Visitor Pattern in java 8 */
    tennis.whenWinPoint( Player.class , tennis::incrementScore ) ;
    
    ui.printPlayers( nadal.getName(), federer.getName() )        ;
   
    /* Fluent Implementation */
     
    tennis.newGame()           // Start_Game_1
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
          .newGame()           // *** Start_Tie-Break ***
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
          .printScore()  ;
  }
}

