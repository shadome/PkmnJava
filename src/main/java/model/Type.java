package model;

public enum Type {
	NONE,
	BUG,
	DARK,
	DRAGON,
	ELECTRIC,
	FAIRY,
	FIGHT,
	FIRE,
	FLYING,
	GHOST,
	GRASS,
	GROUND,
	ICE,
	NORMAL,
	POISON,
	PSYCHIC,
	ROCK,
	STEEL,
	WATER;
	
	public String getUC3() {
		return this.equals(NONE) ? new String() : this.toString().substring(0, 3).toUpperCase();
	}
}
