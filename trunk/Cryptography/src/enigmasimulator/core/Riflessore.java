/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enigmasimulator.core;

/**
 *
 * @author francescoburato
 */
public class Riflessore implements Rotore{
	private int[] array;
	private final int size = 26;

	public Riflessore(){
		this.array = new int[this.size];
		for(int i = 0; i < this.size; ++i)
			this.array[i] = this.size-1-i;
	}

	public int forwardEncoding(int c) {
		return c >= 0 && c <= this.size ? this.array[c] : 0;
	}

	public int backwardEncoding(int c){
		return c >= 0 && c <= this.size ? this.array[c] : 0;
	}
}
