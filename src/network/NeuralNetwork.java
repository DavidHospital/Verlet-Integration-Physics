package network;

import tools.Matrix;

public class NeuralNetwork {

	private Matrix w1;
	private Matrix w2;
	
	public int inputSize;
	public int hiddenSize;
	public int outputSize;
	
	public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
		this.inputSize = inputSize;
		this.hiddenSize = hiddenSize;
		this.outputSize = outputSize;
		
		this.w1 = Matrix.RandomMatrix(inputSize, hiddenSize);
		this.w2 = Matrix.RandomMatrix(hiddenSize, outputSize);
	}
	
	public void setWFromNodes (double[] values) {
		int k = 0;
		for (int i = 0; i < values.length; i ++) {
			if (i < inputSize * hiddenSize) {
				k ++;
				int r = i / inputSize;
				int c = i % hiddenSize;
				w1.setValue(r, c, values[i]);
			} else {
				int r = (i - k) / hiddenSize;
				int c = (i - k) % outputSize;
				w2.setValue(r, c, values[i]);
			}
		}
	}
	
	public Matrix forward(Matrix X) {
		return sigmoid(sigmoid(X.dot(w1)).dot(w2));
	}
	
	private Matrix sigmoid(Matrix m) {
		Matrix ret = Matrix.Zero(m.rows, m.columns);
		for (int r = 0; r < m.rows; r ++) {
			for (int c = 0; c < m.columns; c ++) {
				double a = 1.0 / (1.0 + Math.exp(-m.getValue(r, c)));
				ret.setValue(r, c, a);
			}
		}
		return ret;
	}
}
