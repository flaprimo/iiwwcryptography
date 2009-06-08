/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package turingbombsimulator;

/**
 *
 * @author francescoburato
 */
public class CribNode {

	private char chiaro;
	private char cifra;
	private boolean end;
	private int offset;
	private CribNodeList sons;
	private char initChar;

	public CribNode(char[] chiaro,char[] cifra,  boolean[] fatto, int off,char initChar){
		this.chiaro = chiaro[off];
		this.cifra = cifra[off];
		this.offset = off;
		this.initChar = initChar;
		this.end = false;
		this.sons = null;
		if(this.cifra -'A' + 'a' == this.initChar)
			this.end = true;
		else{
			this.sons = new CribNodeList();
			for(int i = 0; i < chiaro.length;++i)
				if(!fatto[i]&&this.cifra - 'A' + 'a' ==chiaro[i]){
					fatto[i] = true;
					boolean b[] = new boolean[fatto.length];
					for(int j = 0; j < b.length; ++j)
						b[j] = fatto[j];
					this.sons.addNode(new CribNode(chiaro,cifra,fatto,i,initChar));
				}
			this.end = this.sons.isClosedLoop();
		}
	}

	public CribNode(CribNode copy,boolean copyList){
		this.chiaro=copy.chiaro;
		this.cifra = copy.cifra;
		this.end = copy.end;
		this.initChar = copy.initChar;
		this.offset = copy.offset;
		if(copyList)
			this.sons = new CribNodeList(copy.sons);
		else
			this.sons = null;
	}

	public boolean getEnd(){
		return this.end || this.sons.isClosedLoop();
	}

	public CribNodeList getSons(){
		return this.sons;
	}
	@Override
	public String toString(){
		return this.offset + ":" + this.chiaro + "->" + this.cifra;
	}

	public int getOffset(){
		return this.offset;
	}

	public char getCifra(){
		return this.cifra;
	}

	public char getChiaro(){
		return this.chiaro;
	}
}
