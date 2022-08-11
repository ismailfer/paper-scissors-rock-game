package com.ismail;

import com.ismail.game.ConsolePlayer;
import com.ismail.game.PaperScissorsRockGameImpl;
import com.ismail.game.RandomPlayer;

/**
 * Console Player (human) vs Random Player (computer)
 * 
 * @author ismail
 */
public class ConsolePlayerVsRandomPlayerTest
{
    public static void main(String[] args)
    {
        // Number of plays 
        int numberOfPlays = 3;

        // Define the players
        // Note; any combination is accepable; 
        // Game can be played between any two types of players            

        ConsolePlayer player1 = new ConsolePlayer("Console01");

        RandomPlayer player2 = new RandomPlayer("Random01", System.currentTimeMillis());

        PaperScissorsRockGameImpl game = new PaperScissorsRockGameImpl();
        game.init(numberOfPlays, player1, player2);

        game.play();

    }

}
