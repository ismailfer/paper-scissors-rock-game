package com.ismail.game;

/**
 * Abstract class to implement base features of the Player interface
 * 
 * @author ismail
 */
public abstract class PlayerBase implements Player
{
    public String playerID;
    
    public PlayerBase(String playerID)
    {
        this.playerID = playerID;
    }
        
    public final String getPlayerID()
    {
        return playerID;
    }
    
    
    @Override
    public void onGameStarted(String gameDesc)
    {
        
    }
    
    @Override
    public void onGameEnded()
    {
        
    }
    
    @Override
    public String toString()
    {
        return playerID;
    }
}
