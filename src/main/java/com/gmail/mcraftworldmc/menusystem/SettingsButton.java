package com.gmail.mcraftworldmc.menusystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.gmail.mcraftworldmc.SSMain;

public class SettingsButton extends JButton implements ActionListener {
	private JFrame frame;
	public SettingsButton(JFrame frame) {
		super("Saiyan");
		this.frame = frame;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		int opt = (Integer) JOptionPane.showInputDialog(frame,
				"Saiyan Offset", 
				"Choose level", 
				JOptionPane.INFORMATION_MESSAGE,
				null,
				new Object[] {0,1,2,3,4},
				SSMain.OFFSET);
		
		System.out.println(opt + " changed sen level");
		SSMain.changeTo(opt);
	}
}
