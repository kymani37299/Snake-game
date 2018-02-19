package bot.input;

import java.util.ArrayList;

import game.GlobalSettings;
import game.model.Game;
import game.model.Position;
import neuralNetwork.Matrix;

public class VisionInputGenerator extends InputGenerator{

	public VisionInputGenerator(int numInputs) {
		super(numInputs);
	}

	@Override
	public Matrix getInput(Game game) {
		Matrix input = new Matrix(1,super.getNumInputs());
		int index = 0;
		ArrayList<Position> snake = game.getSnake().getSnake();
		Position apple = game.getApple().getPosition();
		
		for(int i=0;i<GlobalSettings.getInstance().getMapSize().getX();i++){
			for(int j=0;j<GlobalSettings.getInstance().getMapSize().getY();j++){
				Position pos = new Position(i, j);
				if(apple.equals(pos)){
					input.set(0, index, 1);
				}else{
					boolean empty = true;
					for(Position p : snake){
						if(p.equals(pos)){
							empty = false;
							input.set(0, index, -1);
							break;
						}
					}
					if(empty){
						input.set(0, index, 0);
					}
				}
				index++;
			}
		}
		return input;
	}

}
