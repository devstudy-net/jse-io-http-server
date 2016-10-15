package net.devstudy.jse.lection06_gomoku;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface WinnerChecker {
	
	void setGameTable(GameTable gameTable);

	WinnerResult isWinnerFound(CellValue cellValue);
}
