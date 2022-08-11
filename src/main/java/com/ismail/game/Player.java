package com.ismail.game;

/**
 * Player interface
 * 
 * Implementation can be of any type; such as:
 * - ConsolePlayer
 * - RandomPlayer
 * - SwingPlayer
 * - OnlinePlayer,
 * - etc
 * 
 * @author ismail
 */
public interface Player
{
    /**
     * Player ID
     */
    String getPlayerID();

   
    /**
     * Player to play the game
     * 
     * @param playDesc Play description (i.e. player number)
     * @return the input of the player
     */
    PlayerHand play(String playDesc);

    /**
     * After the play is played by all players; the game will notify the user by this call
     * 
     * @param play description (i.e. round number)
     * @param score of this player (0 or 1)
     * @param winnerID PlayerID who won this play (Null means no one won the game)
     */
    void notifyOfPlayScore(String playDesc, int score, String winnerID);
    
    /**
     * After the game is played by all rounds; the game will notify the user with the game score
     * 
     * @param score of this player (0 or 1)
     * @param winnerID PlayerID who won this play (Null means no one won the game)
     */
    void notifyOfGameScore(int score, String winnerID);
    
    /**
     * Called when the game has started
     * 
     * @param gameDesc game description (i.e. player1 vs player2)
     */
    void onGameStarted(String gameDesc);
    
    /**
     * Called when the game  has ended
     */
    void onGameEnded();

}
