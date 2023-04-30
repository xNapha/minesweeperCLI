package minesweeper;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Board startBoard = new Board();
		
		startBoard.getBoard();
		Scanner userInput = new Scanner(System.in);
		while(!startBoard.getMineTriggered()) {
			System.out.println("Pos X");
			byte posX = userInput.nextByte();
			System.out.println("Pos Y");
			byte posY = userInput.nextByte();
			startBoard.enterCellPos(posX, posY);
			startBoard.getBoard();
		}
		if(startBoard.getMineTriggered()) {
			System.out.println("You lost");			
		} else if(startBoard.getGameWon()) {
			System.out.println("You won");	
		}
	}
}
