package com.gmail.mcraftworldmc.menusystem;

import java.awt.Component;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class MenuSystem {
	
	//private static Set<Component> components = new HashSet<Component>();
	private JMenuBar menuBar = new JMenuBar();
	
	public MenuSystem(JFrame frame) {
		Component[] comp = {
			new SettingsButton(frame),
			new MusicButtone(frame),
			new StayHighButton(frame),
			new SensitivtyButton(frame),
			new CalibrateButton(frame)
		};
		
		Stream.of(comp)
		.forEach(c -> menuBar.add(c));
	}
	
	public JFrame apply(JFrame frame) {
		frame.setJMenuBar(menuBar);
		
		return frame;
	}
	
	
}
