package minesweeper;

public class Cell implements Mine{
	byte positionX;
	byte positionY;
	boolean isMine = false;
	byte nextToMine = 0;
	boolean revealCell = false;
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
//		return (this.isMine) ? "X" : "O";
//		return (this.revealCell) ? " X" : " " + this.nextToMine;
		if(this.revealCell && this.isMine) {
			return "|X";
		}else if(this.revealCell && !this.isMine){
			return "|"+this.nextToMine;
		}else {
			return "|_";
		}
	}

	@Override
	public void setMine() {
		// TODO Auto-generated method stub
		this.isMine = true;
	}
	
	public void increaseNextToMine() {
		this.nextToMine += 1;
	}
	
	public void triggerCell() {
		this.revealCell = true;
	}
}
