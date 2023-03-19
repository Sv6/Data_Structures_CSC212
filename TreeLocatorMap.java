
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {

	private Map<K, Location> b;
	private Locator<K> tL;
	
	
	public TreeLocatorMap() {
		b = new BST<K, Location>();
		tL = new TreeLocator<K>();
	}
	
	@Override
	public Map<K, Location> getMap() {
		return b;
	}

	@Override
	public Locator<K> getLocator() {
		return tL;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair<Boolean, Integer> pp = b.find(k);
		if(!pp.first) {
			b.insert(k,loc);
			int count = tL.add(k, loc);
			
			return new Pair<Boolean, Integer>(true, count);
		}
		return new Pair<Boolean, Integer>(false, pp.second);
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		Pair<Boolean, Integer> pp = b.find(k);
		if(pp.first) {
			tL.remove(k, b.retrieve());
			tL.add(k, loc);
			b.update(loc);
			return new Pair<Boolean, Integer>(true, b.find(k).second);
		}
		return new Pair<Boolean, Integer>(false, b.find(k).second);
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair<Boolean, Integer> pp = b.find(k);
		if(pp.first) 
			return new Pair<Location, Integer>(b.retrieve(), pp.second);
		
		return null;
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		Pair<Boolean, Integer> pp = b.find(k);
		if(pp.first) {
			tL.remove(k, b.retrieve());
			b.remove(k);
			return new Pair<Boolean, Integer>(true, pp.second);
		}
		return new Pair<Boolean, Integer>(false, pp.second);
	}

	@Override
	public List<K> getAll() {
		return b.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		List<K> ll = new LinkedList<K>();
		List<Pair<Location, List<K>>> tmp = tL.inRange(lowerLeft, upperRight).first;
		int count = tL.inRange(lowerLeft, upperRight).second;
		if(tmp.empty()) return new Pair<List<K>, Integer>(ll, count);
		K key = null;
		
		tmp.findFirst();
		while(!tmp.last()) {
			key = tmp.retrieve().second.retrieve();
			ll.insert(key);
			tmp.findNext();
		}
		ll.insert(key);
				return new Pair<List<K>, Integer>(ll, count);
	}

}
