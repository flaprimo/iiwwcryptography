/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package turingbombsimulator;

/**
 *
 * @author francescoburato
 */
public class Bomb {

	private char[] crib;
	private char[] codedcrib;
	private EnigmaForAnalysis enigmas[];
	private CribNode[] vett;
	private int offset;
	private int[] scambUsed;
	private java.util.ArrayList<String> possibleRes;

	public Bomb(String crib, String codedcrib, int initOffset, int[] ord) throws BadCribException {
		this.crib = crib.toCharArray();
		this.codedcrib = codedcrib.toCharArray();
		this.offset = initOffset;
		this.configure();
		this.possibleRes = new java.util.ArrayList<String>();
		if(this.vett == null)
			throw new BadCribException("Crib senza ciclo");
		else
			this.enigmas = new EnigmaForAnalysis[this.vett.length];
		this.scambUsed = new int[ord.length];
		for(int i = 0; i < this.scambUsed.length; ++i)
			this.scambUsed[i] = ord[i];
		try {
			for (int i = 0; i < this.enigmas.length; ++i) {
				this.enigmas[i] = new EnigmaForAnalysis(initOffset);
				this.enigmas[i].setUsed(this.scambUsed);
				this.enigmas[i].setPanel("abcdefghijklmnopqrstuvwxyz");
				this.enigmas[i].setOffsets("aaa".toCharArray());
				//int j = this.vett[i].getOffset();
				this.enigmas[i].addOffsets(this.vett[i].getOffset());
			}
		} catch (java.io.IOException exc) {
			exc.printStackTrace();
		}
	}
	/* BUA
	 * 012345678910
	 * swetternul l
	 * UKXNHCFLNJ S
	 * UKXNHCFLNJ S
	 * s->u->n->l->s
	 */

	private void configure() {
		if(this.crib.length==this.codedcrib.length){
			this.normalConfiguration(this.crib,this.codedcrib,0);
		}else if(this.codedcrib.length > this.crib.length){
			boolean trovato = false;
			for(int i = 0; ! trovato && i < this.codedcrib.length - this.crib.length; ++i){
				char[] newCodedCrib = new char[this.crib.length];
				for(int j = 0; j < newCodedCrib.length; ++j)
					newCodedCrib[j] = this.codedcrib[i+j];
				normalConfiguration(this.crib,newCodedCrib,i);
				trovato = this.vett != null;
			}
		}
	}

	public void normalConfiguration(char[] crib, char[] codedCrib, int index){
		if(notEqualsCharArr(crib,codedCrib))
			if(crib.length==codedCrib.length){
				boolean trovato = false;
				CribTree t = null;
				int i;
				for(i = 0;!trovato && i < crib.length;++i){
					t = new CribTree(crib,codedCrib,i);
					int j = t.getPossibleWays();
					trovato = j != 0;
				}
				if(trovato){
					this.vett = t.getValidLoop();
					this.offset =this.offset + index;
				}
				else
					this.vett = null;
			}
	}

	public void find() {
		for (int j = 0;j < 17576; ++j) {
			char c =(char) (this.vett[0].getChiaro() - 'a' + 'A');
			boolean ancoraOk = true;
			for (int i = 0; i < this.enigmas.length; ++i){
				c = this.enigmas[i].encodeChar((char) (c - 'A' + 'a'));
				ancoraOk = ancoraOk && c == this.vett[i].getCifra();
			}
			if(ancoraOk){
				this.possibleRes.add(numberToAlfaConversion(j-this.offset));
			}
		}
	}

	public String printSequence(){
		String res = "";
		for(int i = 0; i < this.vett.length; ++i)
			res = res + this.vett[i].getChiaro() + "->";
		res = this.offset + ":" +res + (char) (this.vett[this.vett.length-1].getCifra() - 'A' + 'a');
		return res;
	}

	public String[] getResults(){
		String[] res = new String[this.possibleRes.size()];
		for(int i = 0; i < res.length; ++i)
			res[i] =(String) this.possibleRes.get(i);
		return res;
	}

	public int[] getArray(){
		int[] res = new int[this.scambUsed.length];
		for(int i = 0 ; i < res.length; ++i)
			res[i] = this.scambUsed[i];
		return res;
	}

	public static int alfaToNumberConversion(String s) {
		if (s.length() != 3) {
			return -1;
		} else {
			int temp = 0;
			for (int i = s.length() - 1; temp != -1 && i >= 0; --i) {
				if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
					temp = temp * 26 + s.charAt(i) - 'a';
				} else {
					temp = -1;
				}
			}
			return temp;
		}
	}

	public static String numberToAlfaConversion(int i) {
		i = i % 17576;
		if (i < 0) {
			return "";
		} else if (i == 0) {
			return "aaa";
		} else {
			String s = "";
			while (i != 0) {
				s = s + (char) (i % 26 + 'a');
				i = i / 26;
			}
			if (s.length() == 1) {
				s = s + "aa";
			} else if (s.length() == 2) {
				s = s + "a";
			}
			return s;
		}
	}

	public static boolean notEqualsCharArr(char[] source, char[] dest){
		boolean trovato = !(source.length == dest.length);
		for(int i = 0; ! trovato && i < source.length; ++i)
			trovato = source[i] -'a' == dest[i] - 'A';
		return ! trovato;
	}

	public static void main(String argv[]) throws Exception {
		//Bomb b = new Bomb("swetternull", "XOHMKMOEUKXNHCFLNJSQCLL",0);
		int[] ord = {0,1,2};
		Bomb b = new Bomb("swetternull", "XOHMKMOEUKXNHCFLNJSQCLL",0,ord);
		b.find();
		System.out.println(b.printSequence());
	}
}
