package com.gui.gen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.gt22.generator.core.Writer;

public class GeneratorPanel extends JPanel
{
	GridBagConstraints gc;
	JLabel file = new JLabel();
	public GeneratorPanel(GenFrame instance)
	{
		setBorder(BorderFactory.createTitledBorder("Gnerator"));
		Dimension size = getPreferredSize();
		size.width = 500;
		setPreferredSize(size);
		JLabel modid = new JLabel("Modid: "), name = new JLabel("Name: "), author = new JLabel("Author: "), mcversion = new JLabel("MC version: ");
		JTextField modidtxt = new JTextField(10), nametxt = new JTextField(10), authortxt = new JTextField(10);
		JButton add = new JButton("Generate");
		JLabel errors = new JLabel();
		JComboBox<String> versions = new JComboBox<String>(new String[] {"1.7.10", "1.8", "1.8.9", "1.9", "1.9.4", "1.10", "1.10.2"});
		add.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				errors.setForeground(new Color(255, 0, 0));
				if(nullorempty(modidtxt.getText()))
				{
					errors.setText("Enter modid");
					return;
				}
				if(nullorempty(nametxt.getText()))
				{
					errors.setText("Enter name");
					return;
				}
				if(nullorempty(authortxt.getText()))
				{
					errors.setText("Enter author");
					return;
				}
				if(instance.getFile() == null)
				{
					errors.setText("Select directory");
					return;
				}
				try
				{
					errors.setForeground(new Color(0, 255, 0));
					errors.setText("Mod generated");
					Writer.generateMod(modidtxt.getText(), nametxt.getText(), authortxt.getText(), (String) versions.getSelectedItem(), instance.getFile());
				}
				catch (IOException e1)
				{
					errors.setText("Something went really wrong");
					e1.printStackTrace();
				}
			}
		});
		JButton chooseloc = new JButton("Choose location");
		chooseloc.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				instance.open();
			}
		});
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		add(modid, 0, 0);
		add(name, 0, 1);
		add(author, 0, 2);
		add(modidtxt, 1, 0);
		add(nametxt, 1, 1);
		add(authortxt, 1, 2);
		add(mcversion, 0, 3);
		add(versions, 1, 3);
		add(file, 1, 4);
		add(chooseloc, 0, 4);
		gc.weighty = 10;
		add(errors, 0, 10);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		add(add, 0, 11);
	}
	
	public void setFile(File file)
	{
		this.file.setText(file.getAbsolutePath());
	}
	
	private static boolean nullorempty(String s)
	{
		return s == null || s.equals("");
	}
	
	private void add(Component c, int x, int y)
	{
		gc.gridx = x;
		gc.gridy = y;
		add(c, gc);
	}
}
