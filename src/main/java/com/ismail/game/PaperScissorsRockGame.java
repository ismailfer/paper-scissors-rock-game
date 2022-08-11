package com.ismail.game;

/**
 * Paper Scissors Rock Game
 * 
 * Allows two players to play against each other N times
 * 
 * Player is abstracted; and can be of any type; such as:
 * - RandomPlayer (represents a computer playing randomly, with optional random seed)
 * - ConsolePlayer (represents a human player using the console as input)
 * - SwingPlayer (represents a human player using a Swing frame)
 * 
 * Other players can be added; such as:
 * - RestAPIPlayer: a player using RestAPI over the network
 * - OnlinePlayer: any other network protocol; such as chats, email, etc
 * 
 * Game can be setup by any of the above players; i.e.:
 * - RandomPlayer vs RandomPlayer (computer vs computer)
 * - RandomPlayer vs ConsolePlayer (computer vs human)
 * - ConsolePlayer vs ConsolePlayer (human vs human)
 * - ConsolePlayer vs OnlinePlayer (human using console vs human who is online)
 * - SwingPlayer vs ConsolPlayer
 * - etc
 * 
 * Future Improvements:
 * - The game implementation can also be an online game running on a webserver; or as a standalone server by itself
 * 
 * @author ismail
 */
public interface PaperScissorsRockGame
{

    /**
     * Initialize the game with the number of plays and the players
     * 
     * @param numberOfPlays
     * @param player1
     * @param player2
     */
    void init(int numberOfPlays, Player player1, Player player2);

    /**
     * Evaluate a player hands for two players
     * 
     * @param play1 input for player #1 
     * @param play2 input for player #2 
     * @return the Player who won this play. Null means no one won (Even)
     */
    Player evaluatePlayerHands(PlayerHand play1, PlayerHand play2);

    /**
     * Play the game
     */
    void play();
    
    /**
     * Return the winner of this game after the game has ended
     * 
     * @return winner of the game; Null means no one won (even)
     */
    Player getWinner();


}
