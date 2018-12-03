# KataTennis

 Kata_Tennis with fluent implementation based on the Visitor Design Pattern in Java 8 ( Method reference )
  
  
| Branch    | build status  |
|-----------|---------------|
| [master](https://github.com/rac021/KataTennis/tree/master)  |[![Build Status](https://travis-ci.org/ontop/ontop.svg?branch=master)](https://travis-ci.org/rac021/KataTennis)|
 
 #### Sample code :   [**Match.java**]( https://github.com/rac021/KataTennis/blob/master/src/main/java/com/rac021/kata/tennis/Match.java) 
  
 
    /* Fluent Implementation */
    
    new Play().tennis( nadal, federer )
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
              .newGame()           // *** Start_TieBreak ***
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
              
              
 #### Output :

![tennis_score](https://user-images.githubusercontent.com/7684497/49354806-f1780e00-f6c4-11e8-85e3-ce37c46f7233.png)

