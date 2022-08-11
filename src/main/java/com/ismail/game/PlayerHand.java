package com.ismail.game;

/**
 * Player Hand
 * 
 * Represents a play by the player:
 * 
 * OpenHand=Paper
 * Index and Middle Finger=Scissors
 * Fist=Rock
 * 
 * @author ismail
 */
public enum PlayerHand
{
    Paper(1), Scissors(2), Rock(3); 

    private int value;

    private PlayerHand(int value)
    {
        this.value = value;
    }

    public int intValue()
    {
        return value;
    }

    public static PlayerHand fromInt(int inputVal)
    {
        PlayerHand hand = null;

        switch (inputVal)
        {
        case 1:
            hand = Paper;
            break;

        case 2:
            hand = Scissors;
            break;
            
        case 3:
            hand = Rock;
            break;
        }

        return hand;
    }
    
    public static String valuesAsIntAndString()
    {
       String values ="1=Paper, 2=Scissors, 3=Rock";
       
       return values;
    }
}
