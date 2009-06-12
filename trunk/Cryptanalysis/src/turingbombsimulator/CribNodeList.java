/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package turingbombsimulator;

/**
 *
 * @author francescoburato
 */
public class CribNodeList {

	private class CribNodeListNode {

		public CribNode key;
		public CribNodeListNode next;

		public CribNodeListNode(CribNode key, CribNodeListNode next) {
			this.key = key;
			this.next = next;
		}

		@Override
		public String toString() {
			return key.toString();
		}
	}
	private CribNodeListNode root;

	public CribNodeList() {
		this.root = null;
	}

	public CribNodeList(CribNodeList copy) {
		if (copy == null || copy.root == null) {
			this.root = null;
		} else {
			this.root = null;
			for (CribNodeListNode i = copy.root; i != null; i = i.next) {
				this.addNode(new CribNode(i.key, false));
			}
		}
	}

	public void addNode(CribNode c) {
		if (this.root == null) {
			this.root = new CribNodeListNode(c, null);
		} else {
			CribNodeListNode temp = this.root;
			for (; temp.next != null; temp = temp.next);
			temp.next = new CribNodeListNode(c, null);
		}
	}

	public boolean isClosedLoop() {
		return this.isClosedLoop(this.root);
	}

	private boolean isClosedLoop(CribNodeListNode c) {
		if (c == null) {
			return false;
		} else {
			return c.key.getEnd() || this.isClosedLoop(c.next);
		}
	}

	@Override
	public String toString() {
		return toString(this.root);
	}

	private String toString(CribNodeListNode c) {
		String res = "";
		if (c != null) {
			for (CribNodeListNode i = c; c != null; c = c.next) {
				res = res + c + "\n";
			}
		}
		return res;
	}

	public static int getPossibleWays(CribNode n) {
		if (n != null) {
			if (n.getSons() == null) {
				if (n.getEnd()) {
					return 1;
				} else {
					return 0;
				}
			} else {
				int i = 0;
				for (CribNodeListNode j = n.getSons().root; j != null; j = j.next) {
					i = i + getPossibleWays(j.key);
				}
				return i;
			}
		} else {
			return 0;
		}
	}

	public CribNode[] toCribNodeArray() {
		int i = 0;
		for (CribNodeListNode t = this.root; t != null; t = t.next) {
			++i;
		}
		CribNode[] vett = new CribNode[i];
		i = 0;
		for (CribNodeListNode t = this.root; t != null; t = t.next, ++i) {
			vett[i] = t.key;
		}
		return vett;
	}

	public static CribNodeList getValidLoop(CribNode c) {
		if (c != null) {
			CribNodeList l = new CribNodeList();
			CribNode k = new CribNode(c, false);
			l.addNode(k);
			CribNodeList temp = c.getSons();
			for (CribNodeListNode j = temp.root; j != null;) {
				if (j.key.getEnd()) {
					k = new CribNode(j.key, false);
					l.addNode(k);
					j = j.key.getSons() == null ? null : j.key.getSons().root;
				} else {
					j = j.next;
				}
			}
			return l;
		} else {
			return null;
		}
	}
}
