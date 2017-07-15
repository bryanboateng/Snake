import java.util.ArrayList;

/**
 * The <code>Snake</code> is an {@link ArrayList} consisting of <{@link Block}. The Snake can move forward 
 * in 4 directions (north, east, south, west). The size of the <code>Snake</code> increases if it is in contact
 * with food.
 * 
 * @author Bryan Oppong-Boateng
 *
 */
public class Snake extends ArrayList<Block>{

	/**
	 * Creates a new Snake consisting of one Block.
	 * 
	 * @param indexX indexX x-coordinate of the first Block
	 * @param indexY y-coordinate of the first Block
	 */
	public Snake(int indexX, int indexY){
		super();
		this.add(new Block(indexX, indexY));  //Creates the first Block/Head of the Snake		
	}	
	
	
	/**
	 * Adds a new Block with an x- and y-coordinate to the snake.
	 * 
	 * @param indexX x-coordinate of the new Block
	 * @param indexY y-coordinate of the new Block
	 */
	public void addBlock(int indexX, int indexY){
		Block newAddedBlock = new Block(indexX, indexY);
		this.add(newAddedBlock);
	}	

		
	/**
	 * Moves the snake constantly in one direction, according to the last key that was pressed. The size of 
	 * the snake increases if it has contact with food.
	 * 
	 * @param keystroke The keystroke that determines the direction of the snake.
	 * @param checkFood Is <code> true </code> if the snake is in contact with food.
	 */
	public void move(char keystroke, boolean checkFood){

		Block newFirstBlock;
		switch(keystroke){
			case 'w':		newFirstBlock = new Block(this.get(0).getIndexX(), this.get(0).getIndexY() - 1); //new location of the first Block
							moveUp(newFirstBlock, checkFood);
							break;
							
			case 'a':		newFirstBlock = new Block(this.get(0).getIndexX() - 1, this.get(0).getIndexY());
							moveUp(newFirstBlock, checkFood);
							break;
							
			case 's':		newFirstBlock = new Block(this.get(0).getIndexX(), this.get(0).getIndexY() + 1);
							moveUp(newFirstBlock, checkFood);
							break;
							
			case 'd':		newFirstBlock = new Block(this.get(0).getIndexX() + 1, this.get(0).getIndexY());
							moveUp(newFirstBlock, checkFood);
							break;				
		}		
	}
	
	
	/**
	 * Is used by {@link #move(char, boolean)} to put every block of the snake
	 * in place after the first one / head moves.
	 * 
	 * @param newFirstBlock The first Block of the new snake
	 * @param checkFood Is <code>true</code> if the snake is in contact with food.
	 */
	private void moveUp(Block newFirstBlock, boolean checkFood){
		Snake oldSnake = (Snake)this.clone(); //a copy of the old position of the snake is created
		this.clear();
		this.add(newFirstBlock);
		for(int i = 0; i < oldSnake.size() - 1; i++){
			this.addBlock(oldSnake.get(i).getIndexX(), oldSnake.get(i).getIndexY());
		}
		if(checkFood == true){
			this.addBlock(oldSnake.get(size() - 1).getIndexX(), oldSnake.get(size() - 1).getIndexY()); //the snake size is increased by one block 
		}		
	}
	
	
}
