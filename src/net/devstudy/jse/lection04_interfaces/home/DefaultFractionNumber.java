package net.devstudy.jse.lection04_interfaces.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultFractionNumber implements FractionNumber {
	private int dividend;
	private int divisor = DEFAULT_DIVISOR;

	public DefaultFractionNumber(int dividend, int divisor) {
		super();
		setDividend(dividend);
		setDivisor(divisor);
	}

	public DefaultFractionNumber() {
		super();
	}

	@Override
	public int getDividend() {
		return dividend;
	}

	@Override
	public int getDivisor() {
		return divisor;
	}

	@Override
	public void setDividend(int dividend) {
		this.dividend = dividend;
	}

	@Override
	public void setDivisor(int divisor) {
		if (divisor != 0) {
			this.divisor = divisor;
		} else {
			System.out.println("Invalid divisor");
		}
	}

	@Override
	public double getValue() {
		return ((double) dividend) / divisor;
	}

	@Override
	public String toString() {
		return dividend == 0 ? "0" : dividend + "/" + divisor;
	}

	@Override
	public FractionNumber add(FractionNumber secondArgument) {
		int divident = this.getDividend() * secondArgument.getDivisor() + secondArgument.getDividend() * this.getDivisor();
		return new DefaultFractionNumber(divident, this.getDivisor() * secondArgument.getDivisor());
	}

	@Override
	public FractionNumber sub(FractionNumber secondArgument) {
		int divident = this.getDividend() * secondArgument.getDivisor() - secondArgument.getDividend() * this.getDivisor();
		return new DefaultFractionNumber(divident, this.getDivisor() * secondArgument.getDivisor());
	}

	@Override
	public FractionNumber mul(FractionNumber secondArgument) {
		return new DefaultFractionNumber(this.getDividend() * secondArgument.getDividend(), this.getDivisor() * secondArgument.getDivisor());
	}

	@Override
	public FractionNumber div(FractionNumber secondArgument) {
		if (secondArgument.getDividend() == 0) {
			throw new InvalidFractionNumberArgumentException("secondArgument is 0. / by zero");
		} else {
			return new DefaultFractionNumber(this.getDividend() * secondArgument.getDivisor(), this.getDivisor() * secondArgument.getDividend());
		}
	}

	public static void main(String[] args) {
		/*FractionNumber f1 = new DefaultFractionNumber();
		System.out.println(f1 + "->" + f1.getValue());
		FractionNumber f2 = new DefaultFractionNumber(1, 2);
		System.out.println(f2 + "->" + f2.getValue());
		FractionNumber f3 = new DefaultFractionNumber(3, 4);
		System.out.println(f3 + "->" + f3.getValue());
		f3.setDividend(1);
		f3.setDivisor(3);
		System.out.println(f3 + "->" + f3.getValue());
		System.out.println(f3 + "->" + f3.getDividend() + "/" + f3.getDivisor());

		System.out.println(f2 + "+" + f3 + "=" + f2.add(f3));
		System.out.println(f2 + "-" + f3 + "=" + f2.sub(f3));
		System.out.println(f2 + "*" + f3 + "=" + f2.mul(f3));
		System.out.println(f2 + "/" + f3 + "=" + f2.div(f3));*/
		
		FractionNumber n1 = new DefaultFractionNumber(1, 2);
		FractionNumber n2 = new DefaultFractionNumber();
		FractionNumber n3 = n1.div(n2);
		System.out.println(n3.getValue());
	}
}
