package com.ismail.game;

/**
 * Simple implementation of the PaperScissorsRockGame
 * 
 * @author ismail
 */
public final class PaperScissorsRockGameImpl implements PaperScissorsRockGame
{
    public int numberOfPlays;

    public Player player1;

    public Player player2;

    public Player winner;

    public PaperScissorsRockGameImpl()
    {
    }
    
    @Override
    public void init(int numberOfPlays, Player player1, Player player2)
    {
        if (numberOfPlays < 1)
            throw new IllegalArgumentException("Minimum of 1 play is required!");

        this.numberOfPlays = numberOfPlays;
        this.player1 = player1;
        this.player2 = player2;

    }

    @Override
    public Player evaluatePlayerHands(PlayerHand playerHand1, PlayerHand playerHand2)
    {
        if (playerHand1 == null)
            throw new IllegalArgumentException("Invalid player hand for player #1");

        if (playerHand2 == null)
            throw new IllegalArgumentException("Invalid player hand for player #2");

        Player winner = null;

        if (playerHand1 == PlayerHand.Paper)
        {
            if (playerHand2 == PlayerHand.Paper)
            {
                winner = null;
            }
            else if (playerHand2 == PlayerHand.Rock)
            {
                winner = player1;
            }
            else if (playerHand2 == PlayerHand.Scissors)
            {
                winner = player2;
            }

        }
        else if (playerHand1 == PlayerHand.Rock)
        {
            if (playerHand2 == PlayerHand.Paper)
            {
                winner = player2;
            }
            else if (playerHand2 == PlayerHand.Rock)
            {
                winner = null;
            }
            else if (playerHand2 == PlayerHand.Scissors)
            {
                winner = player1;
            }
        }
        else if (playerHand1 == PlayerHand.Scissors)
        {
            if (playerHand2 == PlayerHand.Paper)
            {
                winner = player1;
            }
            else if (playerHand2 == PlayerHand.Rock)
            {
                winner = player2;
            }
            else if (playerHand2 == PlayerHand.Scissors)
            {
                winner = null;
            }
        }

        return winner;
    }

    @Override
    public Player getWinner()
    {
        return winner;
    }
    
    @Override
    public void play()
    {
        // ----------------------------------------------------------------------------------
        // Notify players of this game starting
        // ----------------------------------------------------------------------------------
        String gameDesc = "PaperRockScissors " + player1.getPlayerID() + " vs " + player2.getPlayerID();
        
        player1.onGameStarted(gameDesc);
        player2.onGameStarted(gameDesc);

        int player1ScoreSum = 0;
        int player2ScoreSum = 0;

        for (int i = 0; i < numberOfPlays; i++)
        {
            // play description for this round
            String playDesc = "Play [" + (i + 1) + " of " + numberOfPlays + "]";

            PlayerHand playerHand1 = null;
            PlayerHand playerHand2 = null;

            // Request players to play; if any input is invalid; repeat this round
            while (true)
            {
                // Send a concurrent requests to each player
                PlayRequestWorker playRequestWorker1 = new PlayRequestWorker(playDesc, player1);
                PlayRequestWorker playRequestWorker2 = new PlayRequestWorker(playDesc, player2);

                playRequestWorker1.start();
                playRequestWorker2.start();

                try
                {
                    playRequestWorker1.join();
                    playRequestWorker2.join();

                    playerHand1 = playRequestWorker1.playHand;
                    playerHand2 = playRequestWorker2.playHand;
                }
                catch (InterruptedException ie)
                {

                }

                // Validate the player's hands; if one of them is invalid; then repeat this round
                if (playerHand1 != null && playerHand2 != null)
                    break;
                    
            }

            // Evaluate the player hands
            Player roundWinner = evaluatePlayerHands(playerHand1, playerHand2);

            int player1Score = 0;
            int player2Score = 0;

            // player 1 wins
            if (roundWinner == player1)
            {
                player1Score = 1;
            }
            // player 2 wins
            else if (roundWinner == player2)
            {
                player2Score = 1;
            }
            // even
            else
            {

            }

            // increment the scores
            player1ScoreSum += player1Score;
            player2ScoreSum += player2Score;

            String winnerPlayerID = roundWinner != null ? roundWinner.getPlayerID() : null;

            // Notify players of their score for this play
            player1.notifyOfPlayScore(playDesc, player1Score, winnerPlayerID);
            player2.notifyOfPlayScore(playDesc, player2Score, winnerPlayerID);

        }

        // ------------------------------------------------------------------------------------
        // Assess who won the game
        // ------------------------------------------------------------------------------------
        if (player1ScoreSum > player2ScoreSum)
        {
            winner = player1;
        }
        else if (player2ScoreSum > player1ScoreSum)
        {
            winner = player2;
        }
        else
        {
            // even
        }

        String winnerPlayerID = winner != null ? winner.getPlayerID() : null;

        // Notify players of the game score
        player1.notifyOfGameScore(player1ScoreSum, winnerPlayerID);
        player2.notifyOfGameScore(player2ScoreSum, winnerPlayerID);

        // Print game output to the console
        if (winner != null)
            System.out.println("Game finished: Winner is " + winner.getPlayerID());
        else
            System.out.println("Game finished: No winner!");

        // ----------------------------------------------------------------------------------
        // Notify players of this game ending
        // ----------------------------------------------------------------------------------
        player1.onGameEnded();
        player2.onGameEnded();
        
    }

    /**
     * Thread used to request players to play asynchronously
     * then wait for their response
     */
    public class PlayRequestWorker extends Thread
    {
        private String playDesc = null;

        private Player player;

        public PlayerHand playHand = null;

        public PlayRequestWorker(String playDesc, Player player)
        {
            this.playDesc = playDesc;
            this.player = player;

            setName(getClass().getName() + "-" + player.getPlayerID());
            setDaemon(true);
        }

        public void run()
        {
            try
            {
                playHand = player.play(playDesc);
            }
            catch (Exception e)
            {

            }
        }
    }

}
