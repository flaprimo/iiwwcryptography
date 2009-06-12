/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package turingbombsimulator;
import enigmasimulator.core.*;
/**
 *
 * @author francescoburato
 */
public class EnigmaForAnalysis extends Enigma{
	protected int initOffset;
	public EnigmaForAnalysis(int i) throws java.io.IOException{
		super();
		this.initOffset = i;
	}

	public void addOffsets(int i){
		this.used[0].goNext(i+this.initOffset);
	}
	public String getCurrentConfig(){
		char [] c= super.getCurrentOffset();
		String s = "";
		for(int i = 0; i < c.length; ++i)
			s = s + c[i];
		return s;
	}
	public void resetAndAdd(){
		/*super.reset();
		int[] vect = new int[this.used.length];
		for(int i = 0; i < this.used.length; ++i)
			vect[i] = this.used[i].getInitialOffset();
		this.setOffsets(vect);
		this.addOffsets(this.initOffset);*/
	}
}
