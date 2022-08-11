package com.ismail;

import com.ismail.game.PaperScissorsRockGameImpl;
import com.ismail.game.RandomPlayer;

/**
 * Random Player (computer) vs Random Player (computer)
 * 
 * @author ismail
 */
public class RandomPlayerVsRandomPlayerTest
{
    public static void main(String[] args)
    {
        // Number of plays 
        int numberOfPlays = 3;

        // Define the players
        // Note; any combination is accepable; 
        // Game can be played between any two types of players            

        RandomPlayer player1 = new RandomPlayer("Random01", System.currentTimeMillis());

        RandomPlayer player2 = new RandomPlayer("Random02", System.nanoTime());

        PaperScissorsRockGameImpl game = new PaperScissorsRockGameImpl();
        game.init(numberOfPlays, player1, player2);

        game.play();

    }

}
