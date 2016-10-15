package net.devstudy.jse.lection05_exceptions.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface FractionNumber {

	int DEFAULT_DIVISOR = 1;

	int getDividend();

	int getDivisor();

	void setDividend(int dividend);

	void setDivisor(int divisor);

	double getValue();

	String toString();

	FractionNumber add(FractionNumber secondArgument);

	FractionNumber sub(FractionNumber secondArgument);

	FractionNumber mul(FractionNumber secondArgument);

	FractionNumber div(FractionNumber secondArgument);
}
