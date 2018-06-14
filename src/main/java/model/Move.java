package model;

public class Move {
	protected Type type;
	protected MoveCategory category;
	protected int pp;
	protected int power;
	protected int accuracy;
//	protected boolean canTargetSelf;
//	protected boolean canTargetFriend;
//	protected boolean canTargetOpponent;
	protected boolean[] canTarget = new boolean[PokemonIndex.values().length];
	protected int distance;
}
