package com.ismail.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Console Implementation of the Player
 * 
 * Uses console to request for user input
 * 
 * @author ismail
 */
public class ConsolePlayer extends PlayerBase
{

    public ConsolePlayer(String playerID)
    {
        super(playerID);
    }

    @Override
    public PlayerHand play(String playDesc)
    {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        PlayerHand playerHand = null;

        // Ask for user input; and validate it
        // If user input invalid values; ask again until we get it
        while (true)
        {
            System.out.print(playDesc + ": Enter (" + PlayerHand.valuesAsIntAndString() + "): ");

            try
            {
                String name = reader.readLine().trim();

                if (name.length() > 0)
                {
                    int inputVal = Integer.parseInt(name);

                    playerHand = PlayerHand.fromInt(inputVal);

                    // valid input: exit the loop
                    if (playerHand != null)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Input value out of range!");
                    }
                }
                else
                {
                    System.out.println("Please input a value!");
                }
            }
            catch (Exception e)
            {
                System.out.println("Invalid input value; please enter a number!");
            }
        }

        return playerHand;
    }

    @Override
    public void notifyOfPlayScore(String playDesc, int score, String winnerID)
    {
        String output = null;
        
        if (playerID.equals(winnerID))
        {
            output = "You have won this play!";
        }
        else if (winnerID != null)
        {
            output = "You have lost against " + winnerID;
        }
        else
        {
            output = "You are even";
        }
        
        System.out.println(output);
        System.out.println();
    }

    @Override
    public void notifyOfGameScore(int score, String winnerID)
    {
        String output = null;
        
        if (playerID.equals(winnerID))
        {
            output = "You have won this GAME with " + score + " points";
        }
        else if (winnerID != null)
        {
            output = "You have lost this GAME against " + winnerID;
        }
        else
        {
            output = "You are even on this GAME";
        }
        
        System.out.println(output);
        System.out.println();
    }
    
    @Override
    public void onGameStarted(String gameDesc)
    {
        System.out.println("************************************************************************");   
        System.out.println(gameDesc);
        System.out.println("************************************************************************");   
    }
}
