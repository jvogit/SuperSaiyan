package com.gmail.mcraftworldmc.menusystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.gmail.mcraftworldmc.SSMain;

public class MusicButtone extends JButton implements ActionListener {
	private JFrame frame;
	public MusicButtone(JFrame frame) {
		super("Music Level");
		this.frame = frame;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		int opt = (Integer) JOptionPane.showInputDialog(frame,
				"Music Level", 
				"Choose when music starts", 
				JOptionPane.INFORMATION_MESSAGE,
				null,
				new Object[] {1,2,3,4, 5},
				SSMain.MUSIC_START);
		
		System.out.println(opt + " changed sen level");
		SSMain.music(opt);
	}
}
