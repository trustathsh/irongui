package de.hshannover.f4.trust.irongui.datastructure;




import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Maps one key to multiple values
 *
 *
 * @author tr
 *
 * @param <E>
 * @param <V>
 */
public abstract class MultiMap<K, V> {

	public abstract void put(K key, V value);
	public abstract ArrayList<V> get(K key);
	public abstract Set<K> keys();
	public abstract boolean hasKey(K key);
	public abstract K getKey(K key);
	public abstract Collection<ArrayList<V>> values();
	public abstract ArrayList<V> remove(K key);
	public abstract boolean isEmpty();
	public abstract boolean hasValues(K key);
	public abstract MultiMap<K, V> deepCopy();
	public abstract void clear();	
}
