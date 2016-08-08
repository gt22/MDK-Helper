package com.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import com.gt22.generator.Constants;
import com.gui.decomp.DecompFrame;
import com.gui.gen.GenFrame;

public class MainFrame extends JFrame
{
	public MainFrame(String title)
	{
		super(title);
		setLayout(new BorderLayout());
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Constants.mainwidth, Constants.mainheight);
		setResizable(false);
		setVisible(true);
	}

	private void initComponents()
	{
		Container c = getContentPane();
		JButton gen = new JButton("Generate mod");
		gen.addActionListener((e) -> openGen());
		JButton decomp = new JButton("Setup MDK");
		decomp.addActionListener((e) -> openDecomp());
		c.add(gen, BorderLayout.NORTH);
		c.add(decomp, BorderLayout.SOUTH);
	}

	private void openGen()
	{
		new GenFrame("Mod generator", this).setLocation(getX(), getY());;
		setVisible(false);
	}
	
	private void openDecomp()
	{
		new DecompFrame("Setup MDK", this).setLocation(getX(), getY());;
		setVisible(false);
	}
}
