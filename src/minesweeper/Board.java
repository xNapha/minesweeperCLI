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
	private boolean endCurrentGame = false;
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
			int newPosX = getNewPos(posX, this.neighbouringCellPositions[i].getPosX());
			int newPosY = getNewPos(posY, this.neighbouringCellPositions[i].getPosY());
			if (isOutOfBounds(newPosX, newPosY, boardSize)) {
				this.gameBoard[newPosX][newPosY].increaseNextToMine();
			}
		}
	}

	public int getNewPos(int position, int positionAdjustment) {
		return position + positionAdjustment;

	}

	public boolean isOutOfBounds(int newPosX, int newPosY, int boardSize) {
		return (newPosX >= 0 && newPosX < boardSize && newPosY >= 0 && newPosY < boardSize);
	}

	public void fillBoardWithMines() {
		while (countMinesCreated < this.maxMines) {
			this.getRandomCell();
			this.plantMineOnBoard();
		}
	}

	public void plantMineOnBoard() {
		Cell currentPos = this.gameBoard[randomPosX][randomPosY];
		if (!currentPos.getIsMine()) {
			Mine currentMine = new Mine(randomPosX, randomPosY);
			this.gameBoard[randomPosX][randomPosY] = currentMine;
			this.increaseNeighbouringCellValue(randomPosX, randomPosY);
			countMinesCreated += 1;
		}
	}

	public void getRandomCell() {
		randomPosX = (int) Math.round((Math.random() * (this.boardSize - 1)));
		randomPosY = (int) Math.round((Math.random() * (this.boardSize - 1)));
	}

	public void getBoard() {
		this.refreshCellCounter();
		this.printHorizontalAxis();
		for (int x = 0; x < this.boardSize; x++) {
			this.printVerticalAxis(x);
			for (int y = 0; y < this.boardSize; y++) {
				Cell currentCell = gameBoard[x][y];
				this.printCurrentCell(currentCell);
				this.setCellCounter(currentCell);
			}
			System.out.println("|");
		}
		this.checkIfGameWon();
	}

	public void printCurrentCell(Cell currentCell) {
		if (this.mineTriggered) {
			System.out.print(currentCell.displayPiece(this.mineTriggered));
		} else {
			System.out.print(currentCell.displayPiece(this.mineTriggered));
		}
	}

	public void printVerticalAxis(int x) {
		if (x < 9) {
			System.out.print("0" + (x + 1));
		} else {
			System.out.print(x + 1);
		}
	}

	public void printHorizontalAxis() {
		String axisGap = "  ";
		String endofAxis = "|\n";
		System.out.print(axisGap);
		for (int i = 0; i < this.boardSize; i++) {
			if ((i + 1) % 10 == 0) {
				System.out.format("|%s", (i + 1) / 10);
			} else {
				System.out.format("|%s", (i + 1) % 10);
			}
		}
		System.out.print(endofAxis);
	}

	public void setCellCounter(Cell currentCell) {
		if (!currentCell.getIsMine() && currentCell.getRevealCell()) {
			this.cellCounter -= 1;
		}
	}

	public void checkIfGameWon() {
		if (this.cellCounter <= 0) {
			setGameWon(true);
			setEndCurrentGame(true);
		}
	}

	public void refreshCellCounter() {
		this.cellCounter = (int) (Math.pow(this.boardSize, 2) - this.maxMines);
	}

	public void enterCellPos(int posX, int posY) {
		Cell currentCell = this.gameBoard[posX][posY];
		currentCell.setRevealCell();
		if (currentCell instanceof Mine) {
			setMineTriggered(true);
			setEndCurrentGame(true);
		} else if (!currentCell.getIsMine() && currentCell.getNextToMine() == 0) {
			this.cascadeEffect(posX, posY);
		}
	}

	public boolean getMineTriggered() {
		return this.mineTriggered;
	}

	public void setMineTriggered(boolean state) {
		this.mineTriggered = state;
	}

	public boolean getGameWon() {
		return this.gameWon;
	}

	public void setGameWon(boolean state) {
		this.gameWon = state;
	}

	public int getBoardSize() {
		return this.boardSize;
	}

	public boolean getEndCurrentGame() {
		return this.endCurrentGame;
	}

	public void setEndCurrentGame(boolean state) {
		this.endCurrentGame = state;
	}

	public void cascadeEffect(int posX, int posY) {
		boolean posOutOfBounds = posX < 0 || posX > this.boardSize || posY < 0 || posY > this.boardSize;
		if (posOutOfBounds) {
			return;
		} else {
			for (int i = 0; i < neighbouringCellPositions.length; i++) {
				int newPosX = getNewPos(posX, this.neighbouringCellPositions[i].getPosX());
				int newPosY = getNewPos(posY, this.neighbouringCellPositions[i].getPosY());
				if (isOutOfBounds(newPosX, newPosY, boardSize) && !this.gameBoard[newPosX][newPosY].getRevealCell()) {
					enterCellPos(newPosX, newPosY);
				}
				if (isOutOfBounds(newPosX, newPosY, boardSize)) {
					this.gameBoard[newPosX][newPosY].setRevealCell();
				}
			}
		}

	}

}
