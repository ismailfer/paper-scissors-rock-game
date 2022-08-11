package com.ismail;

import com.ismail.game.PaperScissorsRockGameImpl;
import com.ismail.game.SwingPlayer;

/**
 * SwingPlayer (human) vs SwingPlayer (human)
 * 
 * @author ismail
 */
public class SwingPlayerVsSwingPlayerTest
{
    public static void main(String[] args)
    {
        // Number of plays 
        int numberOfPlays = 3;

        // Define the players
        // Note; any combination is accepable; 
        // Game can be played between any two types of players            

        SwingPlayer player1 = new SwingPlayer("Swing01", 200, 200);

        SwingPlayer player2 = new SwingPlayer("Swing02", 700, 200);

        PaperScissorsRockGameImpl game = new PaperScissorsRockGameImpl();
        game.init(numberOfPlays, player1, player2);

        game.play();
    }

}
