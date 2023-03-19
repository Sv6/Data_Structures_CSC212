
public class BST<K extends Comparable<K>, T> implements Map<K, T> {

	private BSTNode<T,K> root, current;
	
	@Override
	public boolean empty() {
		return root == null;		
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T retrieve() {
		if(empty()) return null;
		return current.data;
	}

	@Override
	public void update(T e) {
		if(!empty()) current.data = e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		BSTNode<T,K> p = root, q = null;
		int count = 0;
		if(empty()) return new Pair<Boolean, Integer>(false, 0);
		
		while(p != null) {
			q = p;
			if(key.compareTo(p.key) > 0) {
				p = p.right;
				count++;
			}
			else if(key.compareTo(p.key) < 0) {
				p = p.left;
				count++;
			}
			else if(key.compareTo(p.key) == 0){
				current = p;
				count++;
				return new Pair<Boolean, Integer>(true, count);
			}
		}
		current = q;
		return new Pair<Boolean, Integer>(false, count);
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		BSTNode<T,K> p, q = current;
		int count = 0;
	
	if(find(key).first) {
		current = q;
		return new Pair<>(false, count);
	}
	
	p = new BSTNode<T, K>(key, data);
	if(empty()) {
		root = current = p;
		return new Pair<>(false, count);
	}
	
	else {
	int comp = key.compareTo(current.key);
	if(comp < 0) {
		current.left = p;
		count++;
	}
	else {
		current.right = p;
		count++;
	}
	
	current = p;
	return new Pair<Boolean, Integer>(true, count);
	}
	}

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		int count = 0;
		if(empty()) return new Pair<>(false, count);
		
		BSTNode<T, K> p = root;
		BSTNode<T, K> q = null;
		while(p != null) {
			if(key.compareTo(p.key) < 0) {
				count++;
				q = p;
				p = p.left;
			}
			else if(key.compareTo(p.key) > 0) {
				count++;
				q = p;
				p = p.right;
			}
			else if(key.compareTo(p.key) == 0){
				if((p.left != null) && p.right != null) {
					BSTNode<T, K> min = p.right;
					q = p;
					while(min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					p = min;
				}
				if(p.left != null)
					p = p.left;
				else if(p.right != null) //
					p = p.right;
				if(q == null)
					root = p;
				else {
					if(key.compareTo(p.key) < 0) {
						q.left = p;
						count++;
					}
					else if(key.compareTo(p.key) > 0) {
						q.right = p;
						count++;
					}
				}
				current = root;
				return new Pair<Boolean, Integer>(true, count);
			}
		}
		return new Pair<>(false, count);
	}

	@Override
	public List<K> getAll() {
		List<K> list = new LinkedList<K>();
		if(empty()) return list;
		BSTNode<T, K> p = root;
		getAll(p, list);
		
		return list;
		
	}
	
	private void getAll(BSTNode<T, K> p, List<K> list) {
		if (p == null) return;
		
		getAll(p.left, list);
		list.insert(p.key);
		getAll(p.right, list);
	}
	

}
