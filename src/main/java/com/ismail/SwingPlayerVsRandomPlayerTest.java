package com.ismail;

import com.ismail.game.PaperScissorsRockGameImpl;
import com.ismail.game.RandomPlayer;
import com.ismail.game.SwingPlayer;

/**
 * Swing Player (human) vs Random Player (computer)
 * 
 * @author ismail
 */
public class SwingPlayerVsRandomPlayerTest
{
    public static void main(String[] args)
    {
        // Number of plays 
        int numberOfPlays = 3;

        // Define the players
        // Note; any combination is accepable; 
        // Game can be played between any two types of players            

        SwingPlayer player1 = new SwingPlayer("Swing01", 200, 200);

        RandomPlayer player2 = new RandomPlayer("Random01", System.currentTimeMillis());

        PaperScissorsRockGameImpl game = new PaperScissorsRockGameImpl();
        game.init(numberOfPlays, player1, player2);

        game.play();

    }

}
