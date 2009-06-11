/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package turingbombsimulator;

/**
 *
 * @author francescoburato
 */
public class AlanTuringBombs {
	private BombRunner[] threads;
	private String crib;
	private String codedCrib;
	private int initOffset;
	public AlanTuringBombs(String crib, String codedcrib, int initOffset) throws BadCribException{
		this.crib = crib;
		this.codedCrib = codedcrib;
		this.threads = new BombRunner[60];
		this.initOffset = initOffset;
		int[] base = {0,1,2,3,4};
		for(int i = 0; i < this.threads.length; ++i){
			this.threads[i] = new BombRunner(new Bomb(this.crib,this.codedCrib,this.initOffset,disposizioni(base,3,i)));
		}
	}

	public void startThemAll(){
		Thread[] t = new Thread[this.threads.length];
		for(int i = 0; i < t.length; ++i){
			t[i] = new Thread(this.threads[i]);
			t[i].start();
		}
		for(int i = 0; i < t.length; ++i){
			try{
			t[i].join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void printAll(){
		for(int i = 0; i < this.threads.length; ++i){
			String[] s = this.threads[i].getRes();
			String res = "";
			for(int j = 0; j < this.threads[i].getScamb().length; ++j)
				res = res + this.threads[i].getScamb()[j] + " ";
			System.out.println(res);
			for(int j = 0; j < s.length; ++j)
				System.out.println(s[j]);
		}
	}
	public static int fact(int n) {
		return n <= 0 ? 1 : n * fact(n - 1);
	}

	public static int[] disposizioni(int[] s1, int k, int num) {
		num = num * fact(s1.length - k);
		int[] s = new int[s1.length];
		for (int i = 0; i < s.length; ++i) {
			s[i] = s1[i];
		}
		int factorial = 1;
		for (int i = 2; i < s.length; i++) {
			factorial *= i;
		}
		if (num / s.length >= factorial) {
			return null;
		}

		for (int i = 0; i < s.length - 1; i++) {
			int tempi = (num / factorial) % (s.length - i);
			int temp = s[i + tempi];
			for (int j = i + tempi; j > i; j--) {
				s[j] = s[j - 1];
			}
			s[i] = temp;
			factorial /= (s.length - (i + 1));
		}
		int[] res = new int[k];
		for (int i = 0; i < k; ++i) {
			res[i] = s[i];
		}
		return res;
	}

	public static void main(String argv[]) throws Exception{
		AlanTuringBombs a = new AlanTuringBombs("swetternull", "XOHMKMOEUKXNHCFLNJSQCLL",0);
		a.startThemAll();
		a.printAll();
	}
}
