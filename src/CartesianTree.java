/*
 * An implementation of Cartesian Tree
 * 
 * https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-851-advanced-data-structures-spring-2012/lecture-videos/session-15-static-trees/
 * see 13:01 minutes in the MIT lecture video
 */
public class CartesianTree {

	Node root;

	public CartesianTree(int[] nums) {
		root = null;
		buildTree(nums);
	}

	public void printTree() {
		printTree(root);
		System.out.println();
	}
	
	private void printTree(Node current) {
		if(current==null) {
			return;
		}
		
		printTree(current.left);
		System.out.print(current.key+" ");
		printTree(current.right);
	}
	
	private void buildTree(int[] nums) {
		Node current = null;
		for (int i = 0; i < nums.length; i++) {
			if (root == null) {
				root = new Node(nums[i]);
				current = root;
			} else {
				Node newNode = new Node(nums[i]);
				Node last = null;
				while (current != null && newNode.key < current.key) {
					last = current;
					current = current.parent;
				}

				if (current == null) {
					newNode.left = last;
					last.parent = newNode;
					current = newNode;
					root=newNode;
				} else {
					Node currentRight = current.right;
					newNode.left = currentRight;
					if (currentRight != null) {
						currentRight.parent = newNode;
					}
					current.right = newNode;
					newNode.parent = current;
					current = newNode;
				}

			}
		}
	}

	private class Node {
		int key;
		Node parent;
		Node left, right;

		public Node(int key) {
			this.key = key;
		}
	}

}
