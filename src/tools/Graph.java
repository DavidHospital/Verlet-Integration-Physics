package tools;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	protected List<Integer> start;
	protected List<Integer> end;
	
	public Graph() {
		start = new ArrayList<>();
		end = new ArrayList<>();
	}
	
	public void addConnection(int n1, int n2) {
		start.add(n1);
		end.add(n2);
	}
	
	public void removeConnection(int n1, int n2) {
		for (int i = 0; i < start.size(); i++ ) {
			if (start.get(i) == n1 && end.get(i) == n2
					|| start.get(i) == n2 && end.get(i) == n1) {
				start.remove(i);
				end.remove(i);
			}
		}
	}
	
	public List<Integer> getAdj(int n) {
		List<Integer> adj = new ArrayList<Integer>();
		for (int i = 0; i < start.size(); i ++) {
			if (start.get(i) == n) {
				adj.add(end.get(i));
			} else if (end.get(i) == n) {
				adj.add(start.get(i));
			}
		}
		return adj;
	}
	
}
