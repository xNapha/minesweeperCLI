package minesweeper;

public class NeighbouringCellPosition {
	private int posX;
	private int posY;

	public NeighbouringCellPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public static void fillNeighbouringCellPositionArray(NeighbouringCellPosition[] neighbouringCellPositions) {
		neighbouringCellPositions[0] = new NeighbouringCellPosition((int) -1, (int) -1);
		neighbouringCellPositions[1] = new NeighbouringCellPosition((int) -1, (int) 0);
		neighbouringCellPositions[2] = new NeighbouringCellPosition((int) -1, (int) 1);
		neighbouringCellPositions[3] = new NeighbouringCellPosition((int) 0, (int) -1);
		neighbouringCellPositions[4] = new NeighbouringCellPosition((int) 0, (int) 1);
		neighbouringCellPositions[5] = new NeighbouringCellPosition((int) 1, (int) -1);
		neighbouringCellPositions[6] = new NeighbouringCellPosition((int) 1, (int) 0);
		neighbouringCellPositions[7] = new NeighbouringCellPosition((int) 1, (int) 1);
	};
}
