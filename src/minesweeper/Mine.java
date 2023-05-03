package minesweeper;

public class Mine extends Cell {
	public Mine(int i, int j) {
		super(i, j);
		this.isMine = true;
		// TODO Auto-generated constructor stub
	}

	public void triggerCell() {

		this.revealCell = true;
	}
}
