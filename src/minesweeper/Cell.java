package minesweeper;

public class Cell implements Mine{
	byte positionX;
	byte positionY;
	boolean isMine = false;
	byte nextToMine = 0;
	public Cell(byte i, byte j, boolean isMine) {
		this.positionX = i;
		this.positionY = j;
		this.isMine = isMine;
	}
	
	public void getPosition() {
		System.out.printf("X:%s, Y:%s | ", this.positionX, this.positionY);
	}
	public boolean checkIfMine() {
		return (this.isMine) ?  true : false;
	}
	

	@Override
	public void detonated() {
		// TODO Auto-generated method stub
	}
	
	public String displayPiece() {
		return (this.isMine) ? "X" : "O";
	}

	@Override
	public void setMine() {
		// TODO Auto-generated method stub
		this.isMine = true;
	}
	
	public void increaseNextToMine() {
		this.nextToMine += 1;
	}
}
