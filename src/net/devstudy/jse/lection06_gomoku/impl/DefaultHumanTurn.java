package net.devstudy.jse.lection06_gomoku.impl;

import java.util.Objects;

import net.devstudy.jse.lection06_gomoku.Cell;
import net.devstudy.jse.lection06_gomoku.CellValue;
import net.devstudy.jse.lection06_gomoku.GameTable;
import net.devstudy.jse.lection06_gomoku.HumanTurn;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHumanTurn implements HumanTurn{
	private GameTable gameTable;
	
	@Override
	public void setGameTable(GameTable gameTable) {
		Objects.requireNonNull(gameTable, "Game table can't be null");
		this.gameTable = gameTable;
	}

	@Override
	public Cell makeTurn(int row, int col) {
		gameTable.setValue(row, col, CellValue.HUMAN);
		return new Cell(row, col);
	}
}
