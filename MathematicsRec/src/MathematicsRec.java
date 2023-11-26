
/**
 * 
 * @author Hannah Wang <hannah.a.wang@vanderbilt.edu>
 */
class MathematicsRec {
	/**
	 * The method returns a value which: - Increases each of even decimal digits of
	 * n by one - Decreases each of odd decimal digits of n by one
	 * 
	 * @param theDecimalNumber the input decimal number (n)
	 * @return the new decimal number after digit adjustments
	 */
	public static long eduodd(long theDecimalNumber) {
		if (theDecimalNumber == 0) {
			return 1;
		} else {
			int myDigit = (int) Math.abs(theDecimalNumber % 10);
			int myNewDigit = myDigit;
			long myRest = Math.abs(theDecimalNumber / 2);
			long myModRest;

			if (myRest >= 10) {
				myModRest = eduodd(Math.abs(theDecimalNumber / 10));
			} else {
				myModRest = 0;
			}

			if (myDigit % 2 == 0) {
				myNewDigit++;
			} else {
				myNewDigit--;
			}

			if (theDecimalNumber < 0) {
				return -((myModRest * 10) + myNewDigit);
			} else {
				return (myModRest * 10) + myNewDigit;
			}
		}
	}

	/**
	 * The method accepts non-negative integer and returns a value as described
	 * below
	 * 
	 * @param theDecimalNumber is a non-negative decimal number (n)
	 * @return the value in following way: - return 1 when n = 0 - return sum of
	 *         fibby(floor(n/4)) and fibby(floor(3n/4)) when n > 0
	 */
	public static int fibby(int theDecimalNumber) {
		if (theDecimalNumber == 0) {
			return 1;
		}

		return fibby(theDecimalNumber / 4) + fibby((3 * theDecimalNumber) / 4);
	}

	/**
	 * The method prints all consecutive values of n and its fibby value
	 * 
	 * @param theLowerBound the lower bound (start)
	 * @param theUpperBound the upper bound (end)
	 */
	public static void stg(int theLowerBound, int theUpperBound) {
		int[] theFibbyValues = new int[theUpperBound + 1];
		stgPrintHelper(theLowerBound, theUpperBound, -1, theFibbyValues);
	}

	/**
	 * The helper method prints all consecutive values of n and its fibby value
	 * 
	 * @param theLowerBound  the lower bound (start)
	 * @param theUpperBound  the upper bound (end)
	 * @param theFibbyValues HashSet of fibby values between the lower and upper
	 */
	public static void stgPrintHelper(int theLowerBound, int theUpperBound, int prevFibbyVal, int[] theFibbyValues) {
		if (theLowerBound > theUpperBound) {
			return;
		}

		int currentFibbyVal = fibby(theLowerBound);

		if (currentFibbyVal != prevFibbyVal) {
			System.out.println(theLowerBound + " " + currentFibbyVal);
			prevFibbyVal = currentFibbyVal;
		}

		theFibbyValues[theLowerBound] = currentFibbyVal;

		stgPrintHelper(theLowerBound + 1, theUpperBound, prevFibbyVal, theFibbyValues);
	}

	/**
	 * The method returns the median that split the array into 3 parts
	 * 
	 * @param theList the list of integers (a)
	 * @return the median
	 */
	public static double median3(int[] theList) {
		return median3Helper(0, theList.length - 1, theList);
	}

	/**
	 * The method helps returns the median that split the array into 3 parts
	 * recursively
	 * 
	 * @param theList the list of integers (a)
	 * @return the median
	 */
	private static double median3Helper(int theStart, int theEnd, int[] theList) {
		if (theEnd - theStart == 0) {
			return theList[theStart];
		} else if (theEnd - theStart == 1) {
			return (double) (theList[theStart] + theList[theEnd]) / 2;
		} else {
			int myTemp = theEnd - theStart + 1;
			int myFirstLast;
			int myMiddle;

			if (myTemp % 3 == 0) {
				myFirstLast = myTemp / 3;
				myMiddle = myTemp / 3;
			} else if (myTemp % 3 == 1) {
				myFirstLast = myTemp / 3;
				myMiddle = (myTemp / 3) + 1;
			} else {
				myFirstLast = (myTemp / 3) + 1;
				myMiddle = myTemp / 3;
			}

			double myMedian1 = median3Helper(theStart, theStart + myFirstLast - 1, theList);
			double myMedian2 = median3Helper(theStart + myFirstLast, theStart + myFirstLast + myMiddle - 1, theList);
			double myMedian3 = median3Helper(theStart + myFirstLast + myMiddle, theEnd, theList);

			return findMedian(myMedian1, myMedian2, myMedian3);
		}
	}

	/**
	 * The method helps calculate the median after the 3 medians have been found
	 * 
	 * @param theMedian1 the median of the first piece
	 * @param theMedian2 the median of the second piece
	 * @param theMedian3 the median of the third piece
	 * @return the median of the three pieces
	 */
	private static double findMedian(double theMedian1, double theMedian2, double theMedian3) {
		double myMax = Math.max(theMedian1, theMedian2);
		myMax = Math.max(myMax, theMedian3);

		double myMin = Math.min(theMedian1, theMedian2);
		myMin = Math.min(myMin, theMedian3);

		if (theMedian1 != myMax && theMedian1 != myMin) {
			return theMedian1;
		} else if (theMedian2 != myMax && theMedian2 != myMin) {
			return theMedian2;
		} else {
			return theMedian3;
		}
	}
}