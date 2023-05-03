package minesweeper;

public class Cell {
	int positionX;
	int positionY;
	boolean isMine = false;
	int nextToMine = 0;
	boolean revealCell = false;

	public Cell(int i, int j) {
		this.positionX = i;
		this.positionY = j;
	}

	public void getPosition() {
		System.out.printf("X:%s, Y:%s | ", this.positionX, this.positionY);
	}

	public boolean checkIfMine() {
		return (this.isMine) ? true : false;
	}

	public String displayPiece() {
//		return (this.isMine) ? "X" : "O";
//		return (this.revealCell) ? " X" : " " + this.nextToMine;
//		return (this.isMine) ? " X" : " " + this.nextToMine;
		if (this.revealCell && this.isMine) {
			return "|X";
		} else if (this.revealCell && !this.isMine) {
			return "|" + this.nextToMine;
		} else {
			return "|_";
		}
	}

	public void increaseNextToMine() {
		this.nextToMine += 1;
	}

	public void setRevealCell() {
		this.revealCell = true;
	}

	public boolean getRevealCell() {
		return this.revealCell;
	}
}
