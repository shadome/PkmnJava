
public class HPStatistic extends Statistic {
	@Override
	public int getDisplayValue(int level) { // formula (passer lazy)
		assert(level < 101);
		return ((int) ((2 * base + iv + ((int) (ev / 4))) * level / 100)) + level + 10;
	}
}
