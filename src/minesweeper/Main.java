package minesweeper;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Board startBoard = new Board();
		startBoard.getBoard();

		Scanner userInput = new Scanner(System.in);
		userPlayingGame(startBoard, userInput);

		System.out.println("E to exit, R to restart");
		String command = userInput.nextLine();
	}

	public static void userPlayingGame(Board board, Scanner userInput) {
		while (!board.getMineTriggered()) {
			System.out.println("Enter Pos X and Pos Y (eg. 5:5, 2:4)");
			String positions = userInput.nextLine();
			Pattern pattern = Pattern.compile("([0-9]+:+[0-9]+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(positions);
			boolean matchFound = matcher.find();
			String[] positionsArr = positions.split(":");
			if (!matchFound || Integer.parseInt(positionsArr[0]) > board.getBoardSize()
					|| Integer.parseInt(positionsArr[1]) > board.getBoardSize()) {
				System.out.println("Invalid Input");
			} else {
				int posX = Integer.parseInt(positionsArr[0]);
				int posY = Integer.parseInt(positionsArr[1]);
				board.enterCellPos(posX, posY);
				board.getBoard();
			}
		}
		if (board.getMineTriggered()) {
			System.out.println("BOOM!! \nYou lost");
		} else if (board.getGameWon()) {
			System.out.println("You won");
		}
	}

}
