package com.ismail.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Swing Player implementation
 * 
 * Launches a swing frame; asking the user to play a game
 * 
 * @author ismail
 */
@SuppressWarnings("serial")
public class SwingPlayer extends JFrame implements Player
{
    private String playerID;

    private JLabel playDescLabel = new JLabel("");

    private JLabel playOutputLabel = new JLabel("");

    private JButton paperButton = new JButton("Paper");

    private JButton scissorsButton = new JButton("Scissors");

    private JButton rockButton = new JButton("Rock");

    private Color winColor = new Color(0, 100, 0);

    private Color loseColor = new Color(100, 0, 0);

    private Color evenColor = new Color(0, 0, 100);

    private ObjectResponse<PlayerHand> userInputResponse = null;

    // positions to place the swing frame
    private int posX = 0;

    private int posY = 0;

    /**
     * 
     * @param playerID
     */
    public SwingPlayer(String playerID, int posX, int posY)
    {
        this.playerID = playerID;
        this.posX = posX;
        this.posY = posY;

        initUI();

    }

    @Override
    public String getPlayerID()
    {
        return playerID;
    }

    @Override
    public void onGameStarted(String gameDesc)
    {
        setTitle(gameDesc + " [" + getPlayerID() + "]");
    }

    @Override
    public void onGameEnded()
    {

    }

    @Override
    public PlayerHand play(String playDesc)
    {
        // Update the label on the swing form; and enable the buttons
        playDescLabel.setText(playDesc + ": Please click on one of the hands below:");
        //playOutputLabel.setText("");

        paperButton.setEnabled(true);
        rockButton.setEnabled(true);
        scissorsButton.setEnabled(true);

        // Wait for user input
        PlayerHand playerHand = null;

        userInputResponse = new ObjectResponse<>();

        try
        {
            playerHand = userInputResponse.get();
        }
        catch (InterruptedException ie)
        {

        }

        // disable the buttons after user input
        paperButton.setEnabled(false);
        rockButton.setEnabled(false);
        scissorsButton.setEnabled(false);

        return playerHand;

    }

    @Override
    public void notifyOfPlayScore(String playDesc, int score, String winnerID)
    {
        String output = playDesc;

        if (playerID.equals(winnerID))
        {
            output += ": You have won this play!";
        }
        else if (winnerID != null)
        {
            output += ": You have lost this play against " + winnerID;
        }
        else
        {
            output += ": You are even on this play";
        }

        playOutputLabel.setText(output);

        if (getPlayerID().equals(winnerID))
        {
            playOutputLabel.setForeground(winColor);
        }
        else if (getPlayerID() != null)
        {
            playOutputLabel.setForeground(loseColor);
        }
        else if (getPlayerID() != null)
        {
            playOutputLabel.setForeground(evenColor);
        }
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

        playOutputLabel.setText(output);

        if (getPlayerID().equals(winnerID))
        {
            playOutputLabel.setForeground(winColor);
        }
        else if (getPlayerID() != null)
        {
            playOutputLabel.setForeground(loseColor);
        }
        else if (getPlayerID() != null)
        {
            playOutputLabel.setForeground(evenColor);
        }

    }

    /**
     * Initialize the UI
     */
    private void initUI()
    {
        // Set event listeners on the buttons; 
        paperButton.addActionListener((event) -> userInputResponse.set(PlayerHand.Paper));
        scissorsButton.addActionListener((event) -> userInputResponse.set(PlayerHand.Scissors));
        rockButton.addActionListener((event) -> userInputResponse.set(PlayerHand.Rock));

        // disable the buttons by default
        paperButton.setEnabled(false);
        scissorsButton.setEnabled(false);
        rockButton.setEnabled(false);

        Container contentPane = getContentPane();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gridbag);

        c.fill = GridBagConstraints.HORIZONTAL;

        // Row 1: Play Label

        // Play Desc
        c.ipady = 20; //make this component tall
        //c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1.0;
        c.gridwidth = 3; // three column wide
        c.gridx = 0;
        c.gridy = 0;

        gridbag.setConstraints(playDescLabel, c);
        contentPane.add(playDescLabel);

        // Row 2: Player Hand
        // rock
        c.weightx = 0.33;
        c.gridwidth = 1;
        //c.weightx = 0.3;
        c.gridx = 0;
        c.gridy = 1;
        gridbag.setConstraints(paperButton, c);
        contentPane.add(paperButton);

        // scissors
        c.weightx = 0.33;
        c.gridx = 1;
        c.gridy = 1;
        gridbag.setConstraints(scissorsButton, c);
        contentPane.add(scissorsButton);

        // rock
        c.weightx = 0.33;
        c.gridx = 2;
        c.gridy = 1;
        gridbag.setConstraints(rockButton, c);
        contentPane.add(rockButton);

        // Row 3: Output messages

        c.ipady = 0; //reset to default
        c.weightx = 1.0;
        c.weighty = 1.0; //request any extra vertical space
        c.anchor = GridBagConstraints.SOUTH; //bottom of space
        c.insets = new Insets(10, 0, 0, 0); //top padding
        c.gridx = 1; //aligned with button 2
        c.gridwidth = 3; //2 columns wide
        c.gridy = 2; //third row
        gridbag.setConstraints(playOutputLabel, c);
        contentPane.add(playOutputLabel);

        setTitle("Player " + playerID);

        setSize(500, 300);

        //setLocationRelativeTo(null);

        setLocation(posX, posY);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        EventQueue.invokeLater(() -> {
            setVisible(true);
        });

    }

}
