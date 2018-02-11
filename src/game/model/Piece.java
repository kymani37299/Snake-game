package game.model;

public class Piece{
	
	private Position position;
	
	public Piece(Position position){
		this.position = position;
	}
	
	public Piece(Direction direction,Position position,Game game){
		
		switch(direction){
		case UP:
			this.position = new Position(position.getX(),position.getY()-1);
			if(this.position.getY() == -1) 
				this.position.setY(game.getDimension().getY()-1);
			break;
		case DOWN:
			this.position = new Position(position.getX(),position.getY()+1);
			if(this.position.getY() == game.getDimension().getY())
				this.position.setY(0);
			break;
		case LEFT:
			this.position = new Position(position.getX()-1,position.getY());
			if(this.getPosition().getX() == -1)
				this.position.setX(game.getDimension().getX()-1);
			break;
		case RIGHT:
			this.position = new Position(position.getX()+1,position.getY());
			if(this.position.getX() == game.getDimension().getX())
				this.position.setX(0);
			break;
		}
	}
	
	public Position getPosition() {
		return position;
	}
}
