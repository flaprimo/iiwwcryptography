/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package turingbombsimulator;

/**
 *
 * @author francescoburato
 */
public class BombRunner implements Runnable{
	private Bomb myBomb;
	public BombRunner(Bomb b){
		this.myBomb = b;
	}

	@Override
	public void run(){
		this.myBomb.find();
	}

	public String[] getRes(){
		return this.myBomb.getResults();
	}

	public int[] getScamb(){
		return this.myBomb.getArray();
	}
}
