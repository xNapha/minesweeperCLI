package minesweeper;

public class NeighbouringCellPosition {
	private byte posX;
	private byte posY;
	
	public NeighbouringCellPosition(byte posX, byte posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public byte getPosX() {
		return this.posX;
	}
	public byte getPosY() {
		return this.posY;
	}
}
