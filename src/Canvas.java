import java.awt.Color;
import java.awt.*;
import javax.swing.JPanel;

/**
 * {@link JPanel} which displays all graphical aspects of the game. 
 * 
 * @author Bryan Oppong-Boateng
 *
 */
public class Canvas extends JPanel{
	/**
	 * Length of one site of the square in {@link Block}s
	 */
	public static final int BLOCKAMOUNT = 25;
	
	private Snake snake;
	private Block food;
	private boolean gameOver;
	

	public Canvas(){
		super();
		this.setPreferredSize(new Dimension(Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2,Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2));
		snake = new Snake(BLOCKAMOUNT / 2, BLOCKAMOUNT / 2);
		food = new Block(BLOCKAMOUNT / 2,BLOCKAMOUNT / 3);
		snake.addBlock(snake.get(0).getIndexX() + 1, snake.get(0).getIndexY());
	}

	
	
	public void paint(Graphics g){
		if(gameOver == false){
			g.setColor(new Color(0x4f772d));
			g.fillRect(Block.WIDTH, Block.WIDTH, Block.WIDTH*BLOCKAMOUNT, Block.WIDTH*BLOCKAMOUNT);
			g.setColor(new Color(0x132a13));
			g.fillRect(0, 0, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2, Block.WIDTH);
			g.fillRect(Block.WIDTH*BLOCKAMOUNT + Block.WIDTH, 0, Block.WIDTH, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2);
			g.fillRect(0, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2, Block.WIDTH);
			g.fillRect(0, 0, Block.WIDTH, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2);
			g.setColor(new Color(0xecf39e));
			for(int i = 0; i < snake.size(); i++){
				g.fillRect(snake.get(i).getIndexX()*Block.WIDTH, snake.get(i).getIndexY()*Block.WIDTH , Block.WIDTH, Block.WIDTH);
			}		
			g.setColor(new Color(0xffffff));
			g.fillRect(food.getIndexX()*Block.WIDTH, food.getIndexY() * Block.WIDTH, Block.WIDTH, Block.WIDTH);
		}else{
			g.setColor(new Color(0x4f772d));
			g.fillRect(Block.WIDTH, Block.WIDTH, Block.WIDTH*BLOCKAMOUNT, Block.WIDTH*BLOCKAMOUNT);
			g.setColor(new Color(0x132a13));
			g.fillRect(0, 0, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2, Block.WIDTH);
			g.fillRect(Block.WIDTH*BLOCKAMOUNT + Block.WIDTH, 0, Block.WIDTH, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2);
			g.fillRect(0, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2, Block.WIDTH);
			g.fillRect(0, 0, Block.WIDTH, Block.WIDTH*BLOCKAMOUNT + Block.WIDTH * 2);
			g.setColor(new Color(0xecf39e));			
			drawNumber(snake.size(), g);
			}		
	}
	
	
	public void drawNumber(int score, Graphics g){
		int[] arrayNumber = getNumberInDisplayFormatMax3digits(score);
		for(int i = 0; i < 3; i++){
			drawDigit(arrayNumber[i], i, g);
		}
	}

	private void drawDigit(int digit, int index, Graphics g){
		//Source http://www.identifont.com/samples/core/Pixelette.gif (*)
		
		switch(digit){
			case 0:		g.fillRect((7 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  3 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 15 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  3 * Block.WIDTH);
						g.fillRect((8 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						break;
						
			case 1:		g.fillRect((7 + index * 5) * Block.WIDTH, 15 * Block.WIDTH, 3 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((8 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 1 * Block.WIDTH,  4 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						break;
						
			case 2:		g.fillRect((6 + index * 5) * Block.WIDTH, 15 * Block.WIDTH, 4 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((8 + index * 5) * Block.WIDTH, 13 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						break;
			
			case 3:		g.fillRect((6 + index * 5) * Block.WIDTH, 15 * Block.WIDTH, 3 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 13 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 3 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						break;
			
			case 4:		g.fillRect((9 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 1 * Block.WIDTH,  5 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 13 * Block.WIDTH, 1 * Block.WIDTH,  2 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((8 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						break;
			
			case 5:		g.fillRect((6 + index * 5) * Block.WIDTH, 15 * Block.WIDTH, 3 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 13 * Block.WIDTH, 4 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 3 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 1 * Block.WIDTH,  2 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						break;
						
			case 6:		g.fillRect((7 + index * 5) * Block.WIDTH, 15 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 13 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  3 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						break;
						
			case 7:		g.fillRect((9 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 1 * Block.WIDTH,  2 * Block.WIDTH);
						g.fillRect((8 + index * 5) * Block.WIDTH, 13 * Block.WIDTH, 1 * Block.WIDTH,  3 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 3 * Block.WIDTH,  1 * Block.WIDTH);
						break;
						
			case 8:		g.fillRect((7 + index * 5) * Block.WIDTH, 15 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 14 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 13 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1* Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						break;
						
			case 9:		g.fillRect((7 + index * 5) * Block.WIDTH, 15 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 13 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((7 + index * 5) * Block.WIDTH, 11 * Block.WIDTH, 2 * Block.WIDTH,  1 * Block.WIDTH);
						g.fillRect((9 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  3 * Block.WIDTH);
						g.fillRect((6 + index * 5) * Block.WIDTH, 12 * Block.WIDTH, 1 * Block.WIDTH,  1 * Block.WIDTH);
						break;				
		}		
	}
	
	
	
	/**
	 * Converts a Number into a format that is used in {@link #drawNumber(int, Graphics)}.
	 *<p>
	 *1 digit number - 001
	 *<p>
	 *2 digit number - 011
	 * @param number Up to a 3-Digit number.
	 * @return An Array consisting of integers.
	 */
	private int[] getNumberInDisplayFormatMax3digits(int number){
		int[] outputScore = new int[3];
		if(number < 100){
			if(number < 10){
				outputScore[0] = 0;
				outputScore[1] = 0;
				outputScore[2] = number;
			}else{
				outputScore[0] = 0;
				outputScore[1] = number / 10;
				outputScore[2] = number - outputScore[1] * 10;
			}			
		}else{
			outputScore[0] = number / 100;
			outputScore[1] = ((number - outputScore[0] * 100)/10);
			outputScore[2] = (number - (outputScore[1] * 10) - (outputScore[0] * 100));
		}
		return outputScore;
	}



	public Snake getSnake() {
		return snake;
	}
	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	public Block getFood() {
		return food;
	}
	public void setFood(Block food) {
		this.food = food;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	

}
