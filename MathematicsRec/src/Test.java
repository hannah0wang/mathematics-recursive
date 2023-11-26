import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		int[] nums1 = new int[10];
		nums1[0] = 1;
		nums1[1] = 2;
		nums1[2] = -20;
		nums1[3] = -10;
		nums1[4] = 7;
		nums1[5] = 20;
		nums1[6] = -3;
		nums1[7] = 100;
		nums1[8] = 6;
		nums1[9] = 92;
		// median = 1

		int[] nums2 = new int[6];
		nums2[0] = 3;
		nums2[1] = 1;
		nums2[2] = 4;
		nums2[3] = 1;
		nums2[4] = 5;
		nums2[5] = 9;
		// median = 2.5

		int[] nums3 = new int[8];
		nums3[0] = 1;
		nums3[1] = 2;
		nums3[2] = -10;
		nums3[3] = 7;
		nums3[4] = 20;
		nums3[5] = -3;
		nums3[6] = 100;
		nums3[7] = 6;
		// median = 6
		
		int[] nums4 = {-633201, 396034, -1646352, 263104, 1668249, -1678816, -61103, 716861, -1319059, 100837};
		// median = -5283.5

		int[] nums5 = {1, 2, -5, 10, 100, 6};
		// median = 2.5
		
		int[] nums6 = {3, 1, 4, 1};
		// median = 2.5
		
		int[] nums7 = {1, 2};
		// median = 1.5
		
		System.out.println(median3(nums3));
		
		
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		Random r = new Random(54321);
		double[] rando = new double[2000];

		for (int i = 0; i < 2000; i++) {
			int x = r.nextInt(50000);
			int[] b = new int[x + 1];
			for (int j = 0; j < b.length; j++) {
				b[j] = r.nextInt() / 1024;
			}
			rando[i] = MathematicsRec.median3(b);
		}
		byte[] b = md5.digest(Arrays.toString(rando).getBytes());
		byte[] good = { 29, 25, 56, -24, -17, -68, 24, 78, -9, 25, -22, -28, -8, 123, 0, 46 };
		if (!Arrays.equals(b, good)) {
//			System.out.println(Arrays.toString(rando));
			System.out.println("Failed random checks!");
		} else {
			System.out.println("Passed random checks!");
		}
	}

	public static double median3(int[] theList) {
	    return median3Helper(0, theList.length - 1, theList);
	}

	private static double median3Helper(int theStart, int theEnd, int[] theList) {
	    if (theEnd - theStart == 0) {
	        return theList[theStart];
	    } else if (theEnd - theStart == 1) {
	        return (double) (theList[theStart] + theList[theEnd]) / 2;
	    } else {
	        int n = theEnd - theStart + 1;
	        int myFirstLast;
	        int myMiddle;

	        if (n % 3 == 0) {
	            myFirstLast = n / 3;
	            myMiddle = n / 3;
	        } else if (n % 3 == 1) {
	            myFirstLast = n / 3;
	            myMiddle = (n / 3) + 1;
	        } else {
	            myFirstLast = (n / 3) + 1;
	            myMiddle = n / 3;
	        }

	        double myMedian1 = median3Helper(theStart, theStart + myFirstLast - 1, theList);
	        double myMedian2 = median3Helper(theStart + myFirstLast, theStart + myFirstLast + myMiddle - 1, theList);
	        double myMedian3 = median3Helper(theStart + myFirstLast + myMiddle, theEnd, theList);

	        return findMedian(myMedian1, myMedian2, myMedian3);
	    }
	}

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