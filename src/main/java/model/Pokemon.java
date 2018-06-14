package model;

public class Pokemon {
	private int number;
	private String name;
	private int level;
	private Type type1;
	private Type type2;
	
	protected Move[] moves = new Move[MoveIndex.values().length];
	
	private HPStatistic hp;
	protected Statistic attack;
	protected Statistic defense;
	protected Statistic spattack;
	protected Statistic spdefense;
	protected Statistic speed;
	
	private boolean team; // combat
	
	protected int evasion; // combat encapsulate
	protected int accuracy; // combat encapsulate
	private PrimaryStatusCondition primaryStatusCondition;// combat

	private int remainingHP;
	
	public void alterHP(int delta) { // combat
		this.setRemainingHP(this.getRemainingHP() + delta);
		if (this.getRemainingHP() < 0) this.setRemainingHP(0);
		if (this.getRemainingHP() > getHp().getCombatValue()) this.setRemainingHP(getHp().getCombatValue());
	}
	
	public Move getMove(MoveIndex moveIndex) {
		assert(this.moves[moveIndex.getIndex()] != null);
		return this.moves[moveIndex.getIndex()];
	}
	
	public int getBaseSpeed() {
		return speed.base;
	}

	public PrimaryStatusCondition getPrimaryStatusCondition() {
		return primaryStatusCondition;
	}

	public void setPrimaryStatusCondition(PrimaryStatusCondition primaryStatusCondition) {
		this.primaryStatusCondition = primaryStatusCondition;
	}

	public int getRemainingHP() {
		return remainingHP;
	}

	public void setRemainingHP(int remainingHP) {
		this.remainingHP = remainingHP;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isTeam() {
		return team;
	}

	public void setTeam(boolean team) {
		this.team = team;
	}

	public Type getType2() {
		return type2;
	}

	public void setType2(Type type2) {
		this.type2 = type2;
	}

	public Type getType1() {
		return type1;
	}

	public void setType1(Type type1) {
		this.type1 = type1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public HPStatistic getHp() {
		return hp;
	}

	public void setHp(HPStatistic hp) {
		this.hp = hp;
	}
}
