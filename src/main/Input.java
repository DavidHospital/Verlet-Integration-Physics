package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Input {

	private static ArrayList<Integer> keys = new ArrayList<>();
	
	protected static void KeyPressed(KeyEvent e) {
		if (!keys.contains(e.getKeyCode())) {
			keys.add(e.getKeyCode());
		}
	}
	
	protected static void KeyReleased(KeyEvent e) {
		keys.remove((Integer) e.getKeyCode());
	}
	
	public static boolean KeyDown(int key) {
		return keys.contains(key);
	}
}
