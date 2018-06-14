package model;

public enum PrimaryStatusCondition {
	NONE,
	BURN,
	FREEZE,
	PARALYSIS,
	POISON,
	SLEEP,
	TOXIC;
	
	public String getUC3() {
		return this.equals(NONE) ? new String() : this.toString().substring(0, 3).toUpperCase();
	}
}
