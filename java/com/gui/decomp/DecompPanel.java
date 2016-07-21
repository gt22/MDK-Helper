package com.gui.decomp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import com.creatix.autodecompiler.AutoDecompiler;

public class DecompPanel extends JPanel
{
	private GridBagConstraints gc;
	private JLabel file = new JLabel();
	private String[] versions = new String[] {/*"1.6.4", "1.7.2",*/ "1.7.10", "1.8", "1.8.9", "1.9", "1.9.4", "1.10", "1.10.2"};
	public DecompPanel(DecompFrame instance)
	{
		if(AutoDecompiler.forgeVersions == null)
		{
			AutoDecompiler.init();
		}
		Dimension size = getPreferredSize();
		size.width = 500;
		setPreferredSize(size);
		JLabel forgeversion = new JLabel("Forge version"), errors = new JLabel();
		JComboBox<String> version = new JComboBox<String>(versions);
		JButton chooseloc = new JButton("Choose location");
		chooseloc.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				instance.open();
			}
		});
		JCheckBox eclipse = new JCheckBox("Setup eclipse workspace"), idea = new JCheckBox("Setup IDEA workspace"); 
		JButton decompile = new JButton("Decompile");
		decompile.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				errors.setForeground(new Color(255, 0, 0));
				File installdir = instance.getFile();
				if(installdir == null)
				{
					errors.setText("Select directory");
				}
				if(installdir.listFiles() != null && installdir.listFiles().length > 0)
				{
					errors.setText("Directory must be empty");
				}
				installdir.mkdirs();
				AutoDecompiler.setForgeVersion((String) version.getSelectedItem());
				AutoDecompiler.setWorkDir(installdir.getAbsolutePath());
				AutoDecompiler.setEclipse(eclipse.isSelected());
				AutoDecompiler.setIdea(idea.isSelected());
				AutoDecompiler.decompile();
			}
		});
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx= 0.5;
		gc.weighty = 0.5;
		add(forgeversion, 0, 0);
		add(version, 1, 0);
		add(chooseloc, 0, 1);
		add(file, 1, 1);
		add(eclipse, 0, 2);
		add(idea, 0, 3);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		add(errors, 0, 5);
		add(decompile, 0, 5);
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
