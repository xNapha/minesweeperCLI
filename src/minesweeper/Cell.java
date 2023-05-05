package minesweeper;

public class Cell {
	int positionX;
	int positionY;
	boolean isMine = false;
	int nextToMine = 0;
	boolean revealCell = false;

	public Cell(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	public void getPosition() {
		System.out.printf("X:%s, Y:%s | ", this.positionX, this.positionY);
	}

	public boolean getIsMine() {
		return this.isMine;
	}

	public String displayPiece(boolean mineTriggered) {
//		return (this.isMine) ? "|X" : "|0";
//		return (this.revealCell) ? " X" : " " + this.nextToMine;
		if (mineTriggered && this.isMine) {
			return "|X";
		} else if (this.revealCell && this.isMine) {
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

	public int getNextToMine() {
		return this.nextToMine;
	}
}
