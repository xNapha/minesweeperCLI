package minesweeper;
import java.util.Arrays;

public class Board<T extends Cell> {
	public Cell[][] gameBoard = new Cell[10][10];
	private byte maxMines = 10;
	private boolean isMine;
	private byte countMinesCreated = 0;
	private byte randomPosX;
	private byte randomPosY;
	private boolean mineTriggered = false;
	private boolean gameWon = false;
	private NeighbouringCellPosition[] neighbouringCellPositions = new NeighbouringCellPosition[8];
	
	// initialise board
	public Board() {
		this.fillBoardWithCells();
		this.fillNeighbouringCellPositionArray();
		this.fillBoardWithMines();
	}
	
	public void fillNeighbouringCellPositionArray() {
		neighbouringCellPositions[0] = new NeighbouringCellPosition((byte) -1, (byte) -1);
		neighbouringCellPositions[1] = new NeighbouringCellPosition((byte) -1, (byte) 0);
		neighbouringCellPositions[2] = new NeighbouringCellPosition((byte) -1, (byte) 1);
		neighbouringCellPositions[3] = new NeighbouringCellPosition((byte) 0, (byte) -1);
		neighbouringCellPositions[4] = new NeighbouringCellPosition((byte) 0, (byte) 1);
		neighbouringCellPositions[5] = new NeighbouringCellPosition((byte) 1, (byte) -1);
		neighbouringCellPositions[6] = new NeighbouringCellPosition((byte) 1, (byte) 0);
		neighbouringCellPositions[7] = new NeighbouringCellPosition((byte) 1, (byte) 1);
	};
	
	public void fillBoardWithCells() {
		for(byte i = 0; i < 10; i++) {
			for(byte j = 0; j < 10; j++) {
				Cell field = new Cell(i,j, isMine);
				gameBoard[i][j] = field;
			}
		}
	}
	
	public void increaseNeighbouringCellValue(byte posX, byte posY) {
		for(byte i = 0; i < neighbouringCellPositions.length ; i++) {
			try {
				this.gameBoard[posX + this.neighbouringCellPositions[i].getPosX()][posY + this.neighbouringCellPositions[i].getPosY()].increaseNextToMine();
			} catch (Exception error) {
			}
		}
	}
	
	public void fillBoardWithMines() {
		while(countMinesCreated < this.maxMines) {
			this.getRandomCell();
			Cell currentCell = this.gameBoard[randomPosX][randomPosY];
			if(!currentCell.checkIfMine()) {
				currentCell.setMine();
				this.increaseNeighbouringCellValue(randomPosX, randomPosY);
				this.countMinesCreated += 1;
			}
		}
	}
	
	// generate a random number to see if the cell has a mine in it or not
	public void getRandomCell() {
		randomPosX = (byte) Math.round((Math.random() * 9));
		randomPosY = (byte) Math.round((Math.random() * 9));
		// if number is 1 return true else return false
	}
	
	// log the board to the console
	public void getBoard() {
		for(byte i =0; i < 10; i++) {
			for(byte j = 0; j <10; j++) {
				System.out.print(gameBoard[i][j].displayPiece());
			}
			System.out.println(" ");
		}
	}
	
	public void enterCellPos(byte posX, byte posY) {
		Cell currentCell = this.gameBoard[posX][posY];
		currentCell.triggerCell();
		this.mineTriggered = true;
	}
	
	public boolean getMineTriggered() {
		return this.mineTriggered;
	}
	public boolean getGameWon() {
		return this.gameWon;
	}
}
