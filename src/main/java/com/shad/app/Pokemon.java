
public class Pokemon {
	protected int number;
	protected String name;
	protected int level;
	protected Type type1;
	protected Type type2;
	
	protected Move[] moves = new Move[MoveIndex.values().length];
	
	protected HPStatistic hp;
	protected Statistic attack;
	protected Statistic defense;
	protected Statistic spattack;
	protected Statistic spdefense;
	protected Statistic speed;
	
	protected boolean team; // combat
	
	protected int evasion; // combat encapsulate
	protected int accuracy; // combat encapsulate
	protected PrimaryStatusCondition primaryStatusCondition;// combat

	protected int remainingHP;
	
	public void alterHP(int delta) { // combat
		this.remainingHP += delta;
		if (this.remainingHP < 0) this.remainingHP = 0;
		if (this.remainingHP > hp.combatValue) this.remainingHP = hp.combatValue;
	}
	
	public Move getMove(MoveIndex moveIndex) {
		assert(this.moves[moveIndex.getIndex()] != null);
		return this.moves[moveIndex.getIndex()];
	}
	
	public int getBaseSpeed() {
		return speed.base;
	}
}
