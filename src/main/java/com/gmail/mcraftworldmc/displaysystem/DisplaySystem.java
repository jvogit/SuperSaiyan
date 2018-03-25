package com.gmail.mcraftworldmc.displaysystem;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.gmail.mcraftworldmc.SSMain;
import com.gmail.mcraftworldmc.audiosystem.MP3Player;
import com.gmail.mcraftworldmc.audiosystem.SSAudioSystem;

public class DisplaySystem {
	private JFrame frame;
	private ResizeableImagePane panel;
	private int  powerLevel = 0;
	private int tries = 0;
	private int tryPrev = 0;
	private int offset = 0;
	private static boolean calibrate = false;
	private List<ImageBean> loaded = new ArrayList<>();
	private JLabel label = new JLabel();
	private MP3Player player;
	public DisplaySystem(JFrame frame) {
		this.frame = frame;
		loaded.add(new ImageBean("base.jpg"));
		loaded.add(new ImageBean("ss1.jpg"));
		loaded.add(new ImageBean("ss2.jpg"));
		loaded.add(new ImageBean("ss3.gif"));
		loaded.add(new ImageBean("ss4.gif"));
		loaded.add(new ImageBean("ss5.gif"));
		panel = new ResizeableImagePane();
		panel.setImage(loaded.get(0).IMAGE, loaded.get(0).NAME);
		frame.add(panel);
	}
	
	public void tick(SSAudioSystem as) throws InterruptedException {
		boolean initial = as.isStarting();
		int prev = powerLevel;
		if(!initial || calibrate) {
			as.start();
			offset = 0;
			as.tick();
			offset += as.getAudioLevel();
			as.tick();
			offset += as.getAudioLevel();
			as.tick();
			offset += as.getAudioLevel();
			offset /= 3;
			calibrate = false;
			return;
		}else {
			as.tick();
		}
		int level = as.getAudioLevel();
		powerLevel = this.audioLeveltoPowerLevel(level, offset);
		if(powerLevel < this.tryPrev) {
			System.out.println("hit1");
			if(tries++ < SSMain.STAY_HIGH_FOR) {
				System.out.println("Hit");
				Thread.sleep(1000);
				return;
			}else {
				tries = 0;
				this.tryPrev = prev;
				prev += 1;
			}
		}else {
			this.tryPrev = prev;
			tries = 0;
		}
		
		System.out.println(tryPrev + " " + tries + "  " + prev);
		if(prev != powerLevel){
			System.out.println(tryPrev);
			
			panel.setImage(loaded.get(powerLevel).IMAGE, loaded.get(powerLevel).NAME);
			if(powerLevel >= SSMain.MUSIC_START) {
				if(player == null) {
					player = new MP3Player("uitheme.mp3");
					player.play();
				}
			}else {
				if(player != null) {
					player.close();
					player = null;
				}
			}
		}
	}
	
	private int audioLeveltoPowerLevel(int audioLevel, int offset) {
		int diff = Math.abs(audioLevel - offset);
		int rounded = (diff/SSMain.SENSITIVTY)+SSMain.OFFSET;
		//System.out.println(audioLevel + " " + offset + " " + diff + " " + rounded);
		return rounded > 5 ? 5 : rounded <  0 ? 0 : rounded;
	}
	
	public static void calibrate() {
		calibrate = true;
	}
}

class ImageBean {
	Image IMAGE;
	String NAME;
	public ImageBean(String name) {
		this.NAME = name;
		try {
			this.IMAGE = ImageIO.read(SSMain.class.getResource("/images/"+name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
