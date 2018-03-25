package com.gmail.mcraftworldmc.menusystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.gmail.mcraftworldmc.displaysystem.DisplaySystem;

public class CalibrateButton extends JButton implements ActionListener {
	private JFrame frame;
	public CalibrateButton(JFrame frame) {
		super("Calibrate");
		this.frame = frame;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		DisplaySystem.calibrate();
	}
}
