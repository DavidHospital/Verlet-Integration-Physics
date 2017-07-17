package network.dynamic;

public class InputNode extends NetworkNode {
	
	public InputNode (int x, int y) {
		super(null, x, y);
	}
	
	@Override
	public double output() {
		return value;
	}
	
	public void inputValue(double input) {
		this.value = input;
	}
	
	// Do nothing for inputSticks, as there are no sticks to update
	@Override
	void updateSticks(double[][][] values, int layer, int index) {	
	}
	
}
