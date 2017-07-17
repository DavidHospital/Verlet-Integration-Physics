package network.dynamic;

import java.awt.Color;
import java.awt.Graphics;

import object.physicsnew.Node;

public class NetworkStick {

	NetworkNode input;
	double weight;

	private boolean hasDrawn;
	
	private static double width = 0.1;
	
	public NetworkStick (NetworkNode input, double weight) {
		this.input = input;
		this.weight = weight;
	}
	
	public double output() {
		double ret = input.output();
		
			//System.out.println(weight + "\t" + this);
			//System.out.println(ret);

		return weight * ret;
	}
	
	void update() {
		hasDrawn = false;		
		input.update();
	}
	
	void render(Graphics g, NetworkNode output) {
		if (!hasDrawn) {
			hasDrawn = true;
			
			// Draw line here
			double a = Math.atan2(output.y - input.y, output.x - input.x);
			int[] xPoints = new int[4];
			int[] yPoints = new int[4];
			
			xPoints[0] = (int)(input.x + Math.sin(a) * Node.RADIUS * width);
			xPoints[1] = (int)(input.x - Math.sin(a) * Node.RADIUS * width);
			xPoints[2] = (int)(output.x - Math.sin(a) * Node.RADIUS * width);
			xPoints[3] = (int)(output.x + Math.sin(a) * Node.RADIUS * width);
			
			yPoints[0] = (int)(input.y - Math.cos(a) * Node.RADIUS * width);
			yPoints[1] = (int)(input.y + Math.cos(a) * Node.RADIUS * width);
			yPoints[2] = (int)(output.y + Math.cos(a) * Node.RADIUS * width);
			yPoints[3] = (int)(output.y - Math.cos(a) * Node.RADIUS * width);
			
			g.setColor(Color.getHSBColor(0.0f, 0.0f, (float)(input.value + output.value) / 2.0f));
			g.fillPolygon(xPoints, yPoints, 4);
			
			input.render(g);
		}
	}
}
