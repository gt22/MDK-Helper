package com.gt22.generator.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

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
		setResizable(false);
	}
	
	private void initComponents()
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Container c = getContentPane();
		c.add(panel, BorderLayout.WEST);
		
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
