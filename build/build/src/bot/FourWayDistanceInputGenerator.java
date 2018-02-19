package bot;

import java.util.ArrayList;

import game.model.Game;
import game.model.Position;
import neuralNetwork.Matrix;

public class FourWayDistanceInputGenerator extends InputGenerator{

	public FourWayDistanceInputGenerator() {
		super(8);
	}
	
	@Override
	public Matrix getInput(Game game) {
		Matrix input = new Matrix(1,super.getNumInputs());
		ArrayList<Position> snake = game.getSnake().getSnake();
		Position head = game.getSnake().getPosition();
		Position apple = game.getApple().getPosition();
		
		int count = 0;
		Position curr = new Position(head.getX(),head.getY());
		boolean s = true;
		boolean a = true;
		do{
			if(!curr.equals(head)){
				if(a && curr.equals(apple)){
					a = false;
					input.set(0, 0, count);
				}else if(s){
					for(Position p : snake){
						if(p.equals(curr)){
							s = false;
							input.set(0, 1, count);
							break;
						}
					}
				}
			}
			if(!s && !a) break;
			count++;
			curr.incX();
			if(curr.getX() == game.getDimension().getX()){
				curr.setX(0);
			}
		}while(!curr.equals(head));
		
		if(a) input.set(0, 0, 0);
		if(s) input.set(0, 1, 0);
		
		count = 0;
		curr = new Position(head.getX(),head.getY());
		s = true;
		a = true;
		do{
			if(!curr.equals(head)){
				if(a && curr.equals(apple)){
					a = false;
					input.set(0, 2, count);
				}else if(s){
					for(Position p : snake){
						if(p.equals(curr)){
							s = false;
							input.set(0, 3, count);
							break;
						}
					}
				}
			}
			if(!s && !a) break;
			count++;
			curr.decX();
			if(curr.getX() == -1){
				curr.setX(game.getDimension().getX()-1);
			}
		}while(!curr.equals(head));
		
		if(a) input.set(0, 2, 0);
		if(s) input.set(0, 3, 0);
		
		count = 0;
		curr = new Position(head.getX(),head.getY());
		s = true;
		a = true;
		do{
			if(!curr.equals(head)){
				if(a && curr.equals(apple)){
					a = false;
					input.set(0, 4, count);
				}else if(s){
					for(Position p : snake){
						if(p.equals(curr)){
							s = false;
							input.set(0, 5, count);
							break;
						}
					}
				}
			}
			if(!s && !a) break;
			count++;
			curr.decY();
			if(curr.getY() == -1){
				curr.setY(game.getDimension().getY()-1);
			}
		}while(!curr.equals(head));
		
		if(a) input.set(0, 4, 0);
		if(s) input.set(0, 5, 0);
		
		count = 0;
		curr = new Position(head.getX(),head.getY());
		s = true;
		a = true;
		do{
			if(!curr.equals(head)){
				if(a && curr.equals(apple)){
					a = false;
					input.set(0, 6, count);
				}else if(s){
					for(Position p : snake){
						if(p.equals(curr)){
							s = false;
							input.set(0, 7, count);
							break;
						}
					}
				}
			}
			if(!s && !a) break;
			count++;
			curr.incY();
			if(curr.getY() == game.getDimension().getY()){
				curr.setY(0);
			}
		}while(!curr.equals(head));
		
		if(a) input.set(0, 6, 0);
		if(s) input.set(0, 7, 0);
		
		return input;
	}

}
