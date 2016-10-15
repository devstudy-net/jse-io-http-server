package net.devstudy.jse.lection06_gomoku;

import net.devstudy.jse.lection05_exceptions.home.DataSet;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface WinnerResult {

	boolean winnerExists();
	
	DataSet<Cell> getWinnerCells();
}
