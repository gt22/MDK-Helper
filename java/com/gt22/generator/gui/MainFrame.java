package com.gt22.generator.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import com.gt22.generator.Constants;

public class MainFrame extends JFrame
{
	private GeneratorPanel panel;
	private JFileChooser chooser;
	public MainFrame(String title)
	{
		super(title);
		panel = new GeneratorPanel(this);
		chooser  = new JFileChooser(new File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand().equals("ApproveSelection"))
				{
					panel.setFile(chooser.getSelectedFile());
				}
			}
		});
		setLayout(new BorderLayout());
		initComponents();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Constants.width, Constants.heigth);
	}
	
	private void initComponents()
	{
		JTextArea text = new JTextArea();
		Container c = getContentPane();
		c.add(panel, BorderLayout.WEST);
		c.add(text, BorderLayout.NORTH);
		
	}
	
	public void open()
	{
		chooser.showOpenDialog(getContentPane());
	}
	
	public File getFile()
	{
		return chooser.getSelectedFile();
	}
}
