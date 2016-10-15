package net.devstudy.jse.lection06_gomoku;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HumanTurn {
	
	void setGameTable(GameTable gameTable);

	Cell makeTurn(int row, int col);
}
