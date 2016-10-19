package net.devstudy.gomoku;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class CellValueTest {

	@Test
	public void testGetValue(){
		assertEquals("O", CellValue.COMPUTER.getValue());
		assertEquals("X", CellValue.HUMAN.getValue());
		assertEquals(" ", CellValue.EMPTY.getValue());
	}
}
