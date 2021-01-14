package Login;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import game.MiniGame;
import game.MiniGameStack;

public class QueueandStack extends JFrame implements ActionListener
{
	JPanel p1;
	JButton btnQueue = new JButton("FIFO");
	JButton btnStack = new JButton("LIFO");
	private String user;
	public QueueandStack(String u)
	{
		super("QUEUE AND STACK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLocationRelativeTo(null);
		this.user=u;
		addToFrame();
		addActionListener();
		
		setVisible(true);
	}
	
	public void addToFrame()
	{
		p1 = new JPanel();
		
		p1.setLayout(new GridLayout(2,0));
		
		btnQueue.setFont(new Font("Serif", Font.BOLD, 25));
		btnQueue.setBackground(new Color(0, 255, 255));
		btnStack.setFont(new Font("Serif", Font.BOLD, 25));
		btnStack.setBackground(new Color(0, 255, 255));
		
		
		p1.add(btnQueue);
		p1.add(btnStack);
		
		add(p1);
	}
	
	public void addActionListener()
	{
		btnQueue.addActionListener(this);
		btnStack.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnQueue)
		{
			try
			{
				setVisible(false);
				MiniGame mn = new MiniGame(user);
			}
			catch(IOException e1) {}
		}
		
		if (e.getSource() == btnStack)
		{
			try
			{
				setVisible(false);
				MiniGameStack mns = new MiniGameStack(user);
			}
			catch(IOException e1) {}
		}
	}
}
