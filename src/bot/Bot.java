package bot;

import java.util.ArrayList;

import game.controller.GameController;
import game.model.Direction;
import game.model.Game;
import game.model.Position;
import neuralNetwork.Matrix;
import neuralNetwork.NeuralNetwork;

public class Bot implements GameController{

	private NeuralNetwork nn;
	private Game game;

	public Bot(NeuralNetwork nn,Game game){
		this.nn = nn;
		this.game = game;
	}
	
	public Bot(NeuralNetwork nn){
		this.nn = nn;
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	private Matrix getInput(){
		Matrix input = new Matrix(1,8);
		ArrayList<Position> snake = this.game.getSnake().getSnake();
		Position head = this.game.getSnake().getPosition();
		Position apple = this.game.getApple().getPosition();
		int map[][] = new int[this.game.getDimension().getX()][this.game.getDimension().getY()];
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				map[i][j] = 0;
				if(head.equals(new Position(i,j))){
					map[i][j] = 1;
				}else if(apple.equals(new Position(i,j))){
					map[i][j] = 2;
				}else{
					Position pos = new Position(i,j);
					for(Position p: snake){
						if(p.equals(pos)){
							map[i][j] = 3;
							break;
						}
					}
				}
			}
		}
		
		//UP
		int count = 0;
		Position curr = new Position(head.getX(),head.getY());
		boolean s = true;
		boolean a = true;
		for(int i=0;i<this.game.getDimension().getX();i++){
			count++;
			if(map[curr.getX()][curr.getY()]!=0){
				if(s && map[curr.getX()][curr.getY()]==3){
					input.set(0, 0, count);
					s = false;
				}else if(a && map[curr.getX()][curr.getY()]==2){
					input.set(0, 4, count);
					a = false;
				}
			}
			if(!s && !a) break;
			curr.incX();
			if(curr.getX() == this.game.getDimension().getX()){
				curr.setX(0);
			}
		}
		if(s) input.set(0, 0, 0);
		if(a) input.set(0, 4, 0);
		
		//DOWN
		count = 0;
		curr = new Position(head.getX(),head.getY());
		s = true;
		a = true;
		for(int i=0;i<this.game.getDimension().getX();i++){
			count++;
			if(map[curr.getX()][curr.getY()]!=0){
				if(s && map[curr.getX()][curr.getY()]==3){
					input.set(0, 1, count);
					s = false;
				}else if(a && map[curr.getX()][curr.getY()]==2){
					input.set(0, 5, count);
					a = false;
				}
			}
			if(!s && !a) break;
			curr.decX();
			if(curr.getX() == -1){
				curr.setX(this.game.getDimension().getX()-1);
			}
		}
		
		if(s) input.set(0, 1, 0);
		if(a) input.set(0, 5, 0);
		
		//LEFT
		count = 0;
		curr = new Position(head.getX(),head.getY());
		s = true;
		a = true;
		for(int i=0;i<this.game.getDimension().getX();i++){
			count++;
			if(map[curr.getX()][curr.getY()]!=0){
				if(s && map[curr.getX()][curr.getY()]==3){
					input.set(0, 2, count);
					s = false;
				}else if(a && map[curr.getX()][curr.getY()]==2){
					input.set(0, 6, count);
					a = false;
				}
			}
			if(!s && !a) break;
			curr.decY();
			if(curr.getY() == -1){
				curr.setY(this.game.getDimension().getY()-1);
			}
		}
		
		if(s) input.set(0, 2, 0);
		if(a) input.set(0, 6, 0);
		
		//RIGHT
		count = 0;
		curr = new Position(head.getX(),head.getY());
		s = true;
		a = true;
		for(int i=0;i<this.game.getDimension().getX();i++){
			count++;
			if(map[curr.getX()][curr.getY()]!=0){
				if(s && map[curr.getX()][curr.getY()]==3){
					input.set(0, 3, count);
					s = false;
				}else if(a && map[curr.getX()][curr.getY()]==2){
					input.set(0, 7, count);
					a = false;
				}
			}
			if(!s && !a) break;
			curr.incY();
			if(curr.getY() == this.game.getDimension().getY()){
				curr.setY(0);
			}
		}
		
		if(s) input.set(0, 3, 0);
		if(a) input.set(0, 7, 0);
		
		return input;
	}
	
	public Game getGame(){
		return this.game;
	}
	
	@Override
	public Direction getAction() {
		Matrix input = this.getInput();
		Matrix output = nn.feedFoward(input);
		double max = output.get(0, 0);
		for(int i=1;i<4;i++){
			if(output.get(0, i)>max) max = output.get(0, i);
		}
		if(output.get(0, 0) == max) return Direction.UP;
		else if(output.get(0, 1) == max) return Direction.DOWN;
		else if(output.get(0, 2) == max) return Direction.LEFT;
		else return Direction.RIGHT;
	}

}
