
public class LinkedList<T> implements List<T> {

	private Node<T> head;
	private Node<T> current;
	
	@Override
	public boolean empty() {
		return head == null;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void findFirst() {
		current = head;
	}

	@Override
	public void findNext() {
		if(!empty() && !last())
			current = current.next;
	}

	@Override
	public boolean last() {
		if(empty()) return false;
		return current.next == null;
	}

	@Override
	public T retrieve() {
		if(empty()) return null;
		return current.data;
	}

	@Override
	public void update(T e) {
		if(!empty())
			current.data = e;

	}

	@Override
	public void insert(T e) {
		Node<T> tmp;
		if(empty()) current = head = tmp = new Node<T>(e);
		else {
			tmp = current.next;
			current.next = new Node<T>(e);
			current = current.next;
			current.next = tmp;
		}

	}

	@Override
	public void remove() {
		if(!empty()) {
			if(current == head) head = head.next;
			else {
				Node<T> tmp = head;
				while(tmp != current)
					tmp = tmp.next;
				tmp.next = current.next;
			}
			if(current.next == null)
				findFirst();
			else findNext();
		}

	}

}
