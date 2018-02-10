package game.model;

public class Piece{
	
	private Position position;
	
	public Piece(Position position){
		this.position = position;
	}
	
	public Piece(Direction direction,Position position){
		
		switch(direction){
		case UP:
			this.position = new Position(position.getX(),position.getY()-1);
			break;
		case DOWN:
			this.position = new Position(position.getX(),position.getY()+1);
			break;
		case LEFT:
			this.position = new Position(position.getX()-1,position.getY());
			break;
		case RIGHT:
			this.position = new Position(position.getX()+1,position.getY());
			break;
		}
	}
	
	public Position getPosition() {
		return position;
	}
}
