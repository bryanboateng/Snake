/**
 * Measurement that is used in this project.
 * 
 * @author Bryan Oppong-Boateng
 *
 */
public class Block {
	
	/**
	 * Size of one Block in pixels.
	 */
	public static final int WIDTH = 20;
	
	/**
	 * x-coordinate of the <code>Block</code>
	 */
	private int indexX;
	
	/**
	 * y-coordinate of the <code>Block</code>
	 */
	private int indexY;
	
	/**
	 * Creates a new Block with an x-coordinate and a y-coordinate
	 * 
	 * @param indexX x-coordinate of the new Object
	 * @param indexY y-coordiante of the new Object
	 */
	public Block(int indexX, int indexY){
		this.indexX = indexX;
		this.indexY = indexY;		
	}
	

	
	public int getIndexX() {
		return indexX;
	}
	public void setIndexX(int indexX) {
		this.indexX = indexX;
	}
	public int getIndexY() {
		return indexY;
	}
	public void setIndexY(int indexY) {
		this.indexY = indexY;
	}


	
	
	

}
