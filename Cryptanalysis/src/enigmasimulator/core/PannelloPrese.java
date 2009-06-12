/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmasimulator.core;

/**
 *
 * @author francescoburato
 */
public class PannelloPrese {

	private int[] forwardArray;
	private int[] backwardArray;
	private final int size = 26;

	public PannelloPrese(char[] vett) {
		if (vett.length == this.size) {
			this.forwardArray = new int[vett.length];
			for (int i = 0; i < vett.length; ++i) {
				this.forwardArray[i] = vett[i] >= 'a' && vett[i] <= 'z' ? vett[i] - 'a' : 0;
			}
			for(int i = 0; i < this.forwardArray.length; ++i)
				if(this.forwardArray[this.forwardArray[i]]!=i){
					int j;
					boolean trovato = false;
					for(j = 0; ! trovato && j < this.forwardArray.length;++j)
						trovato = this.forwardArray[j] == i;
					--j;
					int temp = this.forwardArray[j];
					this.forwardArray[j] = this.forwardArray[this.forwardArray[i]];
					this.forwardArray[this.forwardArray[i]] = temp;
				}
			this.makeBackward();
		}
	}

	private void makeBackward() {
		this.backwardArray = new int[forwardArray.length];
		for (int i = 0; i < this.size; ++i) {
			int j = 0;
			for (; j < (this.size) && !(this.forwardArray[j] == i); ++j);
			this.backwardArray[i] = j;
		}
	}
	
	public PannelloPrese(int[] vett){
		if (vett.length == this.size) {
			this.forwardArray = new int[vett.length];
			for (int i = 0; i < vett.length; ++i) {
				this.forwardArray[i] = vett[i] >= 0 && vett[i] < this.size ? vett[i] : 0;
			}
			for(int i = 0; i < this.forwardArray.length; ++i)
				if(this.forwardArray[this.forwardArray[i]]!=i){
					int j;
					boolean trovato = false;
					for(j = 0; ! trovato && j < this.forwardArray.length;++j)
						trovato = this.forwardArray[j] == i;
					--j;
					int temp = this.forwardArray[j];
					this.forwardArray[j] = this.forwardArray[i];
					this.forwardArray[i] = temp;
				}
			this.makeBackward();
		}
	}

	public PannelloPrese(String s){
		this(s.toCharArray());
	}

	public int forwardEncoding(int c) {
		return c >= 0 && c < this.size ? this.forwardArray[c] : -1;
	}

	public int backwardEncoding(int c){
		return c >= 0 && c < this.size ? this.backwardArray[c] : -1;
	}
	
	public void print(){
		for(int i = 0; i < this.forwardArray.length; ++i)
			System.out.println((char)(i+'a') + "->" + (char)(this.forwardArray[i]+'a'));
		for(int i = 0; i < this.forwardArray.length; ++i)
			System.out.println((char)(i+'a') + "->" + (char)(this.backwardArray[i]+'a'));
	}

	public char[] getPanel(){
		char[] res = new char[this.forwardArray.length];
		for(int i = 0; i < res.length; ++i)
			res[i] = (char) (this.forwardArray[i] + 'a');
		return res;
	}

	public static void main(String argv[]){
		PannelloPrese p = new PannelloPrese("juliscaertvwxyzbdfghkmnopq");
		String source = "buongiorno a tutti";
		p.print();
		/*//System.out.println(source);
		char[] s = source.toCharArray();
		String res = "";
		for(int i = 0; i < s.length; ++i){
			res = res + (char) (p.forwardEncoding(s[i]-'a')+'a');
		}
		System.out.println(res);*/
	}
}