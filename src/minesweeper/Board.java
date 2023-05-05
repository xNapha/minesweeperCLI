package minesweeper;

public class Board<T extends Cell> {
	private int boardSize;
	public Cell[][] gameBoard;
	private int maxMines;
	private int countMinesCreated = 0;
	private int randomPosX;
	private int randomPosY;
	private int cellCounter;
	private boolean mineTriggered = false;
	private boolean gameWon = false;
	private NeighbouringCellPosition[] neighbouringCellPositions = new NeighbouringCellPosition[8];

	public Board() {
		this.boardSize = 10;
		this.maxMines = 10;
		this.cellCounter = (int) (Math.pow(2, this.boardSize) - this.maxMines);
		this.gameBoard = new Cell[this.boardSize][this.boardSize];
		this.createBoard();
	}

	public Board(int boardSize, int maxMines) {
		this.boardSize = boardSize;
		this.maxMines = maxMines;
		this.cellCounter = (int) (Math.pow(2, this.boardSize) - this.maxMines);
		this.gameBoard = new Cell[this.boardSize][this.boardSize];
		this.createBoard();
	}

	public void createBoard() {
		NeighbouringCellPosition.fillNeighbouringCellPositionArray(neighbouringCellPositions);
		this.fillBoardWithCells();
		this.fillBoardWithMines();
	}

	public void fillBoardWithCells() {
		for (int x = 0; x < this.boardSize; x++) {
			for (int y = 0; y < this.boardSize; y++) {
				Cell currentPos = this.gameBoard[x][y];
				if (currentPos == null) {
					Cell field = new Cell(x, y);
					this.gameBoard[x][y] = field;
				}
			}
		}
	}

	public void increaseNeighbouringCellValue(int posX, int posY) {
		for (int i = 0; i < neighbouringCellPositions.length; i++) {
			int newPosX = posX + this.neighbouringCellPositions[i].getPosX();
			int newPosY = posY + this.neighbouringCellPositions[i].getPosY();
			if (newPosX >= 0 && newPosX < this.boardSize && newPosY >= 0 && newPosY < this.boardSize) {
				this.gameBoard[newPosX][newPosY].increaseNextToMine();
			}
		}
	}

	public void fillBoardWithMines() {
		while (countMinesCreated < this.maxMines) {
			this.getRandomCell();
			Cell currentPos = this.gameBoard[randomPosX][randomPosY];
			if (!currentPos.getIsMine()) {
				Mine currentMine = new Mine(randomPosX, randomPosY);
				this.gameBoard[randomPosX][randomPosY] = currentMine;
				this.increaseNeighbouringCellValue(randomPosX, randomPosY);
				countMinesCreated += 1;
			}
		}
	}

	public void getRandomCell() {
		randomPosX = (int) Math.round((Math.random() * (this.boardSize - 1)));
		randomPosY = (int) Math.round((Math.random() * (this.boardSize - 1)));
	}

	public void getBoard() {
		this.refreshCellCounter();
		System.out.print("  ");
		for (int i = 0; i < this.boardSize; i++) {
			if ((i + 1) % 10 == 0) {
				System.out.format("|%s", (i + 1) / 10);
			} else {
				System.out.format("|%s", (i + 1) % 10);
			}
		}
		System.out.print("|\n");
		for (int x = 0; x < this.boardSize; x++) {
			if (x < 9) {
				System.out.print("0" + (x + 1));
			} else {
				System.out.print(x + 1);
			}
			for (int y = 0; y < this.boardSize; y++) {
				Cell currentCell = gameBoard[x][y];
				System.out.print(currentCell.displayPiece());
				if (!currentCell.getIsMine() && currentCell.getRevealCell()) {
					this.cellCounter -= 1;
				}
			}
			System.out.println("|");
		}
		this.checkIfGameWon();
	}

	public void checkIfGameWon() {
		if (this.cellCounter <= 0) {
			this.gameWon = true;
		}
	}

	public void refreshCellCounter() {
		this.cellCounter = (int) (Math.pow(this.boardSize, 2) - this.maxMines);
	}

	public void enterCellPos(int posX, int posY) {
		Cell currentCell = this.gameBoard[posX][posY];
		currentCell.setRevealCell();
		if (currentCell instanceof Mine) {
			this.mineTriggered = true;
		} else if (!currentCell.getIsMine() && currentCell.getNextToMine() == 0) {
			this.cascadeEffect(posX, posY);
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

	public void cascadeEffect(int posX, int posY) {
		boolean posOutOfBounds = posX < 0 || posX > this.boardSize || posY < 0 || posY > this.boardSize;
		if (posOutOfBounds) {
			return;
		} else {
			for (int i = 0; i < neighbouringCellPositions.length; i++) {
				int newPosX = posX + this.neighbouringCellPositions[i].getPosX();
				int newPosY = posY + this.neighbouringCellPositions[i].getPosY();
				boolean newPosWithinBounds = (newPosX >= 0 && newPosX < this.boardSize && newPosY >= 0
						&& newPosY < this.boardSize);
				if (newPosWithinBounds && !this.gameBoard[newPosX][newPosY].getRevealCell()) {
					enterCellPos(newPosX, newPosY);
				}
				if (newPosWithinBounds) {
					this.gameBoard[newPosX][newPosY].setRevealCell();
				}
			}
		}

	}

}
