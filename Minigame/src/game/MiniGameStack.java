package game;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import Login.CPlay;
import Login.QueueandStack;
import masksql.diemsql;

public class MiniGameStack extends JFrame implements ActionListener
{
	JPanel p1, p2, p3, p4, p5;
	JLabel headerLabel = new JLabel("ANSWER QUESTIONS", JLabel.CENTER);
	JTextArea questionsDisplay = new JTextArea();
	JLabel yourAnsLabel = new JLabel("Your Answer: "); 
	JTextField yourAnsField = new JTextField(20);
	JLabel yourMarkLabel = new JLabel("Your Mark: ");
	JLabel yourMark = new JLabel("0");
	JButton btnOK = new JButton("OK");
	JButton btnReset = new JButton("Reset");
	JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	JMenuBar mb = new JMenuBar();
	JMenu questionDB = new JMenu("Questions DB");
	JMenu accManage = new JMenu("Account Management");
	JMenuItem newques = new JMenuItem("New");
	JMenuItem openques = new JMenuItem("Open");
	JMenuItem controlStopMusic = new JMenuItem("Stop Music");
	JMenuItem controlStartMusic = new JMenuItem("Start Music");
	JMenuItem openplay = new JMenuItem("Open CPlay");
	MusicNen ms = new MusicNen();
	diemsql um = new diemsql();
	private String user;
	private String correctAns;
	private int i = 0;
	Stack<String> QA = new Stack<String>(); 
	String QAArr[];
	Stack<File> files = new Stack<File>();
	
	
	public MiniGameStack(String u) throws IOException
	{
		super("Mini Game S");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setLayout(new GridLayout(2,1));
		this.user =u;
		yourMark.setText(String.valueOf(um.getmark(user)));
		addToFrame();
		addActionListener();
		getQAData();
		for (int i = QA.size()-1; i >= 0; i--)
		{
			QAArr[QA.size() - 1 - i] = QA.get(i);
		}

		questionsDisplay.append(QAArr[i].split(": ")[0]);
		try 
		{
			ms.playMusic();
		} 
		catch (UnsupportedAudioFileException e) {} 
		catch (IOException e) {} 
		catch (LineUnavailableException e) {}
		setLocationRelativeTo(null);  
		setVisible(true);
	}
	
	public void addToFrame()
	{
		mb.add(questionDB);
		mb.add(accManage);
		
		questionDB.add(newques);
		questionDB.add(openques);
		questionDB.add(openplay);
		questionDB.add(controlStopMusic);
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		
		headerLabel.setFont(new Font("Serif", Font.BOLD,36));
		
		questionsDisplay.setFont(new Font("Serif", Font.ITALIC, 26));
		questionsDisplay.setLineWrap(true);
		questionsDisplay.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(questionsDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		questionsDisplay.setEditable(false);
		
		p1.add(headerLabel);
		p1.add(scroll);
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(3,1));
		
		p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		yourMarkLabel.setFont(new Font("Serif", Font.BOLD, 30));
		yourMarkLabel.setForeground(Color.RED);
		yourMark.setFont(new Font("Serif", Font.BOLD, 30));
		yourMark.setForeground(Color.RED);
		p3.add(yourMarkLabel);
		p3.add(yourMark);
		
		p4 = new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		yourAnsLabel.setFont(new Font("Serif", Font.BOLD, 20));
		yourAnsField.setFont(new Font("Serif", Font.PLAIN, 18));
		p4.add(yourAnsLabel);
		p4.add(yourAnsField);
		
		p5 = new JPanel();
		p5.setLayout(new FlowLayout(FlowLayout.CENTER));
		p5.add(btnOK);
		p5.add(btnReset);
		
		p2.add(p3);
		p2.add(p4);
		p2.add(p5);

		add(mb);
		setJMenuBar(mb);
		add(p1);
		add(p2);
	}
	
	public void addActionListener()
	{
		btnOK.addActionListener(this);
		btnReset.addActionListener(this);
		newques.addActionListener(this);
		openques.addActionListener(this);
		controlStopMusic.addActionListener(this);
		controlStartMusic.addActionListener(this);
		openplay.addActionListener(this);
	}

	public void getQAData() throws IOException
	{
		
		j.setMultiSelectionEnabled(true);
		int r = j.showOpenDialog(null);
		if (r == JFileChooser.APPROVE_OPTION)
		{
			File[] inFiles = j.getSelectedFiles();
			for (File inFile:inFiles)
			{
				files.add(inFile);
				Play(inFile);
			}
		}
	}
	
	public void Play(File f) throws IOException
	{
		FileReader fileReader = new FileReader(f);
		BufferedReader bufReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufReader.readLine()) != null)
		{
			QA.push(line);
		}
		QAArr = new String[QA.size()];
		
		bufReader.close();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btnOK)
		{
			correctAns = QAArr[i].split(": ")[1];
			showResult();
			if (yourAnsField.getText().equals(correctAns))
			{
				i++;
				questionsDisplay.append("\n" + QAArr[i].split(":")[0]);
				yourAnsField.setText(null);
			}
		}
		
		if (e.getSource() == btnReset)
		{
			yourAnsField.setText(null);
		}
		
		if (e.getSource() == newques)
		{
			AddNewQues anq = new AddNewQues();
		}
		
		if (e.getSource() == openques)
		{
			ms.stopMusic();
			setVisible(false);
			QueueandStack qs = new QueueandStack(user);
			
		} 
		
		if (e.getSource() == controlStopMusic)
		{
			questionDB.remove(controlStopMusic);
			ms.stopMusic();
			questionDB.add(controlStartMusic);	
		}
		
		if (e.getSource() == controlStartMusic)
		{
			questionDB.remove(controlStartMusic);
			try 
			{
				ms.playMusic();
			} 
			catch (UnsupportedAudioFileException e1) {} 
			catch (IOException e1) {} 
			catch (LineUnavailableException e1) {}
			questionDB.add(controlStopMusic);
		}
		
		if (e.getSource() == openplay)
		{
			ms.stopMusic();
			setVisible(false);
			CPlay cp = new CPlay(user);
		}
	}
	
	public void showResult()
	{
		String ans = yourAnsField.getText();
		
		if (yourAnsField.getText().equals(correctAns))
		{
			MusicDung mra = new MusicDung();
			mra.start();
			if (JOptionPane.showConfirmDialog(null, ans + " is right.","",JOptionPane.DEFAULT_OPTION) == JOptionPane.OK_OPTION)
			{
				mra.stopMusic();
			}
			yourMark.setText(String.valueOf(Integer.parseInt(yourMark.getText())+1));
			um.savemark(user, Integer.parseInt(yourMark.getText()));
			questionsDisplay.append("\n=> Answer: " + ans);
		}
		else
		{
			MusicSai mwa = new MusicSai();
			mwa.start();
			if (JOptionPane.showConfirmDialog(null, ans + " is wrong.\nTry again!","",JOptionPane.DEFAULT_OPTION) == JOptionPane.OK_OPTION)
			{
				mwa.stopMusic();
			}
			yourAnsField.setText(null);
		}
	}
}
