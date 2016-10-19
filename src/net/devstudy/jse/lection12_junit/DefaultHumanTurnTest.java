package net.devstudy.jse.lection12_junit;

import org.junit.Test;

import net.devstudy.jse.lection06_gomoku.Cell;
import net.devstudy.jse.lection06_gomoku.CellValue;
import net.devstudy.jse.lection06_gomoku.GameTable;
import net.devstudy.jse.lection06_gomoku.impl.DefaultHumanTurn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHumanTurnTest {

	@Test
	public void testMakeTurn(){
		final StringBuilder res = new StringBuilder();
		DefaultHumanTurn t = new DefaultHumanTurn();
		t.setGameTable(new GameTable() {
			@Override
			public void setValue(int row, int col, CellValue cellValue) {
				assertEquals(0, row);
				assertEquals(1, col);
				assertEquals(CellValue.HUMAN, cellValue);
				res.append("OK");
			}
			@Override
			public void reInit() {
			}
			@Override
			public boolean isCellFree(int row, int col) {
				return false;
			}
			@Override
			public CellValue getValue(int row, int col) {
				return null;
			}
			@Override
			public int getSize() {
				return 0;
			}
			@Override
			public boolean emptyCellExists() {
				return false;
			}
		});
		Cell cell = t.makeTurn(0, 1);
		assertEquals(0, cell.getRowIndex());
		assertEquals(1, cell.getColIndex());
		if(res.length() == 0) {
			fail("Method setValue is not invoked");
		}
	}
}
