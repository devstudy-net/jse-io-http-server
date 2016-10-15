package net.devstudy.jse.lection06_gomoku.impl;

import java.util.Objects;

import net.devstudy.jse.lection05_exceptions.home.DataSet;
import net.devstudy.jse.lection05_exceptions.home.DataUtils;
import net.devstudy.jse.lection05_exceptions.home.DynaArray;
import net.devstudy.jse.lection06_gomoku.Cell;
import net.devstudy.jse.lection06_gomoku.CellValue;
import net.devstudy.jse.lection06_gomoku.GameTable;
import net.devstudy.jse.lection06_gomoku.WinnerChecker;
import net.devstudy.jse.lection06_gomoku.WinnerResult;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultWinnerChecker implements WinnerChecker {
	private GameTable gameTable;
	private int winCount = DefaultConstants.WIN_COUNT;
	
	@Override
	public void setGameTable(GameTable gameTable) {
		Objects.requireNonNull(gameTable, "Game table can't be null");
		if(gameTable.getSize() < winCount) {
			throw new IllegalArgumentException("Size of gameTable is small: size="+gameTable.getSize()+". Required >= "+winCount);
		}
		this.gameTable = gameTable;
	}

	@Override
	public WinnerResult isWinnerFound(CellValue cellValue) {
		Objects.requireNonNull(cellValue, "cellValue can't be null");
		DataSet<Cell> result = isWinnerByRow(cellValue);
		if (result != null) {
			return new DefaultWinnerResult(result);
		}
		result = isWinnerByCol(cellValue);
		if (result != null) {
			return new DefaultWinnerResult(result);
		}
		result = isWinnerByMainDiagonal(cellValue);
		if (result != null) {
			return new DefaultWinnerResult(result);
		}
		result = isWinnerByNotMainDiagonal(cellValue);
		if (result != null) {
			return new DefaultWinnerResult(result);
		}
		return new DefaultWinnerResult(null);
	}

	protected DataSet<Cell> isWinnerByRow(CellValue cellValue) {
		for (int i = 0; i < gameTable.getSize(); i++) {
			DataSet<Cell> cells = new DynaArray<>();
			for (int j = 0; j < gameTable.getSize(); j++) {
				if (gameTable.getValue(i, j) == cellValue) {
					cells.add(new Cell(i, j));
					if (cells.size() == winCount) {
						return cells;
					}
				} else {
					cells.clear();
					if (j > gameTable.getSize() - winCount) {
						break;
					}
				}
			}
		}
		return null;
	}

	protected DataSet<Cell> isWinnerByCol(CellValue cellValue) {
		for (int i = 0; i < gameTable.getSize(); i++) {
			DataSet<Cell> cells = new DynaArray<>();
			for (int j = 0; j < gameTable.getSize(); j++) {
				if (gameTable.getValue(j, i) == cellValue) {
					cells.add(new Cell(j, i));
					if (cells.size() == winCount) {
						return cells;
					}
				} else {
					cells.clear();
					if (j > gameTable.getSize() - winCount) {
						break;
					}
				}
			}
		}
		return null;
	}

	protected DataSet<Cell> isWinnerByMainDiagonal(CellValue cellValue) {
		int winCountMinus1 = winCount - 1;
		for (int i = 0; i < gameTable.getSize() - winCountMinus1; i++) {
			for (int j = 0; j < gameTable.getSize() - winCountMinus1; j++) {
				DataSet<Cell> cells = new DynaArray<>();
				for (int k = 0; k < winCount; k++) {
					if (gameTable.getValue(i + k, j + k) == cellValue) {
						cells.add(new Cell(i + k, j + k));
						if (cells.size() == winCount) {
							return cells;
						}
					} else {
						break;
					}
				}
			}
		}
		return null;
	}

	protected DataSet<Cell> isWinnerByNotMainDiagonal(CellValue cellValue) {
		int winCountMinus1 = winCount - 1;
		for (int i = 0; i < gameTable.getSize() - winCountMinus1; i++) {
			for (int j = winCountMinus1; j < gameTable.getSize(); j++) {
				DataSet<Cell> cells = new DynaArray<>();
				for (int k = 0; k < winCount; k++) {
					if (gameTable.getValue(i + k, j - k) == cellValue) {
						cells.add(new Cell(i + k, j - k));
						if (cells.size() == winCount) {
							return cells;
						}
					} else {
						break;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 *
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static class DefaultWinnerResult implements WinnerResult {
		private final DataSet<Cell> winnerCells;

		DefaultWinnerResult(DataSet<Cell> winnerCells) {
			this.winnerCells = winnerCells != null ? DataUtils.newImmutableDataSet(winnerCells) : DataUtils.newImmutableDataSet(new Cell[0]);
		}

		public DataSet<Cell> getWinnerCells() {
			return winnerCells;
		}

		public boolean winnerExists() {
			return winnerCells.size() > 0;
		}
	}
}
