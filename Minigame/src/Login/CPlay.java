package Login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import cosodulieu.GameSql;

public class CPlay extends JFrame implements ActionListener
{
	JButton btnFile = new JButton("FILE");
	JButton btnSql = new JButton("SQL");
	private String user;
	public CPlay(String u)
	{
		super("TOPICS PLAY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane();
		setLayout(new FlowLayout(FlowLayout.CENTER));
		this.user = u;
		addToFrame();
		addActionListener();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void addToFrame()
	{
		btnFile.setPreferredSize(new Dimension(400, 100));
		btnFile.setFont(new Font("Serif", Font.BOLD, 30));
		btnFile.setBackground(new Color(30, 144, 255));
		btnSql.setPreferredSize(new Dimension(400, 100));
		btnSql.setFont(new Font("Serif", Font.BOLD, 30));
		btnSql.setBackground(new Color(102, 205, 170));
		
		add(btnFile);
		add(btnSql);
	}
	
	public void addActionListener()
	{
		btnFile.addActionListener(this);
		btnSql.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnFile)
		{
			setVisible(false);
			QueueandStack qs = new QueueandStack(user);
		}
		
		if (e.getSource() == btnSql)
		{
			setVisible(false);
			GameSql ch = new GameSql(user);
		}
	}
}
