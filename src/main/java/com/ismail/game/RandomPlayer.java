package com.ismail.game;

import java.util.Random;

/**
 * Random Implementaiton of the Player
 * 
 * Plays with random input
 * 
 * @author ismail
 */
public class RandomPlayer extends PlayerBase
{
    // random generator 
    private Random random = null;

    public RandomPlayer(String playerID)
    {
        super(playerID);

        random = new Random();
    }

    /**
     * Specify the Random seed to control the random generator
     * 
     * @param playerID
     * @param randomSeed
     */
    public RandomPlayer(String playerID, long randomSeed)
    {
        super(playerID);

        random = new Random(randomSeed);
    }

    @Override
    public PlayerHand play(String playDesc)
    {
        int inputVal = random.nextInt(3) + 1;

        PlayerHand playerHand = PlayerHand.fromInt(inputVal);

        return playerHand;
    }

    @Override
    public void notifyOfPlayScore(String playDesc, int score, String winnerID)
    {
        // do nothing for this player
    }

    @Override
    public void notifyOfGameScore(int score, String winnerID)
    {
        // do nothing for this player
    }
}
