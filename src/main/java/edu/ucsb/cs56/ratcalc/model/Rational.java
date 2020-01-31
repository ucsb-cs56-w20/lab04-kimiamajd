package edu.ucsb.cs56.ratcalc.model;


/** A class to represent a rational number
    with a numerator and denominator
    @author P. Conrad for CS56 F16
    */

public class Rational {

    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
	if (a==0)
	    return b;
	else if (b==0)
	    return a;
	else
	    return gcd(b%a, a);
    }
    /**Constructor for rational class */
    public Rational() {
	this.num = 1;
	this.denom = 1;
    }
	/**
	 * parameterized constructor for rational class
	 * @param num numerator
	 * @param denom denominator
	 * 
	 */
    public Rational(int num, int denom) {
	if (denom== 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	if (num != 0) {
	    int gcd = Rational.gcd(num,denom);
	    this.num /= gcd;
	    this.denom /= gcd;
	}
    }
	/**
	 * @return the rational number as a string 
 	*/
    public String toString() {
	if (denom < 0) {
		denom = -1 * denom;
		num = -1 * num;
	}
	if (denom == 1 || num == 0)
	    return "" + num;
	return num + "/" + denom;
    }
	/**
 	* 
 	* @return the numerator
 	*/
	public int getNumerator() { return this.num; }
 
	/**
	 * 
	 * @return the denominator
	 */
    public int getDenominator() { return this.denom; }
	/**
	 * 
	 * @param r a rational number 
	 * @return product of this and r
	 */
    public Rational times(Rational r) {
	return new Rational(this.num * r.num,
			    this.denom * r.denom);
    }
	
	/**
	 * 
	 * @param a a rational number
	 * @param b another rational number
	 * @return product of rational numbers a and b
	 */
    public static Rational product(Rational a, Rational b) {
	return new Rational(a.num * b.num,
			    a.denom * b.denom);
    }
	
	/**
	 * 
	 * @param a an integer
	 * @param b an integer
	 * @return least common multiple (always positive) of integers a and b.
	 */
	public static int lcm(int a, int b){
		return Math.abs(a*b/gcd(a,b));
	}
	
	/**
	 * @return the sum of this and rational number r 
	 * @param r a rational number
	 */
	public Rational plus(Rational r){
		int lcm = Rational.lcm(this.denom,r.denom);

		int thisNum = this.num*(lcm/this.denom);
		int rNum = r.num*(lcm/r.denom);
		return new Rational(thisNum+rNum,lcm);
	}

	/**
	 * @return the sum of rational numbers a and b
	 * @param a a rational number 
	 * @param b another rational number
	 */
	public static Rational sum(Rational a, Rational b){
		return a.plus(b);
	}

	/**
	 * 
	 * @param r the number to be subtracted by
	 * @return this minus r
	 */
	public Rational minus(Rational r) {
		Rational negativeR = new Rational(-1*r.num, r.denom); 
		return this.plus(negativeR);
	}

	/**
	 * 
	 * @param a Rational number we are subtracting from
	 * @param b Rational number we are subtracting by
	 * @return a minus b
	 */
	 public static Rational difference (Rational a, Rational b) {
		return a.minus(b);
	}

	/**
	Returns the reciprocal of rational number. If numerator is 0, throws ArithmeticException
	 */
/**
 * 
 * @return the reciprocal of this rational number. 
 * Throws ArithmeticException when numerator is 0
 * @throws ArithmeticException
 */
	 public Rational reciprocalOf(){
		 if (this.num == 0) {
			 throw new ArithmeticException("Numerator may not be 0");
		 }
		return new Rational (this.denom, this.num);
	 }

	
	 /**
	  * 
	  * @param r the divisor
	  * @return this number divided by r
	  */
	public Rational dividedBy (Rational r) {
		return this.times(r.reciprocalOf());
	}


	/**
	 * @return a divided by b
	 * @param a the dividend
	 * @param b the divisor
	 */
	 public static Rational quotient(Rational a, Rational b) {
		 return a.dividedBy(b);
	 }
    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
	Rational r = new Rational(5,7);
	System.out.println("r.getNumerator()=" + r.getNumerator());
	System.out.println("r.getDenominator()=" + r.getDenominator());
    }

}
