/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package turingbombsimulator;

/**
 *
 * @author francescoburato
 */
public class BadCribException extends Exception {
	private String msg;
	public BadCribException(String s){
		this.msg = s;
	}
	@Override
	public String toString(){
		return "BadCribException: " + msg;
	}
}
