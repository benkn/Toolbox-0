package ben.kn.toolbox.util;

import java.util.ArrayList;
import java.util.List;

public class TreeTraverser {

	public List<Node> findLeafs(Node node) {
		List<Node> nodes = new ArrayList<Node>();
		if ( node.left != null ) {
			nodes.addAll(findLeafs(node.left));
		} else if ( node.right != null ) {
			nodes.addAll(findLeafs(node.right));
		} else {
			nodes.add(node);
		}

		return nodes;
	}

	public class Node {
		public String value;
		public Node left;
		public Node right;
	}
}
