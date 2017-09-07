package main;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame implements KeyListener {
	
	private boolean isRunning = true;
	private int fps = 60;
	private int windowWidth = 800;
	private int windowHeight = 600;
	
	private BufferedImage backBuffer;
	private Insets insets;
	
	private GameManager gm;
	
	private Game() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	}
	
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
		
		gm = new GameManager(windowWidth, windowHeight);
	}
	
	private void update() {
		gm.update();
	}
	
	private void render() {		
		Graphics g = getGraphics();
		Graphics bgg = backBuffer.getGraphics();
		
		gm.render(bgg);
		
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
	
	public static void main (String[] args) {
		// Initialize Window
		
		Game game = new Game();
		game.run();
		System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gm.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gm.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		gm.keyTyped(e);
	}
}
