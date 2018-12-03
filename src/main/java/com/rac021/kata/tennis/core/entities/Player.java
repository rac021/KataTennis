
package com.rac021.kata.tennis.core.entities ;

import java.util.logging.Logger ;

/**
 *  Entity Player .
 */

/**
 *
 * @author ryahiaoui
 */

public class Player {

    private static Logger LOGGER = Logger.getLogger(Player.class.getName()) ;
   
    private final String name    ;
    
    /**
     * 
     * @param name 
     */
    public Player( String name ) {
      this.name = name ;
    }
    
    /**
     * 
     * @return 
     */
    public String getName()  {
      return name ;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
      return name ;
    }
}
