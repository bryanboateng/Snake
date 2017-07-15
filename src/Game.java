import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import javax.swing.JFrame;


public class Game implements KeyListener{
	
	
	private JFrame display;
	private Canvas canvas;
	private char lastKeystroke;
	private boolean validKeyPress;



	public Game(){
		validKeyPress = true;
		canvas = new Canvas();
		display = new JFrame("Snake");
		display.addKeyListener(this);
		display.setFocusable(true);
		display.add(canvas);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setResizable(false);
		display.setVisible(true);
		display.pack();		
	}
	
	public static void main(String[] args){
		Game bryan = new Game();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(bryan.new TimerTaskMove(), 0, 80);
	}	
	
	private boolean isContact(Block contactBlock){
		if(canvas.getSnake().get(0).getIndexX() == contactBlock.getIndexX() && canvas.getSnake().get(0).getIndexY() == contactBlock.getIndexY() ){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public boolean isContactWithBarrier(){
		
		boolean rowUp = false;
		boolean rowRight = false;
		boolean rowDown  = false;
		boolean rowLeft = false;

		
		for(int i = 1; i <= Canvas.BLOCKAMOUNT; i++){
			if(isContact(new Block(0,i)) == true){
				rowUp = true;
			}
			if(isContact(new Block(i,Canvas.BLOCKAMOUNT + 1)) == true){
				rowRight = true;
			}
			if(isContact(new Block(Canvas.BLOCKAMOUNT + 1,i)) == true){
				rowDown = true;
			}
			if(isContact(new Block(i,0)) == true){
				rowLeft = true;
			}
		}
		
		if(rowUp == true || rowRight == true || rowDown == true || rowLeft == true){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isContactWithFood(){
		return isContact(canvas.getFood());
	}

	public boolean isSelfContact(){
		boolean contact = false;
		for(int i = 1; i < canvas.getSnake().size(); i++){
			if(isContact(canvas.getSnake().get(i))){
				contact = true;
			}
		}
		return contact;
	}
	
	
	public Block getNewPositionFood(){
		Block[][] positions = new Block[Canvas.BLOCKAMOUNT][Canvas.BLOCKAMOUNT];
		for(int y = 0; y < Canvas.BLOCKAMOUNT; y++){
			for(int x = 0; x < Canvas.BLOCKAMOUNT; x++){
				positions[y][x] = new Block(y + 1, x + 1);
			}
		}
		for(int y = 0; y < Canvas.BLOCKAMOUNT; y++){
			for(int x = 0; x < Canvas.BLOCKAMOUNT; x++){
				for( int g = 0; g < canvas.getSnake().size(); g++){
					if(positions[y][x] == null){
						continue;
					}
					if(positions[y][x].getIndexX() == canvas.getSnake().get(g).getIndexX() && positions[y][x].getIndexY() == canvas.getSnake().get(g).getIndexY() ){
						positions[y][x] = null;
					}
				}
			}
		}
		
		
		
		ArrayList<Block> freePositions = new ArrayList<Block>();
		
		for(int y = 0; y < Canvas.BLOCKAMOUNT; y++){
			for(int x = 0; x < Canvas.BLOCKAMOUNT; x++){
				if(positions[y][x] != null){
					freePositions.add(positions[y][x]);
				}
			}
		}
		int indexOfNewPosition = (int) (Math.random()*freePositions.size());
		return freePositions.get(indexOfNewPosition);
	}
	
	public void keyPressed(KeyEvent event){
		if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_UP){
			if(lastKeystroke != 'w' && lastKeystroke  != 's' && validKeyPress == true ){
				lastKeystroke = 'w';
				validKeyPress = false;
			}
		}
		if(event.getKeyCode() == KeyEvent.VK_A || event.getKeyCode() == KeyEvent.VK_LEFT){
			if(lastKeystroke != 'a' && lastKeystroke  != 'd' && validKeyPress == true){
				lastKeystroke = 'a';
				validKeyPress = false;
			}
		}
		if(event.getKeyCode() == KeyEvent.VK_S || event.getKeyCode() == KeyEvent.VK_DOWN){
			if(lastKeystroke != 'w' && lastKeystroke  != 's' && validKeyPress == true){
				lastKeystroke = 's';
				validKeyPress = false;
			}
		}
		if(event.getKeyCode() == KeyEvent.VK_D || event.getKeyCode() == KeyEvent.VK_RIGHT){
			if(lastKeystroke != 'a' && lastKeystroke  != 'd' && validKeyPress == true){
				lastKeystroke = 'd';
				validKeyPress = false;
			}
		}
		if(event.getKeyCode() == KeyEvent.VK_ESCAPE){
			newGame();
		}
		
	}	
	
	public void keyReleased(KeyEvent event){
	}
	
	public void keyTyped(KeyEvent event){
	}
	
	
	public void newGame(){
		lastKeystroke = '\u0000';
		display.remove(canvas);		
		canvas = new Canvas();
		display.add(canvas);		
		canvas.setGameOver(false);
		display.revalidate();
	}
	






	
	
	
		
	private class TimerTaskMove extends TimerTask{

		public void run(){
			

			if(isContactWithBarrier() == true || isSelfContact() == true ){
				canvas.setGameOver(true);
				lastKeystroke = '\u0000';
			}



			if(isContactWithFood() == true){
				canvas.setFood(getNewPositionFood());
				canvas.getSnake().move(lastKeystroke, true);
			}else{
				canvas.getSnake().move(lastKeystroke, false);
			}
			
			validKeyPress = true;
			
			canvas.repaint();			
		}		
	}
	
	
}




