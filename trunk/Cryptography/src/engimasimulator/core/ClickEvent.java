/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engimasimulator.core;

/**
 *
 * @author francescoburato
 */
public class ClickEvent {
	private int offsets;
	public ClickEvent(){
		this(1);
	}
	public ClickEvent(int o){
		this.offsets = o;
	}

	public int getOffsets(){
		return this.offsets;
	}
}
