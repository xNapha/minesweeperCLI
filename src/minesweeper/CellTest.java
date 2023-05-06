package minesweeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest {
	Cell cell;

	@BeforeEach
	public void setUpCell() {
		cell = new Cell(10, 10);
	}

	@Test
	public void cellConstructor_Default_ReturnsInstanceOfCell() {
		assertTrue(cell instanceof Cell);
	}

	@Test
	public void getPositionX_Default_ReturnsInstanceOfCell() {
		assertEquals(10, cell.getPositionX());
	}

	@Test
	public void getPositionY_Default_ReturnsInstanceOfCell() {
		assertEquals(10, cell.getPositionY());
	}

	@Test
	public void setRevealCell_FalseThenTrue_ReturnsFalseThenTrue() {
		assertFalse(cell.getRevealCell());
		cell.setRevealCell();
		assertTrue(cell.getRevealCell());
	}

	@Test
	public void displayPiece_mineTriggeredFalseRevealCellFalse_ReturnsEmptySpace() {
		assertEquals("|_", cell.displayPiece(false));
	}

	@Test
	public void displayPiece_mineTriggeredTrueRevealCellFalse_ReturnsEmptySpace() {
		assertEquals("|_", cell.displayPiece(false));
	}

	@Test
	public void displayPiece_mineTriggeredFalseRevealCellTrue_ReturnsNextToMineValue() {
		cell.setRevealCell();
		assertEquals("|0", cell.displayPiece(false));

	}

	@Test
	public void displayPiece_mineTriggeredFalseRevealCellTrueNextToMine1_ReturnsNextToMineValue() {
		cell.setRevealCell();
		cell.increaseNextToMine();
		assertEquals("|1", cell.displayPiece(false));

	}

	@Test
	public void getNextToMine_defaultValue_ReturnsZero() {
		assertEquals(0, cell.getNextToMine());
	}

	@Test
	public void getNextToMine_increasedTo3_ReturnsZero() {
		for (byte i = 0; i < 3; i++) {
			cell.increaseNextToMine();
		}
		assertEquals(3, cell.getNextToMine());
	}

	@Test
	public void displayPiece_mineTriggeredFalseRevealCellTrueNextToMine3_ReturnsNextToMineValue() {
		cell.setRevealCell();
		for (byte i = 0; i < 3; i++) {
			cell.increaseNextToMine();
		}
		assertEquals("|3", cell.displayPiece(false));

	}
}
