package net.devstudy.jse.lection06_gomoku.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import net.devstudy.jse.lection06_gomoku.Cell;
import net.devstudy.jse.lection06_gomoku.CellValue;
import net.devstudy.jse.lection06_gomoku.ComputerTurn;
import net.devstudy.jse.lection06_gomoku.GameTable;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class RandomComputerTurn implements ComputerTurn {
	private GameTable gameTable;
	
	@Override
	public void setGameTable(GameTable gameTable) {
		Objects.requireNonNull(gameTable, "Game table can't be null");
		this.gameTable = gameTable;
	}

	@Override
	public Cell makeTurn() {
		List<Cell> emptyCells = getAllEmptyCells();
		if (emptyCells.size() > 0) {
			Cell randomCell = emptyCells.get(new Random().nextInt(emptyCells.size()));
			gameTable.setValue(randomCell.getRowIndex(), randomCell.getColIndex(), CellValue.COMPUTER);
			return randomCell;
		} else {
			throw new ComputerCantMakeTurnException("All cells are filled! Have you checked draw state before call of computer turn?");
		}
	}

	@Override
	public Cell makeFirstTurn() {
		return makeTurn();
	}

	protected List<Cell> getAllEmptyCells(){
		List<Cell> emptyCells = new ArrayList<>();
		for (int i = 0; i < gameTable.getSize(); i++) {
			for (int j = 0; j < gameTable.getSize(); j++) {
				if (gameTable.isCellFree(i, j)) {
					emptyCells.add(new Cell(i, j));
				}
			}
		}
		return emptyCells;
	}
}
