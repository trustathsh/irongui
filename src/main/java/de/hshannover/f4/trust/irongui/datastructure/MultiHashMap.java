package de.hshannover.f4.trust.irongui.datastructure;




import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MultiHashMap<K, V> extends MultiMap<K, V> {

	HashMap<K, ArrayList<V>> mMap = new HashMap<K, ArrayList<V>>();
	
	@Override
	public
	void put(K key, V value) {
		if(key == null)
			return;		
		ArrayList<V> list = null;
		if(!mMap.containsKey(key)){			
			if(value != null){	
				list = new ArrayList<V>();
				list.add(value);		
			}			
			mMap.put(key, list);			
		}
		else{
			list = mMap.get(key);
			if(list == null){
				list = new ArrayList<V>();	
				list.add(value);
				mMap.put(key, list);
			}
			else{
				if(value != null && !list.contains(value)){					
					list.add(value);					
				}
			}			
			
		}
	}

	@Override
	public void clear(){
		mMap.clear();
	}
	
	@Override
	public
	ArrayList<V> get(K key) {
		return mMap.get(key);
	}

	@Override
	public
	Set<K> keys() {
		return mMap.keySet();
	}

	@Override
	public
	Collection<ArrayList<V>> values() {
		return mMap.values();
	}

	@Override
	public
	ArrayList<V> remove(K key) {
		return mMap.remove(key);
	}

	@Override
	public
	boolean isEmpty() {
		return mMap.isEmpty();
	}

	@Override
	public
	boolean hasValues(K key) {
		ArrayList<V> values = mMap.get(key);
		if(values == null || values.isEmpty()){
			return false;
		}
		else return true;
	}

	@Override
	public
	boolean hasKey(K key) {
		return mMap.containsKey(key);
	}

	@Override
	public
	K getKey(K key) {		
		Set<K> set = mMap.keySet();
		Iterator<K> keys = set.iterator();
		while(keys.hasNext()){
			K k = keys.next();
			if(key.equals(k)){
				return k;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer();
		Set<K> keys = keys();
		for(K key : keys){
			buf.append("\r\n["+key.getClass().getSimpleName()+"] "+key.toString());
			ArrayList<V> values = mMap.get(key);
			if(values != null){
				for(V v : values){
					buf.append("\r\n |--["+v.getClass().getSimpleName()+"] "+v.toString()+"\r\n");
				}
			}
		}		
		return buf.toString();
	}

	@Override
	public
	MultiMap<K, V> deepCopy() {		
		return null;
	}
}
