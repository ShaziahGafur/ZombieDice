//name: Shaziah Gafur
//date: April 24th, 2016
//purpose: Zombie Dice
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.Applet;
public class Zombie extends Applet implements ActionListener
{
    Panel panel;
    Panel cardA, card1, card2;
    CardLayout cdLayout = new CardLayout ();
    int brains = 0;
    int shots = 0;
    int foot = 0;
    int turn = 1;
    int score1=0;
    int score2=0;
    JLabel pic1;
    JLabel pic2;
    JLabel pic3;
    JButton picA;
    JButton picB;
    JButton picC;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label1;

    public void init ()
    {
	panel = new Panel ();
	panel.setLayout (cdLayout);
	screenA ();
	screen1 ();
	setLayout (new BorderLayout ());
	add ("Center", panel);
    }


    public void screenA ()
    {
	cardA = new Panel ();
	cardA.setBackground (Color.red);
	JLabel picture = new JLabel (createImageIcon ("logo.jpg"));
	JButton play = new JButton (createImageIcon ("play.jpg"));
	play.addActionListener (this);
	play.setActionCommand ("play");
	JButton quit = new JButton (createImageIcon ("quit.jpg"));
	cardA.add (picture);
	cardA.add (play);
	cardA.add (quit);
	panel.add ("1", cardA);
    }


    public void screen1 ()
    {
	card1 = new Panel ();
	card1.setBackground (Color.red);
	label1 = new JLabel ("Player " + turn+"     ");
	label1.setFont (new Font("Ravie", Font.PLAIN, 30));
	label2 = new JLabel ("Shotguns: " + shots+"     ");
	label2.setFont (new Font ("Chiller", Font.BOLD, 25));
	label3 = new JLabel ("Braaainss: " + brains+"     ");
	label3.setFont (new Font ("Chiller", Font.BOLD, 25));
	label4 = new JLabel ("Player 1 Ate: " + score1 + " Braaiinnss     ");
	label4.setFont (new Font ("Chiller", Font.BOLD, 25));
	label5 = new JLabel ("Player 2 Ate: " + score2 + " Braaiinnss     ");
	label5.setFont (new Font ("Chiller", Font.BOLD, 25));
	Panel row1 = new Panel (new GridLayout (1, 3));
	pic1 = new JLabel (createImageIcon ("zombie.jpg"));
	pic2 = new JLabel(createImageIcon ("zombie.jpg"));
	pic3 = new JLabel(createImageIcon ("zombie.jpg"));
	row1.add (pic1);
	row1.add (pic2);
	row1.add (pic3);
	Panel row2 = new Panel (new GridLayout (1, 3));
	picA = new JButton (createImageIcon ("keepGoing.jpg"));
	picB = new JButton (createImageIcon ("stopAndScore.jpg"));
	picC = new JButton (createImageIcon ("nextPlayer.jpg"));
	picA.setActionCommand ("going");
	picA.addActionListener (this);
	picB.setActionCommand ("stop");
	picB.addActionListener (this);
	picC.setActionCommand ("next");
	picC.addActionListener (this);
	picB.setEnabled (false);
	picC.setEnabled (false);
	row2.add (picA);
	row2.add (picB);
	row2.add (picC);
	card1.add (label1);
	card1.add (label2);
	card1.add (label3);
	card1.add (label4);
	card1.add (label5);
	card1.add (row1);
	card1.add (row2);
	panel.add ("2", card1);

    }


    public void actionPerformed (ActionEvent e)
    {

	String roll;
	if (e.getActionCommand ().equals ("play"))
	    cdLayout.show (panel, "2");
	else if (e.getActionCommand ().equals ("going"))
	{
	    foot = 0;
	    roll = diceRoll ();
	    pic1.setIcon (createImageIcon (roll + ".jpg"));
	    brains += Brains (roll);
	    shots += Shots (roll);
	    roll = diceRoll ();
	    pic2.setIcon (createImageIcon (roll + ".jpg"));
	    brains += Brains (roll);
	    shots += Shots (roll);
	    roll = diceRoll ();
	    pic3.setIcon (createImageIcon (roll + ".jpg"));
	    brains += Brains (roll);
	    shots += Shots (roll);
	    if (shots < 3)
	    {
		picA.setEnabled (true);
		picB.setEnabled (true);
		
	    }
	    else
	    {
		picA.setEnabled (false);
		picB.setEnabled (false);
		picC.setEnabled (true);
		pic1.setIcon (createImageIcon ("shotgunned.jpg"));
		pic2.setIcon (createImageIcon ("shotgunned.jpg"));
		pic3.setIcon (createImageIcon ("shotgunned.jpg"));
		brains = 0;
	    }
	    label2.setText ("Shotguns: " + shots+"     ");
	    label3.setText("Braaainss: " + brains+"     ");
	    cdLayout.show (panel, "2");
	    
	}
	
	else if (e.getActionCommand ().equals ("stop"))
	{
	    if (turn == 1)
	    {
		score1 += brains;
		label4.setText("Player 1 Ate: " + score1 + " Braaiinnss");
	    }

	    else
	    {
		score2 += brains;
		label5.setText("Player 2 Ate: " + score2 + " Braaiinnss");
	    }
	    label4.setText("Player 1 Ate: " + score1 + " Braaiinnss     ");
	    label5.setText("Player 2 Ate: " + score2 + " Braaiinnss     ");
		
	    picA.setEnabled (false);
	    picB.setEnabled (false);
	    picC.setEnabled (true);
	    cdLayout.show (panel, "2");
	}
	else if (e.getActionCommand ().equals ("next"))
	{
		if (turn == 1)
		    turn = 2;
		else
		    turn = 1;
	      
		label1.setText("Player " + turn);
		picA.setEnabled (true);
		picB.setEnabled (false);
		picC.setEnabled (false);
		pic1.setIcon (createImageIcon ("zombie.jpg"));
		pic2.setIcon (createImageIcon ("zombie.jpg"));
		pic3.setIcon (createImageIcon ("zombie.jpg"));
		brains=0;
		shots=0;
		label2.setText("Shotguns: " + shots+"     ");
		label3.setText("Braaainss: " + brains+"     ");
		cdLayout.show (panel, "2");
	}
	}


	public int Brains (String roll)
	{
	    int num = 0;
	    if (roll.equals ("greenBrain") || roll.equals ("yellowBrain") || roll.equals ("redBrain"))
		num++;
	    return num;
	}


	public int Shots (String roll)
	{
	    int num = 0;
	    if (roll.equals ("greenShotGun") || roll.equals ("yellowShotGun") || roll.equals ("redShotGun"))
		num++;
	    return num;
	}


	public String diceRoll ()
	{
	    String roll;
	    int num = (int)(Math.random () * 78 + 1);
	    if (num < 19)
		roll = "greenBrain";
	    else if (num < 31)
		roll = "greenFootPrints";
	    else if (num < 37)
		roll = "greenShotGun";
	    else if (num < 45)
		roll = "yellowBrain";
	    else if (num < 53)
		roll = "yellowFootPrints";
	    else if (num < 61)
		roll = "yellowShotGun";
	    else if (num < 64)
		roll = "redBrain";
	    else if (num < 70)
		roll = "redFootPrints";
	    else
		roll = "redShotGun";
	    return roll;
	}


	protected static ImageIcon createImageIcon (String path)
	{
	    java.net.URL imgURL = Zombie.class.getResource (path);
	    if (imgURL != null)
	    {
		return new ImageIcon (imgURL);
	    }


	    else
	    {
		System.err.println ("Couldn't find file: " + path);
		return null;
	    }
	}
}

