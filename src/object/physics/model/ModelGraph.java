package object.physics.model;

import java.util.ArrayList;
import java.util.List;

import object.physics.Muscle;
import object.physics.Node;
import object.scene.Scene;
import tools.Graph;

public class ModelGraph extends Graph {

	private List<Node> nodes;
	private List<Double> thickness;
	private List<Double> contractScale;

	public ModelGraph() {
		super();
		nodes 			= new ArrayList<Node>();
		thickness 		= new ArrayList<Double>();
		contractScale 	= new ArrayList<Double>();
	}

	private void AddNode(Node n) {
		nodes.add(n);
	}
	
	private void addMuscle(int n1, int n2, double thickness, double contractScale) {
		if (n1 < nodes.size() && n2 < nodes.size()) {
			addConnection(n1, n2);
			this.thickness.add(thickness);
			this.contractScale.add(contractScale);
		}
	}
	
	public void update(Scene scene) {
		for (int i = 0; i < nodes.size(); i ++) {
			nodes.get(i).update(scene);
		}
		for (int i = 0; i < start.size(); i ++) {
			
		}
	}
	
	@Override
	public ModelGraph clone() {
		ModelGraph ret = new ModelGraph();
		for (int i = 0; i < nodes.size(); i++) {
			ret.AddNode(nodes.get(i).clone());
		}
		
		for (int i = 0; i < start.size(); i ++) {
			ret.addMuscle(start.get(i), end.get(i), thickness.get(i), contractScale.get(i));
		}
		
		return ret;
	}
	
}
