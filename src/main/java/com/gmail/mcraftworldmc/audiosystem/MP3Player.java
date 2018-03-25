package com.gmail.mcraftworldmc.audiosystem;

import java.io.BufferedInputStream;
import java.io.InputStream;

import com.gmail.mcraftworldmc.SSMain;

import javazoom.jl.player.Player;

public class MP3Player {
	private String filename;
    private Player player; 

    // constructor that takes the name of an MP3 file
    public MP3Player(String filename) {
        this.filename = filename;
    }

    public void close() { if (player != null) player.close(); }

    // play the MP3 file to the sound card
    public void play() {
        try {
            InputStream fis     = SSMain.class.getResourceAsStream("/music/"+filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();




    }
}
