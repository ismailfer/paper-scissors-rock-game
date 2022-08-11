package com.ismail.game;

/**
 * Email Player implementation
 * 
 * Sends an email to a player; with three links (one for each hand gesture); 
 * User to click on the link which will redirects him back to this application URL
 * using RestAPI Controller; and respond to this game
 * 
 * TODO sending of emails not implemented yet; as we need to do a RestController for this
 *      this class is just to show what we can do with this game
 * 
 * @author ismail
 */
public class EmailPlayer extends PlayerBase
{
    private String playerID;

    private String email;

    private ObjectResponse<PlayerHand> mUserInputResp = null;

    /**
     * 
     * @param playerID
     * @param sleepBetweenPlays sleep between plays (optional)
     */
    public EmailPlayer(String playerID, String email)
    {
        super(playerID);

        this.email = email;
    }

    @Override
    public void onGameStarted(String gameDesc)
    {
        // TODO send en email to the user indicating he has started playing this game

    }

    @Override
    public void onGameEnded()
    {

    }

    @Override
    public PlayerHand play(String playDesc)
    {
        // TODO send an email to the user with three links; one for each hand gesture

        mUserInputResp = new ObjectResponse<>();

        PlayerHand playerHand = null;

        try
        {
            playerHand = mUserInputResp.get();
        }
        catch (InterruptedException ie)
        {

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
            output = "You have lost this play against " + winnerID;
        }
        else
        {
            output = "You are even on this play";
        }

        // TODO send an email to the user showing the result of this play

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
        // TODO send an email to the user showing the result of this game

    }

}
