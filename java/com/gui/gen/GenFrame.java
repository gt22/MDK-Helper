package com.gui.gen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import com.gt22.generator.Constants;
import com.gui.MainFrame;
import com.gui.decomp.DecompPanel;

public class GenFrame extends JFrame
{
	private GeneratorPanel genpanel;
	private JFileChooser chooser;
	public GenFrame(String title, MainFrame instance)
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
		genpanel = new GeneratorPanel(this);
		chooser  = new JFileChooser(new File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.addActionListener((e) -> {
			if(e.getActionCommand().equals("ApproveSelection"))
			{
				genpanel.setFile(chooser.getSelectedFile());
			}
		});;
		setLayout(new BorderLayout());
		initComponents();
		setVisible(true);
		setSize(Constants.genwidth, Constants.genheigth);
		setResizable(false);
	}
	
	private void initComponents()
	{
		Container c = getContentPane();
		genpanel.setVisible(true);
		c.add(genpanel, BorderLayout.WEST);
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
