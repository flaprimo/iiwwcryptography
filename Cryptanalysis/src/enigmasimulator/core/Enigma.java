/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enigmasimulator.core;

/**
 *
 * @author francescoburato
 */
public class Enigma {
	protected PannelloPrese panel;
	protected Scambiatore used[];
	protected Scambiatore possible[];
	protected Riflessore rif;
	protected final int size = 26;

	public Enigma() throws java.io.IOException{
		java.io.FileInputStream stream = new java.io.FileInputStream(new java.io.File("files/scambiatori.conf"));
		int num = 0,temp = stream.read();
		while(temp != '\n'){
			num = num * 10 + temp - '0';
			temp = stream.read();
		}
		String[] s = new String[num];
		byte[] b = new byte[this.size];
		for(int j = 0; j < s.length; ++j){
			s[j] = "";
			stream.read(b);
			for(int i = 0; i < b.length; ++i)
				s[j] = s[j] + (char) b[i];
			stream.read();
		}
		this.possible = new Scambiatore[s.length];
		for(int i = 0; i < this.possible.length; ++i)
			this.possible[i] = new Scambiatore(s[i]);
		this.rif = new Riflessore();
		this.used = null;
	}

	public void setPanel(char[] s){
		this.panel = new PannelloPrese(s);
	}

	public void setPanel(String s){
		this.panel = new PannelloPrese(s);
	}

	public char[] getPanel(){
		return this.panel.getPanel();
	}

	protected void setTurningRules(){
		for(int i = 0 ;i < this.used.length -1; ++i)
			this.used[i].addClickListener(this.used[i+1]);
	}

	public void setUsed(int[] ord){
		boolean usable = ord.length <= this.possible.length;
		for(int i = 0; i < ord.length; ++i)
			usable = ord[i] < this.possible.length && ord[i] >= 0;
		if(usable){
			this.used = new Scambiatore[ord.length];
			for(int i = 0; i < ord.length; ++i)
				this.used[i] = new Scambiatore(this.possible[ord[i]]);
			setTurningRules();
		}
	}

	public boolean isUsedSet(){
		return this.used != null;
	}

	public void setOffsets(int[] ord){
		boolean usable = ord.length == this.used.length;
		for(int i = 0; i < ord.length; ++i)
			usable =ord[i] >= 0;
		if(usable){
			for(int i = 0; i < this.used.length; ++i)
				this.used[i].setInitialOffset(ord[i]%this.size);
		}
	}

	public void setOffsets(char[] ord){
		int [] temp = new int[ord.length];
		for(int i = 0; i < ord.length; ++i)
			temp[i] = ord[i] - 'a';
		this.setOffsets(temp);
	}

	public char encodeChar(char c){
		if(c>='a' && c <='z'){
			int num = c - 'a';
			num = this.panel.forwardEncoding(num);
			for(int i = 0; i < this.used.length ;++i)
				num = this.used[i].forwardEncoding(num);
			num = this.rif.forwardEncoding(num);
			for(int i = this.used.length -1 ; i >= 0; --i)
				num = this.used[i].backwardEncoding(num);
			num = this.panel.backwardEncoding(num);
			this.used[0].goNext(1);
			return (char) (num + 'A');
		}else
			return c;
	}

	public char[] getCurrentOffset(){
		char[] res = new char[this.used.length];
		for(int i = 0; i < res.length; ++i)
			res[i] = this.used[i].getCurrentPosition();
		return res;
	}

	public int getPossibleScamb(){
		return this.possible.length;
	}


	public char decodeChar(char c){
		if(c>='A' && c <= 'Z')
			return this.encodeChar((char)(c-'A' + 'a'));
		else
			return c;
	}

	public static String enigmaClearTextFormatter(String s){
		String res ="";
		s = s.toLowerCase();
		for(int i = 0; i < s.length(); ++i){
			char c = s.charAt(i);
			if(c >= 'a' && c <= 'z')
				res = res + c;
			else switch(c){
				case 'ò' :
					res = res + 'o';
					break;
				case 'à' :
					res = res + 'a';
					break;
				case 'è' :
				case 'é' :
					res = res + 'e';
					break;
				case 'ù' :
					res = res + 'u';
					break;
				default :
					res = res + "";
					break;
			}
		}
		return res;
	}

	public static String enigmaCipherTextFormatter(String s){
		s = s.toUpperCase();
		String res = "";
		for(int i = 0; i < s.length(); ++i){
			res =res + s.charAt(i);
			if((i+1)%4==0)
				res = res + " ";
		}
		return res;
	}

	public void reset(){
		for(int i = 0; i< this.used.length; ++i)
			this.used[i].reset();
	}

	public static void main(String argv[]){
		try{
			Enigma e = new Enigma(), e1 = new Enigma();
			int[] scamb ={0,1,2};
			char[] offsets = {'a','a','a'};
			e.setUsed(scamb);
			e.setOffsets(offsets);
			e1.setUsed(scamb);
			e1.setOffsets(offsets);
			String source = enigmaClearTextFormatter("abcdefghilmnopqrstu");
			//System.out.println(source);
			char[] s = source.toCharArray();
			String res = "";
			for(int i = 0; i < s.length; ++i){
				res = res + e.encodeChar(s[i]);
			}
			res = enigmaCipherTextFormatter(res);
			System.out.println(res);
			res = enigmaClearTextFormatter(res).toUpperCase();
			char[] c = res.toCharArray();
			res = "";
			for(int i = 0; i < c.length; ++i){
				res = res +e1.decodeChar(c[i]);
			}
			res = enigmaClearTextFormatter(res);
			System.out.println(res);
		}catch(java.io.IOException e){
			e.printStackTrace();
		}
	}
}
