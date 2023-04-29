package minesweeper;
import java.util.Arrays;

public class Board<T extends Cell> {
	public Cell[][] gameBoard = new Cell[10][10];
	private byte maxMines = 10;
	private boolean isMine;
	private byte countMinesCreated = 0;
	private byte randomPosX;
	private byte randomPosY;
	// initialise board
	public Board() {
		this.fillBoardWithCells();
		this.fillBoardWithMines();
	}
	
	public void fillBoardWithCells() {
		for(byte i = 0; i < 10; i++) {
			for(byte j = 0; j < 10; j++) {
				
				Cell field = new Cell(i,j, isMine);
				gameBoard[i][j] = field;
			}
		}
	}
	
	public void fillBoardWithMines() {
		while(countMinesCreated < this.maxMines) {
			this.getRandomCell();
			Cell currentCell = this.gameBoard[randomPosX][randomPosY];
			if(!currentCell.checkIfMine()) {
				currentCell.setMine();
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
}
