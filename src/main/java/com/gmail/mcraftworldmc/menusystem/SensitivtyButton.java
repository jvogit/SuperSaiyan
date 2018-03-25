package com.gmail.mcraftworldmc.menusystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.gmail.mcraftworldmc.SSMain;

public class SensitivtyButton extends JButton implements ActionListener {
	private JFrame frame;
	public SensitivtyButton(JFrame frame) {
		super("Sensitivity");
		this.frame = frame;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		int opt = (Integer) JOptionPane.showInputDialog(frame,
				"Sensitivity", 
				"Chooose sensitivty", 
				JOptionPane.INFORMATION_MESSAGE,
				null,
				new Object[] {5,7,10,15,20},
				SSMain.SENSITIVTY);
		
		System.out.println(opt + " changed sen level");
		SSMain.sense(opt);
	}
}
