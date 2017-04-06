import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// BinomialHeap bh = new BinomialHeap();
		// bh.binomialInsert(37);
		int k, j, i;
		TreeNode t;
		String s;
		Scanner scan;
		BinomialHeap bh = null;
		boolean exit = false;
		do {
			// System.out.println("1. To read data from a file.");
			System.out.println("1. To make heap");
			System.out.println("2. To find the min value");
			System.out.println("3. To insert a node");
			System.out.println("4. To extract min value");
			System.out.println("5. To return current head.");
			System.out.println("6. To decrease a key");
			System.out.println("7. To delete a node from tree.");
			System.out.println("8. To traverse binomial tree.");
			System.out.println("9. To search a key in binomial tree");
			System.out.println("10. To exit.");
			System.out.println("Please enter your choice : ");
			scan = new Scanner(System.in);
			i = scan.nextInt();
			switch (i) {
			case 1:
				System.out.println("Creating a new binomial tree");
				bh = new BinomialHeap();
				// s=scan.next();
				// fo = new FileOperations();
				// ar=fo.readFile(s);
				// for(Integer m:ar){
				// k=m;
				// rbt.insert(rbt.root, k);
				// }
				break;
			case 2:
				if (bh == null || bh.head == null) {
					System.out.println("Binomial tree is empty");
					break;
				}
				System.out.println("Min value in current state of binomial tree is : ");
				t = bh.binomialHeapMinimum(bh.head);
				System.out.println(t.key);
				break;
			case 3:
				System.out.println("Provide the key you want to insert :");
				k = scan.nextInt();
				bh.head = bh.binomialInsert(bh.head, k);
				break;
			case 4:
				if (bh == null || bh.head == null) {
					System.out.println("Binomial tree is empty");
					break;
				}
				System.out.println("Extracting min : ");
				t = bh.binomialHeapMinimum(bh.head);
				bh.head = bh.binomialExtractMin(bh.head);
				System.out.println(t.key);
				break;
			case 5:
				System.out.println(bh.head.toString());
				break;
			case 6:
				if (bh == null || bh.head == null) {
					System.out.println("Binomial tree is empty");
					break;
				}
				System.out.println("Provide the key you want to change : ");
				int old_key = scan.nextInt();
				t = bh.searchNode(bh.head, old_key);
				if (t == null) {
					System.out.println("Given key is not present in current binomial tree");
					break;
				}
				System.out.println("Provide new key value : ");
				int new_key = scan.nextInt();
				bh.binomialHeapDecreaseKey(bh.head, t, new_key);
				break;

			/// need to start from here
			case 7:
				if (bh == null || bh.head == null) {
					System.out.println("Binomial tree is empty");
					break;
				}
				System.out.println("Provide the key you want to delete : ");
				int key_delete = scan.nextInt();
				t = bh.searchNode(bh.head, key_delete);
				if (t == null) {
					System.out.println("Given key is not present in current binomial tree");
					break;
				}
				bh.binomialDeleteKey(bh.head, t);

			case 8:
				if (bh == null || bh.head == null) {
					System.out.println("Binomial tree is empty");
					break;
				}
				bh.traverseBinomialTree(bh.head);
				break;
			case 9:
				System.out.println("Enter the key you want to search :");
				k = scan.nextInt();
				TreeNode x = bh.searchNode(bh.head, k);
				if (x != null) {
					System.out.println(x.toString());
				}
				break;
			case 10:
				exit = true;
			default:
				exit = true;
			}

			System.out.println();
			System.out.println();
			System.out.println();
		} while (!exit);
		System.exit(0);
	}
}
