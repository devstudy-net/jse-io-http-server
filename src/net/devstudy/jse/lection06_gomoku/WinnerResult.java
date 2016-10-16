package net.devstudy.jse.lection06_gomoku;

import java.util.List;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface WinnerResult {

	boolean winnerExists();
	
	List<Cell> getWinnerCells();
}
