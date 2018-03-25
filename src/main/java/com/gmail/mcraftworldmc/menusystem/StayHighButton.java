package com.gmail.mcraftworldmc.menusystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.gmail.mcraftworldmc.SSMain;

public class StayHighButton extends JButton implements ActionListener {
	private JFrame frame;
	public StayHighButton(JFrame frame) {
		super("High for");
		this.frame = frame;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		int opt = (Integer) JOptionPane.showInputDialog(frame,
				"Stay High For", 
				"HOw much you stay at an elevated level", 
				JOptionPane.INFORMATION_MESSAGE,
				null,
				new Object[] {1,2,3,4,5},
				SSMain.STAY_HIGH_FOR);
		
		System.out.println(opt + " changed sen level");
		SSMain.high(opt);
	}
}
