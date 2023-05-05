package minesweeper;

import java.util.Scanner;

public class MineSweeper {
	private Scanner userInput = new Scanner(System.in);
	private String boardSize;
	private String regEx;
	private String boardSizeQuestion = "What board size do you want? (Eg. 20 creates a 20x20 board, leaving it blank creates a 10x10 board, Min 5, Max. 30): ";
	private String invalidInput = "Invalid input";
	private String minimumBoardSizeError = "Smaller than allowed minimum board size.";
	private String maximumBoardSizeError = "Greater than allowed maximum board size.";
	private boolean matchFound = false;
	private boolean exitGame = false;
	private int gamesWon = 0;
	private int gamesLost = 0;

	public void startGame() {
		while (!exitGame) {
			this.userInputBoardSize();
			while (!matchFound) {
				this.userInputBoardSize();
			}
			this.createBoard();
			this.exitOrRestart();
		}
	}

	private void userInputBoardSize() {
		System.out.println(boardSizeQuestion);
		boardSize = userInput.nextLine();
		String regEx = "\\d+";
		matchFound = boardSize.matches(regEx);
		if (boardSize.equals("")) {
			matchFound = true;
		} else if (!matchFound) {
			matchFound = false;
			System.out.format(
					"%s was not valid, please enter a number between 5-30 or leave it blank to create a 10x10 board.\n",
					boardSize);
		} else if (Integer.parseInt(boardSize) > 30) {
			matchFound = false;
			System.out.format("%s. %s\n", invalidInput, maximumBoardSizeError);
		} else if (Integer.parseInt(boardSize) < 5) {
			matchFound = false;
			System.out.format("%s. %s\n", invalidInput, minimumBoardSizeError);
		}
	}

	private void exitOrRestart() {
		System.out.println("E to exit, Any key to restart");
		String command = userInput.nextLine().toLowerCase();
		if (command.equals("e")) {
			this.exitGame = true;
		}
		userInput.close();
	}

	private void createBoard() {
		if (boardSize.equals("")) {
			this.gameInProgress(10, 10);
		} else {
			this.gameInProgress(Integer.parseInt(boardSize), exponentialMines(Integer.parseInt(boardSize)));
		}
	}

	private int exponentialMines(int boardSize) {
		int mines = boardSize;
//		for testing purposes only
//		if (boardSize == 5)
//			mines = 1;

		if (boardSize > 10)
			mines += (boardSize); // 10 mines | 100 cells
		if (boardSize >= 20)
			mines += (boardSize * 3); // 100 mines | 400 cells
		if (boardSize == 30)
			mines += (boardSize * 4); // 270 mines | 900 cells

		return mines;
	}

	private void gameInProgress(int boardSize, int mines) {
		Board startBoard = new Board(boardSize, mines);
		System.out.format("%s games won : %s games lost\n", gamesWon, gamesLost);
		startBoard.getBoard();
		while (!startBoard.getEndCurrentGame()) {
			System.out.println("Enter Pos X and Pos Y (eg. 2:4)");
			String positions = userInput.nextLine();
			regEx = "(\\d+:\\d+)";
			boolean matchFound = positions.matches(regEx);
			String[] positionsArr = positions.split(":");
			if (!matchFound || Integer.parseInt(positionsArr[0]) > startBoard.getBoardSize()
					|| Integer.parseInt(positionsArr[1]) > startBoard.getBoardSize()
					|| Integer.parseInt(positionsArr[0]) == 0 || Integer.parseInt(positionsArr[1]) == 0) {
				System.out.println("Invalid Input");
			} else {
				int posX = Integer.parseInt(positionsArr[1]) - 1;
				int posY = Integer.parseInt(positionsArr[0]) - 1;
				startBoard.enterCellPos(posX, posY);
			}
			startBoard.getBoard();
		}
		if (startBoard.getMineTriggered()) {
			this.gamesLost += 1;
			System.out.println("BOOM!!\nYou lost");
		} else if (startBoard.getGameWon()) {
			System.out.println("You successfully avoided all the mines!!\nYou Won!");
			this.gamesWon += 1;
		}
	}
}
