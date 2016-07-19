package com.gt22.generator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.gt22.generator.core.Core;

public class GeneratorPanel extends JPanel
{
	JLabel file = new JLabel();
	public GeneratorPanel(MainFrame instance)
	{
		setBorder(BorderFactory.createTitledBorder("Gnerator"));
		Dimension size = getPreferredSize();
		size.width = 500;
		setPreferredSize(size);
		JLabel modid = new JLabel("Modid: "), name = new JLabel("Name: "), author = new JLabel("Author: ");
		JTextField modidtxt = new JTextField(10), nametxt = new JTextField(10), authortxt = new JTextField(10);
		JButton add = new JButton("Generate");
		JLabel errors = new JLabel();
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
					Core.generateMod(modidtxt.getText(), nametxt.getText(), authortxt.getText(), instance.getFile());
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
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		add(modid, gc);
		gc.gridy = 1;
		add(name, gc);
		gc.gridy = 2;
		add(author, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		add(modidtxt, gc);
		gc.gridy = 1;
		add(nametxt, gc);
		gc.gridy = 2;
		add(authortxt, gc);
		gc.gridy = 3;
		add(file, gc);
		gc.gridx = 0;
		add(chooseloc, gc);
		gc.weighty = 10;
		gc.gridy = 4;
		add(errors, gc);
		gc.gridy = 5;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		add(add, gc);
	}
	
	public void setFile(File file)
	{
		this.file.setText(file.getAbsolutePath());
	}
	
	private static boolean nullorempty(String s)
	{
		return s == null || s.equals("");
	}
}
