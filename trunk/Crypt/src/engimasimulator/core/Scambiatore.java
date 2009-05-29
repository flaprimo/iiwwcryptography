/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engimasimulator.core;

/**
 *
 * @author francescoburato
 */
public class Scambiatore implements Rotore, ClickListener {

	private class ClickListenerList {

		private class ListenerNode {

			public ClickListener key;
			public ListenerNode next;

			public ListenerNode(ClickListener k, ListenerNode n) {
				this.key = k;
				this.next = n;
			}
		}
		private ListenerNode root;

		public ClickListenerList() {
			this.root = null;
		}

		public void addListener(ClickListener l) {
			this.root = new ListenerNode(l, this.root);
		}

		public void fireClickEvent(ClickEvent evt) {
			this.fireClickEvent(evt, this.root);
		}

		private void fireClickEvent(ClickEvent evt, ListenerNode n) {
			if (n != null) {
				n.key.clickArrived(evt);
			}
		}
	}
	private int[] forwardArray;
	private int[] backwardArray;
	private int initialOffset;
	private int actualOffset;
	private final int size = 26;
	private ClickListenerList l;

	public void print() {
		String s = "";
		for (int i = 0; i < this.size; ++i) {
			s = s + this.forwardArray[(i + this.initialOffset + this.actualOffset) % this.size] + ";";
		}
		System.out.println(s.substring(0, s.length() - 1));
		s = "";
		for (int i = 0; i < this.size; ++i) {
			s = s + (this.backwardArray[i] - this.actualOffset - this.initialOffset + 2 * this.size) % this.size + ";";
		}
		System.out.println(s.substring(0, s.length() - 1));
	}

	private void makeBackward() {
		this.backwardArray = new int[forwardArray.length];
		for (int i = 0; i < this.size; ++i) {
			int j = 0;
			for (; j < (this.size) && !(this.forwardArray[j] == i); ++j)
				;
			this.backwardArray[i] = j;
		}
	}

	public Scambiatore() {
		this.initialOffset = 0;
		this.actualOffset = 0;
		this.l = new ClickListenerList();
	}

	public Scambiatore(char[] vett) {
		this();
		if (vett.length == this.size) {
			this.forwardArray = new int[vett.length];
			for (int i = 0; i < vett.length; ++i) {
				this.forwardArray[i] = vett[i] >= 'a' && vett[i] <= 'z' ? vett[i] - 'a' : 0;
			}
			this.makeBackward();
		}
	}

	public Scambiatore(int[] vett) {
		this();
		if (vett.length == this.size) {
			this.forwardArray = new int[vett.length];
			for (int i = 0; i < vett.length; ++i) {
				this.forwardArray[i] = vett[i] >= 0 && vett[i] < this.size ? vett[i] : 0;
			}
			this.makeBackward();
		}
	}

	public Scambiatore(Scambiatore s){
		this();
		this.forwardArray = new int[s.forwardArray.length];
		this.backwardArray = new int[s.backwardArray.length];
		for(int i = 0; i < s.size; ++i){
			this.forwardArray[i] = s.forwardArray[i];
			this.backwardArray[i] = s.backwardArray[i];
		}
	}

	public Scambiatore(String s) {
		this(s.toCharArray());
	//	print();
	}

	public int forwardEncoding(int c) {
		return c >= 0 && c < this.size ? this.forwardArray[(c + this.initialOffset + this.actualOffset) % this.size] : 0;
	}

	public int backwardEncoding(int c) {
		return c >= 0 && c < this.size ? (this.backwardArray[c] - this.actualOffset - this.initialOffset + 2 * this.size) % this.size : 0;
	}

	public void goNext(int how) {
		if(this.actualOffset + how >= 26)
			this.l.fireClickEvent(new ClickEvent());
		this.actualOffset = (this.actualOffset + how) % this.size;
	}

	public void reset() {
		this.actualOffset = 0;
	}

	public void setInitialOffset(int n) {
		this.initialOffset = n >= 0 && n < this.size ? n : 0;
//		this.print();
	}

	public void clickArrived(ClickEvent evt) {
		this.goNext(evt.getOffsets());
	}

	public void addClickListener(ClickListener c) {
		this.l.addListener(c);
	}

	public char getCurrentPosition(){
		return (char) ('A' + (this.initialOffset + this.actualOffset)%26);
	}

}
