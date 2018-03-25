package com.gmail.mcraftworldmc.audiosystem;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class SSAudioSystem {
	private TargetDataLine line;
	private AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
	private boolean started = false;
	private int level = 0;
	DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
		    format);
	public SSAudioSystem() {
		AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
		    format); // format is an AudioFormat object
		if (!AudioSystem.isLineSupported(info)) {
		    // Handle the error ... 
			System.out.println("error establishing audio");
			return;
		}
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick() {
		if(started) {
			if (!AudioSystem.isLineSupported(info)) {
			    // Handle the error ... 
				System.out.println("error establishing audio");
				return;
			}
			try {
				line.open(format);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    line.start();
		    byte tempBuffer[] = new byte[line.getBufferSize()/5];
		    if (line.read(tempBuffer, 0, tempBuffer.length) > 0) {
                level = calculateRMSLevel(tempBuffer);
                //System.out.println(level);
            }
		}
	}
	
	public void start() {
		started = true;
	}
	
	public int getAudioLevel() {
		return level;
	}
	
	public void stop() {
		line.close();
		started = false;
	}
	
	public boolean isStarting() {
		return started;
	}
	
	/*public void start() {
		// Obtain and open the line.
		try {
		    int level = 0;
		    byte tempBuffer[] = new byte[line.getBufferSize()/5];
		    if (line.read(tempBuffer, 0, tempBuffer.length) > 0) {
                level = calculateRMSLevel(tempBuffer);
            }
            System.out.println(level);
	        line.close();
		} catch (LineUnavailableException ex) {
		    // Handle the error ... 
		}
	}*/
	
	public int calculateRMSLevel(byte[] audioData)
	{ 
	    long lSum = 0;
	    for(int i=0; i < audioData.length; i++)
	        lSum = lSum + audioData[i];

	    double dAvg = lSum / audioData.length;
	    double sumMeanSquare = 0d;

	    for(int j=0; j < audioData.length; j++)
	        sumMeanSquare += Math.pow(audioData[j] - dAvg, 2d);

	    double averageMeanSquare = sumMeanSquare / audioData.length;

	    return (int)(Math.pow(averageMeanSquare,0.5d) + 0.5);
	}
	
}
