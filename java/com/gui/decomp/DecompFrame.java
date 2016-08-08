package com.gui.decomp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import com.gt22.generator.Constants;
import com.gui.MainFrame;

public class DecompFrame extends JFrame
{
	private DecompPanel panel;
	private JFileChooser chooser;
	public DecompFrame(String title, MainFrame instance)
	{
		super(title);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				instance.setVisible(true);
				instance.setLocation(getX(), getY());
			}
		});
		panel = new DecompPanel(this);
		chooser = new JFileChooser(new File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.addActionListener((e) -> {
			if(e.getActionCommand().equals("ApproveSelection"))
			{
				panel.setFile(chooser.getSelectedFile());
			}
		});
		setSize(Constants.decompwidth, Constants.decompheight);
		setResizable(false);
		setLayout(new BorderLayout());
		initComponents();
		setVisible(true);
	}
	
	private void initComponents()
	{
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
