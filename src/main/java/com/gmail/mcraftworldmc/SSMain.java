package com.gmail.mcraftworldmc;

import javax.swing.JFrame;

import com.gmail.mcraftworldmc.audiosystem.SSAudioSystem;
import com.gmail.mcraftworldmc.displaysystem.DisplaySystem;
import com.gmail.mcraftworldmc.menusystem.MenuSystem;

public class SSMain {
	public static int OFFSET = 0;
	public static int MUSIC_START = 3;
	public static int SENSITIVTY = 10;
	public static int STAY_HIGH_FOR = 3;
	
	public static int changeTo(int off) {
		OFFSET = off;
		return off;
	}
	
	public static int music(int i) {
		MUSIC_START = i;
		return i;
	}
	
	public static int sense(int i) {
		SENSITIVTY = i;
		return i;
	}
	
	public static int high(int i) {
		STAY_HIGH_FOR = i;
		return i;
	}
	public static void main(String[] args) throws InterruptedException {
		JFrame mainFrame = SSMain.getJFrame();
		SSAudioSystem system = new SSAudioSystem();
		MenuSystem mSystem = new MenuSystem(mainFrame);
		DisplaySystem dSystem = new DisplaySystem(mainFrame);
		mSystem.apply(mainFrame);
		mainFrame.setVisible(true);
		while(true) {
			dSystem.tick(system);
		}
		//system.start();
	}
	
	public static JFrame getJFrame() {
		JFrame frame = new JFrame("SUPER SAIYAN 1.0.0");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return frame;
	}
}
