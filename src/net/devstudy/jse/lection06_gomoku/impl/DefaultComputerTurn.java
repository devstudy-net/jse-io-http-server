package net.devstudy.jse.lection06_gomoku.impl;

import java.util.Objects;
import java.util.Random;

import net.devstudy.jse.lection05_exceptions.home.DataSet;
import net.devstudy.jse.lection05_exceptions.home.DynaArray;
import net.devstudy.jse.lection06_gomoku.Cell;
import net.devstudy.jse.lection06_gomoku.CellValue;
import net.devstudy.jse.lection06_gomoku.ComputerTurn;
import net.devstudy.jse.lection06_gomoku.GameTable;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultComputerTurn implements ComputerTurn {
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
	public Cell makeTurn() {
		CellValue[] figures = { CellValue.COMPUTER, CellValue.HUMAN };
		for (int i = winCount - 1; i > 0; i--) {
			for (CellValue cellValue : figures) {
				Cell cell = tryMakeTurn(cellValue, i);
				if (cell != null) {
					return cell;
				}
			}
		}
		return makeRandomTurn();
	}

	@Override
	public Cell makeFirstTurn() {
		Cell cell = new Cell(gameTable.getSize() / 2, gameTable.getSize() / 2);
		gameTable.setValue(cell.getRowIndex(), cell.getColIndex(), CellValue.COMPUTER);
		return cell;
	}

	protected Cell makeRandomTurn() {
		DataSet<Cell> emptyCells = getAllEmptyCells();
		if (emptyCells.size() > 0) {
			Cell randomCell = emptyCells.get(new Random().nextInt(emptyCells.size()));
			gameTable.setValue(randomCell.getRowIndex(), randomCell.getColIndex(), CellValue.COMPUTER);
			return randomCell;
		} else {
			throw new ComputerCantMakeTurnException("All cells are filled! Have you checked draw state before call of computer turn?");
		}
	}
	
	protected DataSet<Cell> getAllEmptyCells(){
		DataSet<Cell> emptyCells = new DynaArray<>();
		for (int i = 0; i < gameTable.getSize(); i++) {
			for (int j = 0; j < gameTable.getSize(); j++) {
				if (gameTable.isCellFree(i, j)) {
					emptyCells.add(new Cell(i, j));
				}
			}
		}
		return emptyCells;
	}

	protected Cell tryMakeTurn(CellValue cellValue, int notBlankCount) {
		Cell cell = tryMakeTurnByRow(cellValue, notBlankCount);
		if (cell != null) {
			return cell;
		}
		cell = tryMakeTurnByCol(cellValue, notBlankCount);
		if (cell != null) {
			return cell;
		}
		cell = tryMakeTurnByMainDiagonal(cellValue, notBlankCount);
		if (cell != null) {
			return cell;
		}
		cell = tryMakeTurnByNotMainDiagonal(cellValue, notBlankCount);
		if (cell != null) {
			return cell;
		}
		return null;
	}

	protected Cell tryMakeTurnByRow(CellValue cellValue, int notBlankCount) {
		for (int i = 0; i < gameTable.getSize(); i++) {
			for (int j = 0; j < gameTable.getSize() - winCount - 1; j++) {
				boolean hasEmptyCells = false;
				int count = 0;
				DataSet<Cell> inspectedCells = new DynaArray<>();
				for (int k = 0; k < winCount; k++) {
					inspectedCells.add(new Cell(i, j + k));
					if (gameTable.getValue(i, j + k) == cellValue) {
						count++;
					} else if (gameTable.getValue(i, j + k) == CellValue.EMPTY) {
						hasEmptyCells = true;
					} else {
						hasEmptyCells = false;
						break;
					}
				}
				if (count == notBlankCount && hasEmptyCells) {
					return makeTurnToOneCellFromDataSet(inspectedCells);
				}
			}
		}
		return null;
	}

	protected Cell tryMakeTurnByCol(CellValue cellValue, int notBlankCount) {
		for (int i = 0; i < gameTable.getSize(); i++) {
			for (int j = 0; j < gameTable.getSize() - winCount - 1; j++) {
				boolean hasEmptyCells = false;
				int count = 0;
				DataSet<Cell> inspectedCells = new DynaArray<>();
				for (int k = 0; k < winCount; k++) {
					inspectedCells.add(new Cell(j + k, i));
					if (gameTable.getValue(j + k, i) == cellValue) {
						count++;
					} else if (gameTable.getValue(j + k, i) == CellValue.EMPTY) {
						hasEmptyCells = true;
					} else {
						hasEmptyCells = false;
						break;
					}
				}
				if (count == notBlankCount && hasEmptyCells) {
					return makeTurnToOneCellFromDataSet(inspectedCells);
				}
			}
		}
		return null;
	}

	protected Cell tryMakeTurnByMainDiagonal(CellValue cellValue, int notBlankCount) {
		for (int i = 0; i < gameTable.getSize() - winCount - 1; i++) {
			for (int j = 0; j < gameTable.getSize() - winCount - 1; j++) {
				boolean hasEmptyCells = false;
				int count = 0;
				DataSet<Cell> inspectedCells = new DynaArray<>();
				for (int k = 0; k < winCount; k++) {
					inspectedCells.add(new Cell(i + k, j + k));
					if (gameTable.getValue(i + k, j + k) == cellValue) {
						count++;
					} else if (gameTable.getValue(i + k, j + k) == CellValue.EMPTY) {
						hasEmptyCells = true;
					} else {
						hasEmptyCells = false;
						break;
					}
				}
				if (count == notBlankCount && hasEmptyCells) {
					return makeTurnToOneCellFromDataSet(inspectedCells);
				}
			}
		}
		return null;
	}

	protected Cell tryMakeTurnByNotMainDiagonal(CellValue cellValue, int notBlankCount) {
		for (int i = 0; i < gameTable.getSize() - winCount - 1; i++) {
			for (int j = winCount - 1; j < gameTable.getSize(); j++) {
				boolean hasEmptyCells = false;
				int count = 0;
				DataSet<Cell> inspectedCells = new DynaArray<>();
				for (int k = 0; k < winCount; k++) {
					inspectedCells.add(new Cell(i + k, j - k));
					if (gameTable.getValue(i + k, j - k) == cellValue) {
						count++;
					} else if (gameTable.getValue(i + k, j - k) == CellValue.EMPTY) {
						hasEmptyCells = true;
					} else {
						hasEmptyCells = false;
						break;
					}
				}
				if (count == notBlankCount && hasEmptyCells) {
					return makeTurnToOneCellFromDataSet(inspectedCells);
				}
			}
		}
		return null;
	}

	protected Cell makeTurnToOneCellFromDataSet(DataSet<Cell> inspectedCells) {
		Cell cell = findEmptyCellForComputerTurn(inspectedCells);
		gameTable.setValue(cell.getRowIndex(), cell.getColIndex(), CellValue.COMPUTER);
		return cell;
	}
	
	protected Cell findEmptyCellForComputerTurn(DataSet<Cell> cells) {
		for (int i = 0; i < cells.size(); i++) {
			Cell currentCell = cells.get(i);
			if (gameTable.getValue(currentCell.getRowIndex(), currentCell.getColIndex()) != CellValue.EMPTY) {
				if (i == 0) {
					if(isCellEmpty(cells.get(i + 1))) {
						return cells.get(i + 1);
					}
				} else if(i == cells.size() - 1) {
					if(isCellEmpty(cells.get(i - 1))) {
						return cells.get(i - 1);
					}
				} else {
					boolean searchDirectionAsc = new Random().nextBoolean();
					int first = searchDirectionAsc ? i + 1 : i - 1;
					int second = searchDirectionAsc ? i - 1 : i + 1;
					if(isCellEmpty(cells.get(first))) {
						return cells.get(first);
					} else if(isCellEmpty(cells.get(second))) {
						return cells.get(second);
					}
				}
			}
		}
		throw new ComputerCantMakeTurnException("All cells are filled: "+cells);
	}
	
	protected boolean isCellEmpty(Cell cell) {
		return gameTable.getValue(cell.getRowIndex(), cell.getColIndex()) == CellValue.EMPTY;
	}
}
