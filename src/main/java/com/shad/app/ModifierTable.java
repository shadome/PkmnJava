import java.util.HashMap;
import java.util.Map;

public final class ModifierTable<TKey1, TKey2> {
	private Map<TKey1, Map<TKey2, Double>> map;
	
	public double getModifier(TKey1 key1, TKey2 key2) {
		Map<TKey2, Double> map = this.map.get(key1);
		return (map == null) ? 1 : (!map.containsKey(key2)) ? 1 : map.get(key2).doubleValue();
	}
	
	public void register(TKey1 key1, TKey2 key2, double value) {
		if (this.map == null)
			this.map = new HashMap<TKey1, Map<TKey2, Double>>();
		if (!this.map.containsKey(key1))
			this.map.put(key1, new HashMap<TKey2, Double>());
		this.map.get(key1).put(key2, new Double(value));
	}
}
