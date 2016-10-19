package net.devstudy.jse.lection12_junit.home;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.jse.lection05_exceptions.home.DefaultFractionNumber;
import net.devstudy.jse.lection05_exceptions.home.FractionNumber;
import net.devstudy.jse.lection05_exceptions.home.InvalidFractionNumberArgumentException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class FactionNumberTest {
	private static final double DELTA = 0.0000000000001;
	private FractionNumber newInstance(int... params) {
		if (params.length == 0) {
			return new DefaultFractionNumber();
		} else {
			return new DefaultFractionNumber(params[0], params[1]);
		}
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testInitState() {
		FractionNumber fn = newInstance();
		assertEquals(0., fn.getValue(), DELTA);
		assertEquals("0", fn.toString());
		assertEquals(FractionNumber.DEFAULT_DIVISOR, fn.getDivisor());
	}

	@Test
	public void test1_2() {
		FractionNumber fn = newInstance(1, 2);
		assertEquals(0.5, fn.getValue(), DELTA);
		assertEquals("1/2", fn.toString());
		assertEquals(1, fn.getDividend());
		assertEquals(2, fn.getDivisor());
	}

	@Test
	public void testGetValue() {
		FractionNumber fn = newInstance();
		fn.setDividend(4);
		fn.setDivisor(4);
		assertEquals(1., fn.getValue(), DELTA);
		assertEquals(0.25, newInstance(1, 4).getValue(), DELTA);
		assertEquals(1.75, newInstance(7, 4).getValue(), DELTA);
		assertEquals(0., newInstance(0, 4).getValue(), DELTA);
		assertEquals(5., newInstance(20, 4).getValue(), DELTA);
	}

	@Test
	public void testToString() {
		FractionNumber fn = newInstance();
		fn.setDividend(4);
		fn.setDivisor(4);
		assertEquals("4/4", fn.toString());
		assertEquals("1/4", newInstance(1, 4).toString());
		assertEquals("7/4", newInstance(7, 4).toString());
		assertEquals("0", newInstance(0, 4).toString());
		assertEquals("20/4", newInstance(20, 4).toString());
	}
	
	@Test
	public void testSetInvalidDivisor() {
		thrown.expect(InvalidFractionNumberArgumentException.class);
		thrown.expectMessage("Divisor for FractionNumber can't be 0");
		FractionNumber fn = newInstance();
		fn.setDivisor(0);
	}
	
	@Test
	public void testInvalidConstructorArgument() {
		thrown.expect(InvalidFractionNumberArgumentException.class);
		thrown.expectMessage("Divisor for FractionNumber can't be 0");
		newInstance(2, 0);
	}
	
	@Test
	public void testAdd() {
		FractionNumber fn = newInstance(1, 2).add(newInstance(1, 4));
		assertEquals(0.75, fn.getValue(), DELTA);
		assertEquals("6/8", fn.toString());
		assertEquals(6, fn.getDividend());
		assertEquals(8, fn.getDivisor());
	}
	
	@Test
	public void testSub() {
		FractionNumber fn = newInstance(1, 2).sub(newInstance(1, 4));
		assertEquals(0.25, fn.getValue(), DELTA);
		assertEquals("2/8", fn.toString());
		assertEquals(2, fn.getDividend());
		assertEquals(8, fn.getDivisor());
	}
	
	@Test
	public void testMul() {
		FractionNumber fn = newInstance(1, 2).mul(newInstance(1, 4));
		assertEquals(0.125, fn.getValue(), DELTA);
		assertEquals("1/8", fn.toString());
		assertEquals(1, fn.getDividend());
		assertEquals(8, fn.getDivisor());
	}
	
	@Test
	public void testDiv() {
		FractionNumber fn = newInstance(1, 2).div(newInstance(1, 4));
		assertEquals(2.0, fn.getValue(), DELTA);
		assertEquals("4/2", fn.toString());
		assertEquals(4, fn.getDividend());
		assertEquals(2, fn.getDivisor());
	}
	
	@Test
	public void testDivWithException() {
		thrown.expect(InvalidFractionNumberArgumentException.class);
		thrown.expectMessage("secondArgument is 0. / by zero");
		newInstance(1, 2).div(newInstance(0, 4));
	}
	
	@Test
	public void testEqualsAndHashCode() {
		FractionNumber first = newInstance(1, 2);
		FractionNumber second = newInstance(1, 2);
		assertTrue(first.equals(second));
		assertTrue(first.hashCode() == second.hashCode());
	}
}
