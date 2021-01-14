package Login;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Connectest.JDBCConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import game.MiniGame;
import Login.CPlay;

public class LoginForm extends JFrame implements ActionListener
{
	JPanel p1, p2, p3, p4;
	JLabel headerLabel = new JLabel("LOGIN", JLabel.CENTER);
	JLabel userNameLabel = new JLabel(" USERNAME ");
	JLabel passWordLabel = new JLabel(" PASSWORD ");
	JTextField userNameTextField = new JTextField(15);
	JPasswordField passWordTextField = new JPasswordField(15);
	JButton loginButton = new JButton("Login");
	//background
	public static final String strImagePath="Images";
	ImageIcon background;
	JPanel jpanel;
	public static void main(String[] args) throws IOException 
	{
		LoginForm frame=new LoginForm();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void setBackGround(ImageIcon img)
	{
		this.background=img;	
	}
	public LoginForm() throws IOException
	{
		super("Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 250);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(2,1));
		addToFrame();
		addActionListener();
		setVisible(true);
	}
	public void addToFrame()
	{
		
		//iconuser
		JLabel lbliconuser = new JLabel();
		lbliconuser.setIcon(new ImageIcon(new ImageIcon("Images/user1.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		//iconpassword
		JLabel lbliconpassword = new JLabel();
		lbliconpassword.setIcon(new ImageIcon(new ImageIcon("Images/password.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(3,1));
		headerLabel.setBounds(50,50,50,50);
		headerLabel.setFont(new Font("Ariel Signature", Font.BOLD, 32));
		headerLabel.setSize(200,250);
		headerLabel.setBorder(new EmptyBorder(10,0,0,0));
		p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.add(lbliconuser);
		p2.add(userNameLabel);
		p2.add(userNameTextField);
		//icon
		p3 = new JPanel();
		p3.add(lbliconpassword);
		p3.add(passWordLabel);
		p3.add(passWordTextField);
		
		
		p4 = new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		loginButton.setIcon(new ImageIcon(new ImageIcon("Images/login.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		p4.add(loginButton);

		
		p1.add(headerLabel);
		p1.add(p2);
		p1.add(p3);
		add(p1);
		add(p4);
		p2.setBorder(new EmptyBorder(0,0,1,0));
		p3.setBorder(new EmptyBorder(0,10,10,10));
		
	}
	
	public void addActionListener()
	{
		loginButton.addActionListener(this);



	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()== loginButton)
		{
			if (userNameTextField.getText().equals("") || passWordTextField.getText().equals(""))
			{
				JOptionPane.showConfirmDialog(rootPane,"Some Fields Are is Empty","Error",JOptionPane.DEFAULT_OPTION );
			}
			else
			{	
				PreparedStatement pst = null;
				Connection conn = null;
				try
				{
					conn = JDBCConnection.getConnection();
					String sql = "select * from login where acc = ? and pass = ?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, userNameTextField.getText());
					pst.setString(2, passWordTextField.getText());
					ResultSet rs = pst.executeQuery();
					if(rs.next())
					{
						setVisible(false);
						CPlay cp = new CPlay(userNameTextField.getText());
					}
					else
					{
						JOptionPane.showConfirmDialog(rootPane,"Fail","Error",JOptionPane.DEFAULT_OPTION );
					}
			}catch(Exception e1) {};
		}
		
		
		
		
	}
	
	}
}

