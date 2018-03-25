package com.gmail.mcraftworldmc.displaysystem;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.gmail.mcraftworldmc.SSMain;

public class ResizeableImagePane extends JPanel {

    private Image img;
    private boolean gif = false;
    private String name;
    
    public ResizeableImagePane() {
    }

    public void setImage(Image value, String name) {
    	gif = false;
    	this.name = name;
        if (img != value) {
            Image old = img;
            this.img = value;
            firePropertyChange("image", old, img);
            revalidate();
            repaint();
        }
        
        if(name.split("\\.")[1].equalsIgnoreCase("gif")) {
        	gif = true;
        }
    }
    
    //public void 

    public Image getImage() {
        return img;
    }

    @Override
    public Dimension getPreferredSize() {
        return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(this), img.getHeight(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            double scaleFactor = getScaleFactorToFit(new Dimension(img.getWidth(this), img.getHeight(this)), getSize());

            int x = (int)((width - (img.getWidth(this) * scaleFactor)) / 2);
            int y = (int)((height - (img.getHeight(this) * scaleFactor)) / 2);

            AffineTransform at = new AffineTransform();
            at.translate(x, y);
            at.scale(scaleFactor, scaleFactor);
            g2d.setTransform(at);
            if(gif) {
            	ImageIcon ii = new ImageIcon(SSMain.class.getResource("/images/"+name));
                ii.paintIcon(this, g2d, 0, 0);
            }else {
            	g2d.drawImage(img, 0, 0, this);
            }
            //g2d.drawImage(img, 0, 0, this);
            g2d.dispose();
        }
    }

    public double getScaleFactor(int iMasterSize, int iTargetSize) {

        return (double) iTargetSize / (double) iMasterSize;

    }

    public double getScaleFactorToFit(Dimension original, Dimension toFit) {

        double dScale = 1d;

        if (original != null && toFit != null) {

            double dScaleWidth = getScaleFactor(original.width, toFit.width);
            double dScaleHeight = getScaleFactor(original.height, toFit.height);

            dScale = Math.min(dScaleHeight, dScaleWidth);

        }

        return dScale;

    }

}
