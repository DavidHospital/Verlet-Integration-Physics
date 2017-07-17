package tools;

import java.util.Random;

public class Matrix {

	private double[][] values;
	public final int rows;
	public final int columns;
	
	public Matrix(int rows, int columns, double[] values) {
		this.rows = rows;
		this.columns = columns;
		this.values = new double[rows][columns];
		if (rows * columns != values.length) {
			try {
				throw new MatrixWrongSizeException();
			} catch (MatrixWrongSizeException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		for (int x = 0; x < rows; x ++) {
			for (int y = 0; y < columns; y ++) {
				this.values[x][y] = values[x * columns + y];
			}
		}
	}
	
	public static Matrix RandomMatrix(int rows, int columns) {
		double[] values = new double[rows * columns];
		Random r = new Random();
		for (int i = 0; i < values.length; i ++) {
			values[i] = r.nextGaussian() * 10;
		}
		return new Matrix(rows, columns, values);
	}
	
	public static Matrix Zero(int rows, int columns) {
		double[] values = new double[rows * columns];
		return new Matrix(rows, columns, values);
	}
	
	public Matrix add(Matrix other) {
		if (this.rows != other.rows || this.columns != other.columns) {
			try {
				throw new MatrixWrongSizeException();
			} catch (MatrixWrongSizeException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		Matrix ret = Matrix.Zero(rows, columns);
		for (int x = 0; x < rows; x ++) {
			for (int y = 0; y < columns; y ++) {
				ret.values[x][y] = this.values[x][y] + other.values[x][y];
			}
		}
		return ret;
	}
	
	public Matrix mult(Matrix other) {
		if (this.rows != other.rows || this.columns != other.columns) {
			try {
				throw new MatrixWrongSizeException();
			} catch (MatrixWrongSizeException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		Matrix ret = Matrix.Zero(rows, columns);
		for (int x = 0; x < rows; x ++) {
			for (int y = 0; y < columns; y ++) {
				ret.values[x][y] = this.values[x][y] * other.values[x][y];
			}
		}
		return ret;
	}
	
	public Matrix dot(Matrix other) {
		if (this.columns != other.rows) {
			try {
				throw new MatrixWrongSizeException();
			} catch (MatrixWrongSizeException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		Matrix ret = Matrix.Zero(this.rows, other.columns);
		for (int r = 0; r < this.rows; r ++) {
			for (int c = 0; c < other.columns; c ++) {
				double sum = 0;
				for (int i = 0; i < this.columns; i ++) {
					sum += this.values[r][i] * other.values[i][c];
				}
				ret.values[r][c] = sum;
			}
		}
		return ret;
	}
	
	public Matrix mult(double k) {
		Matrix ret = Matrix.Zero(rows, columns);
		for (int x = 0; x < rows; x ++) {
			for (int y = 0; y < columns; y ++) {
				ret.values[x][y] = this.values[x][y] * k;
			}
		}
		return ret;
	}
	
	public double getValue(int r, int c) {
		return values[r][c];
	}
	
	public void setValue(int r, int c, double value) {
		values[r][c] = value;
	}
	
	@Override
	public String toString() {
		String ret = "";
		for (int r = 0; r < rows; r ++) {
			ret += String.format( "%.2f", getValue(r, 0) );
			for (int c = 1; c < columns; c ++) {
				ret += ", " + String.format( "%.2f", getValue(r, c) );
			}
			ret += "\n";
		}
		return ret;
	}

}
