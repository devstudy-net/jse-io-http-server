package net.devstudy.jse.lection04_interfaces;

import java.util.Arrays;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class EnumTest {

	enum CellValue {
		EMPTY(' '), 
		HUMAN('X'), 
		COMPUTER('O');
		private char value;

		private CellValue(char value) {
			this.value = value;
		}

		public char getValue() {
			return value;
		}

		public boolean isFugure() {
			return this != EMPTY;
		}
	}

	public static void main(String[] args) {
		CellValue cellValue = null;
		cellValue = CellValue.HUMAN;
		System.out.println(cellValue.name());
		System.out.println(cellValue.ordinal());
		System.out.println(cellValue.toString());
		System.out.println(Arrays.toString(CellValue.values()));
		cellValue = CellValue.valueOf("COMPUTER");
		System.out.println(cellValue.name());
		System.out.println(cellValue.ordinal());
		System.out.println(cellValue.toString());
		
		cellValue = CellValue.HUMAN;
		System.out.println(cellValue.getValue() + "->" + cellValue.isFugure());
		System.out.println(CellValue.COMPUTER.getValue() + "->" + CellValue.COMPUTER.isFugure());
		System.out.println(CellValue.EMPTY.getValue() + "->" + CellValue.EMPTY.isFugure());
	}

}
