/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package turingbombsimulator.extras;

/**
 *
 * @author francescoburato
 */
public class Combinatorics {

	static public char[] permutation(char[] s1, int num) {
		// s is the input elements array and num
		// is the number which represents the permutation
		char[] s = new char[s1.length];
		for (int i = 0; i < s.length; ++i) {
			s[i] = s1[i];
		}
		int factorial = 1;
		for (int i = 2; i < s.length; i++) {
			factorial *= i; // calculates the factorial of (s.length - 1)
		}
		if (num / s.length >= factorial) // Optional. if the number is not in the
		// range of [0, s.length! - 1]
		{
			return null;
		}

		for (int i = 0; i < s.length - 1; i++) {//go over the array

			// calculates the next cell from the cells left
			// (the cells in the range [i, s.length - 1])
			int tempi = (num / factorial) % (s.length - i);

			// Temporarily saves the value of the cell needed
			// to add to the permutation this time
			char temp = s[i + tempi];

			// shift all elements to "cover" the "missing" cell
			for (int j = i + tempi; j > i; j--) {
				s[j] = s[j - 1];
			}

			s[i] = temp; // put the chosen cell in the correct spot

			factorial /= (s.length - (i + 1)); // updates the factorial

		}

		return s;
	}
	
	public static int fact(int n){
		return n <= 0 ? 1 : n * fact(n-1);
	}

	public static int[] disposizioni(int[] s1, int k, int num) {
		num = num * fact(s1.length-k);
		int[] s = new int[s1.length];
		for (int i = 0; i < s.length; ++i) {
			s[i] = s1[i];
		}
		int factorial = 1;
		for (int i = 2; i < s.length; i++) {
			factorial *= i; // calculates the factorial of (s.length - 1)
		}
		if (num / s.length >= factorial) // Optional. if the number is not in the
		// range of [0, s.length! - 1]
		{
			return null;
		}

		for (int i = 0; i < s.length - 1; i++) {//go over the array
			// calculates the next cell from the cells left
			// (the cells in the range [i, s.length - 1])
			int tempi = (num / factorial) % (s.length - i);
			// Temporarily saves the value of the cell needed
			// to add to the permutation this time
			int temp = s[i + tempi];
			// shift all elements to "cover" the "missing" cell
			for (int j = i + tempi; j > i; j--) {
				s[j] = s[j - 1];
			}
			s[i] = temp; // put the chosen cell in the correct spot
			factorial /= (s.length - (i + 1)); // updates the factorial
		}
		int[] res = new int[k];
		for(int i = 0; i < k; ++i)
			res[i] = s[i];
		return res;
	}

	public static String permutationString(char[] s1, int num) {
		// s is the input elements array and num
		// is the number which represents the permutation
		char[] s = new char[s1.length];
		for (int i = 0; i < s.length; ++i) {
			s[i] = s1[i];
		}
		int factorial = 1;
		for (int i = 2; i < s.length; i++) {
			factorial *= i; // calculates the factorial of (s.length - 1)
		}
		if (num / s.length >= factorial) // Optional. if the number is not in the
		// range of [0, s.length! - 1]
		{
			return null;
		}

		for (int i = 0; i < s.length - 1; i++) {//go over the array

			// calculates the next cell from the cells left
			// (the cells in the range [i, s.length - 1])
			int tempi = (num / factorial) % (s.length - i);

			// Temporarily saves the value of the cell needed
			// to add to the permutation this time
			char temp = s[i + tempi];

			// shift all elements to "cover" the "missing" cell
			for (int j = i + tempi; j > i; j--) {
				s[j] = s[j - 1];
			}

			s[i] = temp; // put the chosen cell in the correct spot

			factorial /= (s.length - (i + 1)); // updates the factorial

		}
		String res = "";
		for (int i = 0; i < s.length; ++i) {
			res = res + s[i];
		}
		return res;
	}

	public static String intArrayToString(int[] vett){
		String res = "";
		for(int i = 0; i < vett.length; ++i)
			res = res + vett[i];
		return res;
	}

	public static void main(String argv[]) {
		int[] disp = {0,1,2,3,4};
		for(int i = 0 ; i < 60; ++i){
			System.out.println(intArrayToString(disposizioni(disp,3,i)));
		}
	}
}
