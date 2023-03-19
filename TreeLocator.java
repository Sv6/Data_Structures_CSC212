
public class TreeLocator<T> implements Locator<T> {

	private TLNode<T> root;
	private TLNode<T> current;
	
	public TreeLocator() {
		root = null;
	}
	
	@Override
	public int add(T e, Location loc) {
		if(e == null) return 0;
		TLNode<T> tmp = new TLNode<T>(e, loc);
		int count = 0;
		TLNode<T> rootTmp = root;
		boolean added = false;
		if(root == null)
			root = rootTmp = tmp;
	
		else {
			while (!added) {
			if(tmp.loc.x < rootTmp.loc.x && tmp.loc.y <= rootTmp.loc.y) {
				count++;
				if(rootTmp.c1 != null) {
					rootTmp = rootTmp.c1;
				}
				else {
				rootTmp.c1 = tmp;
				current = tmp;
				added = true;
				
				}
			}
			else if(tmp.loc.x <= rootTmp.loc.x && tmp.loc.y > rootTmp.loc.y) {
				count++;
				if(rootTmp.c2 != null) {
					rootTmp = rootTmp.c2;
				}
				else {
				rootTmp.c2 = tmp;
				current = tmp;
				added = true;
				}
			}
			
			else if(tmp.loc.x > rootTmp.loc.x && tmp.loc.y >= rootTmp.loc.y) {
				count++;
				if(rootTmp.c3 != null) {
					rootTmp = rootTmp.c3;
				}
				else {
				rootTmp.c3 = tmp;
				current = tmp;
				added = true;
				}
			}
			
			else if(tmp.loc.x >= rootTmp.loc.x && tmp.loc.y < rootTmp.loc.y) {
				count++;
				if(rootTmp.c4 != null) {
					rootTmp = rootTmp.c4;
				}
				else {
				rootTmp.c4 = tmp;
				current = tmp;
				added = true;
				}
			}
			else if(tmp.loc.x == rootTmp.loc.x && tmp.loc.y == rootTmp.loc.y) {
				count++;
				rootTmp.data.insert(tmp.data.retrieve());
				current = tmp;
				added = true;
			}
			else {
				System.out.println("not printed");
				break;
			
			}
		}
	}
		return count;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		List<T> ll = new LinkedList<T>();
        if (loc == null) 
            return new Pair<List<T>, Integer>(ll, 0);
        TLNode<T> p = root;
        Pair<Boolean, Integer> pp = find(loc);   
        if (!pp.first) {
        	current = p;
        	return new Pair<List<T>, Integer>(ll, pp.second);
        }
        	ll = current.data;
        	current = p;
        	return new Pair<List<T>, Integer>(ll, pp.second);
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		   TLNode<T> p = root;
			if (e == null || loc == null) 
	            return new Pair<Boolean, Integer>(false, 0);
	        
	            TLNode<T> q = root;
	            Pair<Boolean, Integer> pair = find(loc);
	            if (!pair.first) {
	                p = q;
	                return new Pair<Boolean, Integer>(false, pair.second);
	            } 
	   
	                boolean contain = contains(e);
	                if (!contain) {
	                    p = q;
	                    return new Pair<Boolean, Integer>(false, pair.second);
	                } else {
	                    p.data.remove();
	                    p = q;
	                    return new Pair<Boolean, Integer>(true, pair.second);
	                }
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> lp = new LinkedList<Pair<Location, List<T>>>();
		TLNode<T> p = root;
		getAll(lp, p);
		return lp;
	}
	private void getAll(List<Pair<Location, List<T>>> lp, TLNode<T> p) {
		if(p == null) return;
		Pair<Location, List<T>> pp = new Pair<Location, List<T>>(p.loc, p.data);
		lp.insert(pp);

		getAll(lp, p.c1); getAll(lp, p.c2); getAll(lp, p.c3); getAll(lp, p.c4);
	}


	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		   TLNode<T> p = root; 
	        List<Pair<Location, List<T>>> ll = new LinkedList<Pair<Location, List<T>>>(); 
	        int count = recInRange(lowerLeft, upperRight, ll, p, 0);
	        return new Pair<List<Pair<Location, List<T>>>, Integer>(ll, count);
	}
	private int recInRange(Location lowerLeft, Location upperRight, List<Pair<Location, List<T>>> ll, TLNode<T> p, int count) {
		if(p == null) return 0;
		
		if((lowerLeft.x <= p.loc.x && lowerLeft.y <= p.loc.y) && (upperRight.x >= p.loc.x && upperRight.y >= p.loc.y)) {
			count++;
			p.data.findFirst();
				
				ll.insert(new Pair<Location, List<T>>(p.loc,p.data));
				ll.findNext();
				
			}
			if(findCase(1,p,lowerLeft, upperRight))
				count += recInRange(lowerLeft, upperRight, ll, p.c1 , count);
			if(findCase(2,p,lowerLeft, upperRight))
				count += recInRange(lowerLeft, upperRight, ll, p.c2 , count);
			if(findCase(3,p,lowerLeft, upperRight))
				count += recInRange(lowerLeft, upperRight, ll, p.c3 , count);
			if(findCase(3,p,lowerLeft, upperRight))
				count += recInRange(lowerLeft, upperRight, ll, p.c4 , count);
			
			return count;
		}
	private boolean findCase(int c, TLNode<T> p, Location lowerLeft, Location upperRight) {
		switch(c) {
		case 1: return p.c1 != null;
		case 2: return p.c2 != null;
		case 3: return p.c3 != null;
		case 4: return p.c4 != null;
		default: return false;
		}
	}
	
	private boolean contains(Object e) {
		TLNode<T> p = root;
		if(p.data.empty())
		return false;
		
		p.data.findFirst();
		while(!p.data.last()) {
			if(p.data.retrieve().equals(e))
				return true;
			p.data.findNext();
		}
			return p.data.retrieve().equals(e);
	}
	
	

	private Pair<Boolean, Integer> find(Location loc) {
		if(loc == null) 
			return new Pair<Boolean, Integer>(false, 0);
		TLNode<T> p = root;
		int count = 0;
		if(p.data.empty())
			return new Pair<Boolean, Integer>(false, count);
		TLNode<T> q = root;
		if(p != null) {
		while(true) {
			if(p == null)
				break;
			count++;
			q = p;
			if(p.loc.x == loc.x && p.loc.y == loc.y) 
				return new Pair<Boolean, Integer>(true, count);
			else if(findCase(1, p.loc, loc))
				p = p.c1;
			else if(findCase(2, p.loc, loc))
				p = p.c2;
			else if(findCase(3, p.loc, loc))
				p = p.c3;
			else if(findCase(4, p.loc, loc))
				p = p.c4;
			else
				continue; 
		}
		current = q;
		}
		return new Pair<Boolean, Integer>(false, count);			
		}
	private boolean findCase(int c, Location loc1, Location loc2) {
		switch(c) {
		case 1: return (loc2.x < loc1.x && loc2.y <= loc1.y);
		case 2: return (loc2.x <= loc1.x && loc2.y > loc1.y);
		case 3: return (loc2.x > loc1.x && loc2.y >= loc1.y);
		case 4: return (loc2.x >= loc1.x && loc2.y < loc1.y);
		default: return false;
		}
	}

}
