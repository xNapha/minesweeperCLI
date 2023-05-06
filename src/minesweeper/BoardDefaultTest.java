package minesweeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardDefaultTest {
	static Board board;

	@BeforeEach
	public void setUpBoard() {
		board = new Board();
	}

	@Test
	public void getBoardSize_Default_ReturnsBoardSize10() {
		assertEquals(10, board.getBoardSize());
	}

	@Test
	public void getMaxMines_Default_ReturnsMaxMines10() {
		assertEquals(10, board.getMaxMines());
	}

	@Test
	public void isOutOfBounds_WithinBounds_ReturnsTrue() {
		assertTrue(board.isOutOfBounds(5, 5, 10));
		assertTrue(board.isOutOfBounds(9, 9, 10));
		assertTrue(board.isOutOfBounds(0, 0, 10));
	}

	@Test
	public void isOutOfBounds_OutOfBounds_ReturnsTrue() {
		assertFalse(board.isOutOfBounds(-1, 0, 10));
		assertFalse(board.isOutOfBounds(0, -1, 10));
		assertFalse(board.isOutOfBounds(10, 0, 10));
		assertFalse(board.isOutOfBounds(0, 10, 10));
	}

	@Test
	public void fillBoardWithCells_RandomIndexIsCell_ReturnsTrue() {
		assertTrue(board.getGameBoard()[0][0] instanceof Cell);
		assertTrue(board.getGameBoard()[board.getBoardSize() / 2][board.getBoardSize() / 2] instanceof Cell);
		assertTrue(board.getGameBoard()[board.getBoardSize() - 1][board.getBoardSize() - 1] instanceof Cell);
	}

	@Test
	public void plantMineOnBoard_MineAtRandomSpot_ReturnsTrue() {
		board.plantMineOnBoard();
		assertTrue(board.getGameBoard()[board.getRandomPosX()][board.getRandomPosY()] instanceof Mine);
		board.plantMineOnBoard();
		assertTrue(board.getGameBoard()[board.getRandomPosX()][board.getRandomPosY()] instanceof Mine);
		board.plantMineOnBoard();
		assertTrue(board.getGameBoard()[board.getRandomPosX()][board.getRandomPosY()] instanceof Mine);
		board.plantMineOnBoard();
		assertTrue(board.getGameBoard()[board.getRandomPosX()][board.getRandomPosY()] instanceof Mine);
	}

}
