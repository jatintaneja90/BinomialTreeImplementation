
public class TreeNode {
	int key;
	int degree;
	TreeNode parent;
	TreeNode child;
	TreeNode sibling;

	TreeNode(int key) {
		this.key = key;
		degree = 0;
		parent = null;
		child = null;
		sibling = null;
	}

	public String toString() {

		int p = parent == null ? -1 : parent.key;
		int c = child == null ? -1 : child.key;
		int s = sibling == null ? -1 : sibling.key;

		return "Key is " + this.key + " and child of " + p + " and parent of " + c + "and its sibling is" + s
				+ " and degree is " + degree;
	}
}
