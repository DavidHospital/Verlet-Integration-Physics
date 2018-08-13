package genetic;

public interface DNA {
	
	public double fitness();
	public DNA mutate(double mutationRate);
	public DNA child(DNA other);
	
}
