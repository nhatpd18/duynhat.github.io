package game;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class AddNewQues extends JFrame implements ActionListener
{
	JPanel p1, p2;
	JTextArea newquestions = new JTextArea();
	JButton addQues = new JButton("Save");
	JButton cancelQues = new JButton("Cancel"); 
	JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	ArrayList<String> newQ = new ArrayList<String>();
	String newqs[];
	
	public AddNewQues()
	{
		super("New Questions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1,1));
		
		addToFram();
		addActionListener();
		
		setVisible(true);
	}
	
	public void addToFram()
	{
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		
		newquestions.setFont(new Font("Serif", Font.ITALIC, 18));
		newquestions.setLineWrap(true);
		newquestions.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(newquestions, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.add(addQues);
		p2.add(cancelQues);
		
		p1.add(scroll);
		p1.add(p2);
		
		add(p1);
	}
	
	public void addActionListener()
	{
		addQues.addActionListener(this);
		cancelQues.addActionListener(this);
	}
	
	public void addData() throws IOException
	{
		int r = j.showOpenDialog(null);
		String newques = newquestions.getText();
		newQ.add(newques);
		newqs = new String[newQ.size()];
		for (int i = 0; i < newQ.size(); i++)
			newqs[i] = newQ.get(i);
		if (r == JFileChooser.APPROVE_OPTION)
		{
			File outFile = new File(j.getSelectedFile().getAbsolutePath());
			FileOutputStream outFileStream = new FileOutputStream(outFile);
			PrintWriter outStream = new PrintWriter(outFileStream);
			for (int i = 0; i < newQ.size(); i++)
				outStream.println(newqs[i]);
			outStream.close();
		}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == addQues)
		{
			try 
			{
			addData();
			} catch(IOException e1) {}
			this.setVisible(false);
		}
		if (e.getSource() == cancelQues)
		{
			this.setVisible(false);
		}
	}
}