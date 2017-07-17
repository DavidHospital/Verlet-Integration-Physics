package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	private boolean isRunning = true;
	private int fps = 30;
	private int windowWidth = 500;
	private int windowHeight = 500;
	
	private BufferedImage backBuffer;
	private Insets insets;
	
	private void run() {
		initialize();
		
		while(isRunning) 
        { 
            long time = System.currentTimeMillis(); 

            update(); 
            render(); 

            time = (1000 / fps) - (System.currentTimeMillis() - time); 

            if (time > 0) 
            { 
                try 
                { 
                	Thread.sleep(time); 
                } 
                catch(Exception e){} 
            } 
        } 

        setVisible(false); 
	}
	
	private void initialize() {
		setTitle("Evolution!");
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right,
				insets.top + windowHeight + insets.bottom);
		
		backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	private void update() {
		
	}
	
	private void render() {
		Graphics g = getGraphics();
		Graphics bgg = backBuffer.getGraphics();
		
		bgg.setColor(Color.WHITE);
		bgg.fillRect(0, 0, windowWidth, windowHeight);
		
		bgg.setColor(Color.DARK_GRAY);
		bgg.drawOval(10, 10, 20, 20);
		
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
	
	public static void main (String[] args) {
		// Initialize Window
		
		Game game = new Game();
		game.run();
		System.exit(0);
	}
}
