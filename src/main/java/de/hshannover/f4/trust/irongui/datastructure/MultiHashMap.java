/*
 * #%L
 * =====================================================
 *   _____                _     ____  _   _       _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \| | | | ___ | | | |
 *    | | | '__| | | / __| __|/ / _` | |_| |/ __|| |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _  |\__ \|  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_| |_||___/|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Hochschule Hannover
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.f4.hs-hannover.de/
 * 
 * This file is part of irongui, version 0.4.6,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2014 Trust@HsH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package de.hshannover.f4.trust.irongui.datastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MultiHashMap<K, V> extends MultiMap<K, V> {

	HashMap<K, ArrayList<V>> mMap = new HashMap<K, ArrayList<V>>();

	@Override
	public void put(K key, V value) {
		if (key == null)
			return;
		ArrayList<V> list = null;
		if (!mMap.containsKey(key)) {
			if (value != null) {
				list = new ArrayList<V>();
				list.add(value);
			}
			mMap.put(key, list);
		} else {
			list = mMap.get(key);
			if (list == null) {
				list = new ArrayList<V>();
				list.add(value);
				mMap.put(key, list);
			} else {
				if (value != null && !list.contains(value)) {
					list.add(value);
				}
			}

		}
	}

	@Override
	public void clear() {
		mMap.clear();
	}

	@Override
	public ArrayList<V> get(K key) {
		return mMap.get(key);
	}

	@Override
	public Set<K> keys() {
		return mMap.keySet();
	}

	@Override
	public Collection<ArrayList<V>> values() {
		return mMap.values();
	}

	@Override
	public ArrayList<V> remove(K key) {
		return mMap.remove(key);
	}

	@Override
	public boolean isEmpty() {
		return mMap.isEmpty();
	}

	@Override
	public boolean hasValues(K key) {
		ArrayList<V> values = mMap.get(key);
		if (values == null || values.isEmpty()) {
			return false;
		} else
			return true;
	}

	@Override
	public boolean hasKey(K key) {
		return mMap.containsKey(key);
	}

	@Override
	public K getKey(K key) {
		Set<K> set = mMap.keySet();
		Iterator<K> keys = set.iterator();
		while (keys.hasNext()) {
			K k = keys.next();
			if (key.equals(k)) {
				return k;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		Set<K> keys = keys();
		for (K key : keys) {
			buf.append("\r\n[" + key.getClass().getSimpleName() + "] "
					+ key.toString());
			ArrayList<V> values = mMap.get(key);
			if (values != null) {
				for (V v : values) {
					buf.append("\r\n |--[" + v.getClass().getSimpleName()
							+ "] " + v.toString() + "\r\n");
				}
			}
		}
		return buf.toString();
	}

	@Override
	public MultiMap<K, V> deepCopy() {
		return null;
	}
}
