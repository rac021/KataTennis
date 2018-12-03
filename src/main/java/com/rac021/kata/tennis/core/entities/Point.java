
package com.rac021.kata.tennis.core.entities ;

/**
 *  Enum of Scores .
 */

/**
 *
 * @author ryahiaoui
 */

public enum Point      {
    
        Love    (0)    ,
        
        Fifteen (15)   , 
        
        Thirty  (30)   ,
        
        Forty   (40)   ;
        
        private final int numVal ;
        
        Point ( int numVal )     {
            this.numVal = numVal ; 
        }
        
        public int getNumVal()   {
            return numVal        ;
        }
}
