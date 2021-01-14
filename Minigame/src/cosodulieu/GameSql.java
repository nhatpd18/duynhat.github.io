package cosodulieu;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import Connectest.JDBCConnection;
import cosodulieu.GiaoDienGameSql;

public class GameSql extends JFrame implements ActionListener
{
	JButton btn;
	ArrayList<JButton> listbtns = new ArrayList<JButton>();
	ArrayList<Integer> listID = new ArrayList<Integer>();
	private String user;
	public GameSql(String u)
	{
		super("TOPICS GAMESQL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane();
		setLayout(new FlowLayout(FlowLayout.CENTER));
		this.user=u;
		selectCate();
		addActionListener();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void selectCate()
	{
		int i = 0;
		PreparedStatement pst = null;
		Connection conn = null;
		try
		{
			conn = JDBCConnection.getConnection();
			
			String sql = "select * from category";
			pst = conn.prepareStatement(sql);
        
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{
				listID.add(rs.getInt(1));
				btn = new JButton(rs.getString(2));
				btn.setPreferredSize(new Dimension(300, 100));
				btn.setBackground(new Color(152, 251, 152));
				btn.setFont(new Font("Serif", Font.BOLD, 25));
				listbtns.add(btn);
				add(btn);
			}
		}
		catch (Exception e1) {}
	}
	public void addActionListener()
	{
		for (JButton nut : listbtns)
		{
			nut.addActionListener(this);
		}

	}
	public void actionPerformed(ActionEvent e)
	{
		int i = 0;
		for (JButton nut : listbtns)
		{
			
			if (e.getSource() == nut)
			{
				setVisible(false);
				GiaoDienGameSql mn = new GiaoDienGameSql(user,listID.get(i));		
			}
			i++;
		}
		
	}
}
