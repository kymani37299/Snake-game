package neuralNetwork;

import java.util.Random;

public class Matrix {
	private final static Random rand = new Random();
	private int nRows;
	private int nColumns;
	private double matrix[][];
	
	public Matrix(int nRows,int nColumns){
		this.nRows = nRows;
		this.nColumns = nColumns;
		this.matrix = new double[nRows][nColumns];
		for(int i=0;i<nRows;i++){
			for(int j=0;j<nColumns;j++){
				this.matrix[i][j] = 0;
			}
		}
	}
	
	public Matrix(double matrix[][]){
		this.nRows = matrix.length;
		this.nColumns = matrix[0].length;
		this.matrix = matrix;
	}
	
	public void randomize(double lb,double ub){
		for(int i=0;i<nRows;i++){
			for(int j=0;j<nColumns;j++){
				this.matrix[i][j] = lb + (ub-lb)*rand.nextDouble();
			}
		}
	}
	
	public void randomize(int x,int y,double lb,double ub){
		this.matrix[x][y] = lb + (ub-lb)*rand.nextDouble();
	}
	
	public Matrix multiply(Matrix other){
		assert this.nColumns == other.nRows: "Invalid matrix multiplication (" 
				+ this.nRows + "x" + this.nColumns + ")" + "x" 
				+"(" + other.nRows + "x" + other.nColumns + ")";
		Matrix product = new Matrix(this.nRows,other.nColumns);
		for(int i=0;i<this.nRows;i++){
			for(int j=0;j<other.nColumns;j++){
				double sum = 0;
				for(int k=0;k<this.nColumns;k++){
					sum += this.matrix[i][k]*other.matrix[k][j];
				}
				product.matrix[i][j] = sum;
			}
		}
		return product;
	}
	
	public void applySigmoid(){
		for(int i=0;i<this.nRows;i++){
			for(int j=0;j<this.nColumns;j++){
				this.matrix[i][j] = (2/(1+Math.exp(-this.matrix[i][j])))-1;
			}
		}
	}
	
	public void set(int x,int y,double val){
		this.matrix[x][y] = val;
	}
	
	public double get(int x,int y){
		return this.matrix[x][y];
	}
	
	public int getnRows() {
		return nRows;
	}

	public int getnColumns() {
		return nColumns;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<this.nRows;i++){
			for(int j=0;j<this.nColumns;j++){
				sb.append(matrix[i][j] + " ");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
