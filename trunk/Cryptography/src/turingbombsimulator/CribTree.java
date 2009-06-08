/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package turingbombsimulator;

/**
 *
 * @author francescoburato
 */
public class CribTree {
	private CribNode root;
	public CribTree(char[] chiaro, char[] cifra,int off){
		if(chiaro.length == cifra.length){
			boolean b[] = new boolean[cifra.length];
			for(int i = 0; i < b.length; ++i)
				b[i] = false;
			this.root = new CribNode(chiaro,cifra,b,off,chiaro[off]);
		}else
			this.root = null;
	}

	public int getPossibleWays(){
		return CribNodeList.getPossibleWays(root);
	}

	public CribNode[] getValidLoop(){
		return CribNodeList.getValidLoop(this.root).toCribNodeArray();
	}

	public static void main(String argv[]){
		CribTree n = new CribTree("swetternullsacs".toCharArray(),"UKXNHCFLNJSQCLL".toCharArray(),0);
		System.out.println(n.getPossibleWays());
		CribNode[] t = n.getValidLoop();
		for(int i = 0; i < t.length; ++i)
			System.out.println(t[i]);
	}
}
