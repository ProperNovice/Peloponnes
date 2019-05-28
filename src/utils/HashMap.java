package utils;

import java.util.Iterator;
import java.util.Map;

public class HashMap<K, V> implements Iterable<K> {

	private java.util.HashMap<K, V> hashMap = new java.util.HashMap<>();

	public HashMap() {
		this.hashMap = new java.util.HashMap<>();
	}

	public V get(K key) {
		return this.hashMap.get(key);
	}

	public V put(K key, V value) {
		return this.hashMap.put(key, value);
	}

	@Override
	public Iterator<K> iterator() {

		ArrayList<K> keys = new ArrayList<>();

		for (Map.Entry<K, V> entry : this.hashMap.entrySet())
			keys.addLast(entry.getKey());

		return keys.iterator();

	}

	public boolean containsKey(K key) {
		return this.hashMap.containsKey(key);
	}

	public boolean containsValue(V value) {
		return this.hashMap.containsValue(value);
	}

	public void print() {

		Logger.INSTANCE.log("/*");

		for (Map.Entry<K, V> entry : this.hashMap.entrySet())
			Logger.INSTANCE.log(entry.getKey() + " - " + entry.getValue());

		Logger.INSTANCE.logNewLine("*/");

	}

	@Override
	public HashMap<K, V> clone() {

		HashMap<K, V> hashMapClone = new HashMap<K, V>();

		for (K key : this)
			hashMapClone.put(key, this.get(key));

		return hashMapClone;

	}

}
