
public class TLNode<T> {
	
	public TLNode<T> c1, c2, c3, c4;
	public List<T> data;
	public Location loc;
	

	
	public TLNode() {
		loc = null;
		data = new LinkedList<T>();
		c1 = c2 = c3 = c4 = null;
		
	}
	
	public TLNode(T e, Location l) {
		data = new LinkedList<T>();
		data.insert(e);
		loc = l;
		c1 = c2 = c3 = c4 = null;
	}
}
