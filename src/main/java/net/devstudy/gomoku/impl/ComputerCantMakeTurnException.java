package net.devstudy.gomoku.impl;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class ComputerCantMakeTurnException extends IllegalStateException {
	private static final long serialVersionUID = -8088634040132432079L;

	public ComputerCantMakeTurnException(String message) {
		super(message);
	}
}
