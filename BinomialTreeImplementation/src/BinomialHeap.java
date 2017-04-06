
public class BinomialHeap {
	TreeNode head;

	BinomialHeap() {
		head = null;
	}

	public TreeNode makeBinomialHeap() {
		return head;
	}

	TreeNode binomialHeapMinimum(TreeNode hd) {
		// TreeNode p=hd;
		TreeNode y = null;
		int min = Integer.MAX_VALUE;
		while (hd != null) {
			if (hd.key < min) {
				min = hd.key;
				y = hd;
			}
			hd = hd.sibling;
		}
		return y;
	}

	void binomialLink(TreeNode b, TreeNode a) {
		b.parent = a;
		b.sibling = a.child;
		a.child = b;
		a.degree = a.degree + 1;
	}

	TreeNode binomialHeapUnion(TreeNode h1, TreeNode h2) {
		TreeNode h;
		if (h1 == null)
			return h2;
		else if (h2 == null)
			return h1;
		else
			h = binomialTreeMerge(h1, h2);
		if (h == null)
			return h;
		TreeNode prev_x = null;
		TreeNode x = h;
		TreeNode next_x = x.sibling;
		while (next_x != null) {
			if ((x.degree != next_x.degree) || (next_x.sibling != null && x.degree == next_x.sibling.degree)) {
				prev_x = x;
				x = next_x;
			} else {
				if (x.key <= next_x.key) {
					x.sibling = next_x.sibling;
					binomialLink(next_x, x);
				} else {
					if (prev_x == null) {
						h = next_x;
					} else {
						prev_x.sibling = next_x;
					}
					binomialLink(x, next_x);
					x = next_x;
				}
			}
			next_x = x.sibling;
		}
		return h;
	}

	TreeNode binomialTreeMerge(TreeNode h1, TreeNode h2) {
		TreeNode h;// = new TreeNode(-1);

		if (h1.degree < h2.degree) {
			h = h1;
			h1 = h1.sibling;
		} else if (h1.degree > h2.degree) {
			h = h2;
			h2 = h2.sibling;
		} else if (h1.key < h2.key) {
			h = h1;
			h1 = h1.sibling;
		} else {
			h = h2;
			h2 = h2.sibling;
		}

		TreeNode x = h;
		while (h1 != null && h2 != null) {
			if (h1.degree < h2.degree) {
				h.sibling = h1;
				h1 = h1.sibling;
			} else {
				h.sibling = h2;
				h2 = h2.sibling;
			}
			h = h.sibling;
		}
		if (h1 != null) {
			h.sibling = h1;
			h1 = h1.sibling;
		}
		if (h2 != null) {
			h.sibling = h2;
			h2 = h2.sibling;
		}

		return x;
	}

	TreeNode binomialInsert(TreeNode hd, int k) {
		if (this.head == null) {
			TreeNode t = new TreeNode(k);
			return t;
			// return;
		}
		// BinomialHeap bh=new BinomialHeap();
		TreeNode t = new TreeNode(k);
		// bh.head=t;
		return binomialHeapUnion(hd, t);
		// return h;
	}

	TreeNode binomialExtractMin(TreeNode h) {
		TreeNode min = binomialHeapMinimum(h);
		TreeNode y = h;
		BinomialHeap bh = new BinomialHeap();

		if (min != h) { // locating y which is previous node to min
			while (y.sibling != min) {
				y = y.sibling;
			}
			y.sibling = min.sibling;
		} else {
			h = min.sibling;
		}

		bh.head = reverseLinkedList(min.child);
		return binomialHeapUnion(bh.head, h);
		// return min;
	}

	TreeNode reverseLinkedList(TreeNode a) { // reversing the child list of root
												// node and directing parent
												// property to null
		TreeNode prev, cur, next;
		prev = a;
		cur = a.sibling;
		next = cur.sibling;

		if (prev == null)
			return a;
		else
			prev.parent = null;

		if (cur == null)
			return a;
		else
			cur.parent = null;

		while (next != null) {
			cur.sibling = prev;
			next.parent = null;
			prev = cur;
			cur = next;
			next = next.sibling;
		}
		cur.sibling = prev;
		a.sibling = null;
		return cur;

	}

	void binomialHeapDecreaseKey(TreeNode h, TreeNode x, int key) {
		TreeNode y, z;
		if (x.key < key) {
			System.out.println("Error: Given key should be smaller than exiting key.");
			return;
		}
		x.key = key;
		y = x;
		z = x.parent;
		while (z != null && z.key > y.key) {
			int temp = z.key;
			z.key = y.key;
			y.key = temp;
			temp = z.degree;
			z.degree = y.degree;
			y.degree = temp;
			y = z;
			z = z.parent;
		}
	}

	TreeNode binomialDeleteKey(TreeNode h, TreeNode x) {
		binomialHeapDecreaseKey(h, x, Integer.MIN_VALUE);
		return binomialExtractMin(h);
	}

	void traverseBinomialTree(TreeNode h) {
		TreeNode hd = h;
		while (hd != null) {
			System.out.println(hd.toString());
			if (hd.child != null)
				traverseBinomialTree(hd.child);
			hd = hd.sibling;
		}
	}

	TreeNode searchNode(TreeNode hd, int key) {
		// TreeNode hd = this.head;
		if (hd.key == key) {
			return hd;
		}

		while (hd != null) {
			// System.out.println(h.toString());
			if(hd.key == key){
				return hd;
			}
			if (hd.child != null) {
				TreeNode a = searchNode(hd.child, key);
				if (a != null)
					return a;
			}
			hd = hd.sibling;
		}
		return null;
	}
}
