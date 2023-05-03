package minesweeper;

public class Board<T extends Cell> {
	private int boardSize;
	public Cell[][] gameBoard;
	private int maxMines;
	private int countMinesCreated = 0;
	private int randomPosX;
	private int randomPosY;
	private boolean mineTriggered = false;
	private boolean gameWon = false;
	private NeighbouringCellPosition[] neighbouringCellPositions = new NeighbouringCellPosition[8];

	// initialise board
	public Board() {
		this.boardSize = 10;
		this.maxMines = 10;
		this.gameBoard = new Cell[this.boardSize][this.boardSize];
		this.createBoard();
	}

	public Board(int boardSize, int maxMines) {
		this.boardSize = boardSize;
		this.maxMines = maxMines;
		this.gameBoard = new Cell[this.boardSize][this.boardSize];
		this.createBoard();
	}

	public void createBoard() {
		NeighbouringCellPosition.fillNeighbouringCellPositionArray(neighbouringCellPositions);
		this.fillBoardWithCells();
		this.fillBoardWithMines();
	}

	public void fillBoardWithCells() {
		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				Cell currentPos = this.gameBoard[i][j];
				if (currentPos == null) {
					Cell field = new Cell(i, j);
					this.gameBoard[i][j] = field;
				}
			}
		}
	}

	public void increaseNeighbouringCellValue(int posX, int posY) {
		for (int i = 0; i < neighbouringCellPositions.length; i++) {
			try {
				this.gameBoard[posX + this.neighbouringCellPositions[i].getPosX()][posY
						+ this.neighbouringCellPositions[i].getPosY()].increaseNextToMine();
			} catch (Exception error) {
			}
		}
	}

	public void fillBoardWithMines() {
		while (countMinesCreated < this.maxMines) {
			this.getRandomCell();
			Cell currentPos = this.gameBoard[randomPosX][randomPosY];
			if (!currentPos.checkIfMine()) {
				Mine currentMine = new Mine(randomPosX, randomPosY);
				this.gameBoard[randomPosX][randomPosY] = currentMine;
				this.increaseNeighbouringCellValue(randomPosX, randomPosY);
				countMinesCreated += 1;
			}

		}
	}

	// generate a random number to see if the cell has a mine in it or not
	public void getRandomCell() {
		randomPosX = (int) Math.round((Math.random() * (this.boardSize - 1)));
		randomPosY = (int) Math.round((Math.random() * (this.boardSize - 1)));
		// if number is 1 return true else return false
	}

	// log the board to the console
	public void getBoard() {
		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				System.out.print(gameBoard[i][j].displayPiece());
			}
			System.out.println(" ");
		}
	}

	public void enterCellPos(int posX, int posY) {
		Cell currentCell = this.gameBoard[posX][posY];
		currentCell.setRevealCell();
		if (currentCell instanceof Mine && currentCell.getRevealCell()) {
			this.mineTriggered = true;
		}
	}

	public boolean getMineTriggered() {
		return this.mineTriggered;
	}

	public boolean getGameWon() {
		return this.gameWon;
	}

	public int getBoardSize() {
		return this.boardSize;
	}
}
