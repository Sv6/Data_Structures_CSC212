
public class BSTNode<T, K> {
	public K key;
	public T data;
	public BSTNode<T,K> left, right;
	
	public BSTNode() {
		key = null;
		data = null;
		left = right = null;
	}
	
	public BSTNode(K k, T val) {
		key = k;
		data = val;
		left = right = null;
	}
	
	public BSTNode(K k, T val, BSTNode<T,K> left, BSTNode<T,K> right) {
		key = k;
		data = val;
		this.left = left;
		this.right = right;
	}
}
