package minesweeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MineTest {
	Mine mine;

	@BeforeEach
	public void setUpMine() {
		mine = new Mine(10, 10);
	}

	@Test
	public void mineConstructor_Default_ReturnsInstanceOfMine() {
		assertTrue(mine instanceof Mine);
	}

	@Test
	public void mineConstructor_Default_ReturnsInstanceOfCell() {
		assertTrue(mine instanceof Cell);
	}

	@Test
	public void getPositionX_Default_ReturnsInstanceOfMine() {
		assertEquals(10, mine.getPositionX());
	}

	@Test
	public void getPositionY_Default_ReturnsInstanceOfMine() {
		assertEquals(10, mine.getPositionY());
	}

	@Test
	public void setRevealmine_FalseThenTrue_ReturnsFalseThenTrue() {
		assertFalse(mine.getRevealCell());
		mine.setRevealCell();
		assertTrue(mine.getRevealCell());
	}

	@Test
	public void displayPiece_mineTriggeredFalseRevealmineFalse_ReturnsEmptySpace() {
		assertEquals("|_", mine.displayPiece(false));
	}

	@Test
	public void displayPiece_mineTriggeredTrueRevealmineFalse_ReturnsDetonatedMine() {
		assertEquals("|X", mine.displayPiece(true));
	}

}
