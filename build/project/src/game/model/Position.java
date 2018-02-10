package game.model;

public class Position {

	private int x;
	private int y;
	
	public Position(int x,int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "" + x + " " +y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Position){
			Position pos = (Position)obj;
			return pos.x == this.x && pos.y == this.y;
		}
		return super.equals(obj);
	}
	
}
