package model;

import java.util.Arrays;
import java.util.List;

import database.Tables;

public class Battlefield {
	
	protected Weather weather;
	
	private Pokemon[] pokemons = new Pokemon[PokemonIndex.values().length];
	
	public void useMove(Pokemon source, MoveIndex moveIndex, List<Pokemon> targets) {
		Move move = source.getMove(moveIndex);
		if (MoveCategory.PHYSICAL.equals(move.category) || MoveCategory.SPECIAL.equals(move.category)) {
			double modifiers = 1 - ((int)Math.random() * 16) / (double)100; // random formula 
			modifiers *= (Math.random() < source.getBaseSpeed() / 512) ? 1.5 : 1; // critical strike formula
			modifiers *= Tables.INSTANCE.WEATHER.getModifier(weather, move.type); // weather formula
			modifiers *= (targets.stream().filter(x -> !x.isTeam()).count() > 1) ? 0.75 : 1; // targets (if > 1, 0,75)
			modifiers *= (move.type.equals(source.getType1()) || move.type.equals(source.getType2())) ? 1.5 : 1; // STAB
			modifiers *= (MoveCategory.PHYSICAL.equals(move.category) && PrimaryStatusCondition.BURN.equals(source.getPrimaryStatusCondition())) ? 0.5 : 1;// burn (todo ability guts)
			int attack = (MoveCategory.PHYSICAL.equals(move.category)) ? source.attack.getCombatValue() : source.spattack.getCombatValue();
			for (Pokemon target : targets) {
				modifiers *= Tables.INSTANCE.TYPES.getModifier(move.type, target.getType1()); // type
				modifiers *= Tables.INSTANCE.TYPES.getModifier(move.type, target.getType2()); // type
				int defense = (MoveCategory.PHYSICAL.equals(move.category)) ? target.defense.getCombatValue() : target.spdefense.getCombatValue();
				int damage = (int)((((2/5 * source.getLevel()) + 2) * move.power * attack / defense) / 50 + 2 * modifiers); // formula
				target.alterHP(damage);
			}
			// todo effects
		} 
	}
	
	@Override
	public String toString() {
		int halfwidth = 25;
		StringBuilder sb = new StringBuilder();
		String ls;
		String rs;
		Pokemon l;
		Pokemon r;
		// row 1 " 001 BULB PSY/POI                    WAT SQUI 004 "
		l = this.getPokemons()[PokemonIndex.TOPLEFT.getIndex()];
		r = this.getPokemons()[PokemonIndex.TOPRIGHT.getIndex()];
		ls = String.format("%-" + halfwidth + "." + halfwidth + "s", (l == null) ? new String() : String.format(" %03d %5.5s %3.3s%1.1s%3.3s", l.getNumber(), l.getName().toUpperCase(), l.getType1().getUC3(), (Type.NONE.equals(l.getType2())) ? new String() : "/", (Type.NONE.equals(l.getType2())) ? new String() : l.getType2().getUC3()));
		rs = String.format("%" + halfwidth + "." + halfwidth + "s", (r == null) ? new String() : String.format("%3.3s%1.1s%3.3s %5.5s %03d ", (Type.NONE.equals(r.getType2())) ? new String() : r.getType2().getUC3(), (Type.NONE.equals(r.getType2())) ? new String() : '/', r.getType1().getUC3(), r.getName().toUpperCase(), r.getNumber()));
		sb.append(ls).append(rs).append('\n');
		// row 2 " L99 100% PAR                            100% L99 "
		ls = String.format("%-" + halfwidth + "." + halfwidth + "s", (l == null) ? new String() : String.format(" L%03d %3d%% %3.3s", l.getLevel(), (int)((l.getRemainingHP() * 100.0f) / l.getHp().getCombatValue()), l.getPrimaryStatusCondition().getUC3()));
		rs = String.format("%" + halfwidth + "." + halfwidth + "s", (r == null) ? new String() : String.format("%-3.3s %3d%% L%03d ", r.getPrimaryStatusCondition().getUC3(), (int)((r.getRemainingHP() * 100.0f) / r.getHp().getCombatValue()), r.getLevel()));
		sb.append(ls).append(rs).append('\n');
		// row 3 "                                                  "
		sb.append('\n');
		// row 4 " L99 100% PAR                        BUR 100% L99 "
		l = this.getPokemons()[PokemonIndex.BOTLEFT.getIndex()];
		r = this.getPokemons()[PokemonIndex.BOTRIGHT.getIndex()];
		ls = String.format("%-" + halfwidth + "." + halfwidth + "s", (l == null) ? new String() : String.format(" L%03d %3d%% %3.3s", l.getLevel(), (int)((l.getRemainingHP() * 100.0f) / l.getHp().getCombatValue()), l.getPrimaryStatusCondition().getUC3()));
		rs = String.format("%" + halfwidth + "." + halfwidth + "s", (r == null) ? new String() : String.format("%-3.3s %3d%% L%03d ", r.getPrimaryStatusCondition().getUC3(), (int)((r.getRemainingHP() * 100.0f) / r.getHp().getCombatValue()), r.getLevel()));
		sb.append(ls).append(rs).append('\n');
		// row 5 " 001 BULB PSY/POI                    WAT SQUI 004 "
		ls = String.format("%-" + halfwidth + "." + halfwidth + "s", (l == null) ? new String() : String.format(" %03d %5.5s %3.3s%1.1s%3.3s", l.getNumber(), l.getName().toUpperCase(), l.getType1().getUC3(), (Type.NONE.equals(l.getType2())) ? new String() : "/", (Type.NONE.equals(l.getType2())) ? new String() : l.getType2().getUC3()));
		rs = String.format("%" + halfwidth + "." + halfwidth + "s", (r == null) ? new String() : String.format("%3.3s%1.1s%3.3s %5.5s %03d ", (Type.NONE.equals(r.getType2())) ? new String() : r.getType2().getUC3(), (Type.NONE.equals(r.getType2())) ? new String() : '/', r.getType1().getUC3(), r.getName().toUpperCase(), r.getNumber()));
		sb.append(ls).append(rs).append('\n');
		// row 6 "--------------------------------------------------"
		char[] sep = new char[halfwidth << 1];
		Arrays.fill(sep, '-');
		sb.append(sep).append('\n');
		// row 7 "1-HIGH JUMP KICK [COM]      2-SMELLING SALTS [NOR]"
		String.format("", new Object[] {});
//		ls = String.format("%-" + halfwidth + "." + halfwidth + "s", (l == null || l.moves[MoveIndex.TOPLEFT.getIndex()] == null) ? new String() : 
		// row 8 "3-SMELLING SALTS [NOR]      4-HIGH JUMP KICK [COM]"
		// row 9 "--------------------------------------------------"
		sb.append(sep).append('\n');
		System.out.println("1-HIGH JUMP KICK [COM]      2-SMELLING SALTS [NOR]");
		System.out.println("3-SMELLING SALTS [NOR]      4-HIGH JUMP KICK [COM]");
		System.out.println("--------------------------------------------------");
		
		return sb.toString();
	}

	public Pokemon[] getPokemons() {
		return pokemons;
	}

	public void setPokemons(Pokemon[] pokemons) {
		this.pokemons = pokemons;
	}
}
