package com.shad.app;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
//		System.out.println(" 001 BULB PSY/POI                    WAT SQUI 004 ");
//		System.out.println(" L99 100% PAR                            100% L99 ");
//		System.out.println("                                                  ");
//		System.out.println(" L99 100% PAR                        BUR 100% L99 ");
//		System.out.println(" 001 BULB PSY/POI                    WAT SQUI 004 ");
//		System.out.println("--------------------------------------------------");
//		System.out.println("1-HIGH JUMP KICK [COM]      2-SMELLING SALTS [NOR]");
//		System.out.println("3-SMELLING SALTS [NOR]      4-HIGH JUMP KICK [COM]");
//		System.out.println("--------------------------------------------------");
		Battlefield b = new Battlefield();
		Pokemon p = new Pokemon();
		p.team = true;
		p.number = 1;
		p.name = "haunter";
		p.type1 = Type.GHOST;
		p.type2 = Type.POISON;
		p.level = 42;
		HPStatistic hp = new HPStatistic(); hp.combatValue = 150;
		p.hp = hp;
		p.remainingHP = 122;
		p.primaryStatusCondition = PrimaryStatusCondition.SLEEP;
		b.pokemons[PokemonIndex.TOPLEFT.getIndex()] = p;
		b.pokemons[PokemonIndex.BOTLEFT.getIndex()] = p;
		p = new Pokemon();
		p.team = false;
		p.number = 111;
		p.name = "abra";
		p.type1 = Type.PSYCHIC;
		p.type2 = Type.NONE;
		p.level = 9;
		hp = new HPStatistic(); hp.combatValue = 115;
		p.hp = hp;
		p.remainingHP = 8;
		p.primaryStatusCondition = PrimaryStatusCondition.BURN;
		b.pokemons[PokemonIndex.TOPRIGHT.getIndex()] = p;
		b.pokemons[PokemonIndex.BOTRIGHT.getIndex()] = p;
		printBattlefield(b);
	}
	
	private static int HALF_WIDTH = 25;
	
	private static String HALF_ROW_LEFT_STUB = "%-" + HALF_WIDTH + "." + HALF_WIDTH + "s";
	
	private static String HALF_ROW_RIGHT_STUB = "%" + HALF_WIDTH + "." + HALF_WIDTH + "s";
	
	private static void printBattlefield(Battlefield b) {
		printScreen(b);
		printDialog();
	}
	
	private static String getPokemonDescri(Pokemon p) {
		if (p == null) {
			return String.format(HALF_ROW_LEFT_STUB, new String());
		}
		Object[] params = new Object[] { 
				p.number, 
				p.name.toUpperCase(), 
				p.type1.getUC3(), 
				(Type.NONE.equals(p.type2)) ? new String() : "/", 
				(Type.NONE.equals(p.type2)) ? new String() : p.type2.getUC3() 
		};
		return p.team ? 
				String.format(HALF_ROW_LEFT_STUB, String.format(" %03d %5.5s %3.3s%1.1s%3.3s", params)) :
				String.format(HALF_ROW_RIGHT_STUB, String.format("%3.3s%1.1s%3.3s %5.5s %03d ", Reverse(params)));
	}
	
	private static String getPokemonStatus(Pokemon p) {
		if (p == null) {
			return String.format(HALF_ROW_LEFT_STUB, new String());
		}
		Object[] params = new Object[] { 
				p.level, 
				(int)((p.remainingHP * 100.0f) / p.hp.getCombatValue()), 
				p.primaryStatusCondition.getUC3()
		};
		return p.team ? 
				String.format(HALF_ROW_LEFT_STUB, String.format(" L%03d %3d%% %3.3s", params)) :
				String.format(HALF_ROW_RIGHT_STUB, String.format("%-3.3s %3d%% L%03d ", Reverse(params)));
	}
	
	private static void printScreen(Battlefield b) {
		StringBuilder sb = new StringBuilder();
		String ls;
		String rs;
		// row 1 " 001 BULB PSY/POI                    WAT SQUI 004 "
		ls = getPokemonDescri(b.pokemons[PokemonIndex.TOPLEFT.getIndex()]);
		rs = getPokemonDescri(b.pokemons[PokemonIndex.TOPRIGHT.getIndex()]);
		sb.append(ls).append(rs).append('\n');
		// row 2 " L99 100% PAR                            100% L99 "
		ls = getPokemonStatus(b.pokemons[PokemonIndex.TOPLEFT.getIndex()]);
		rs = getPokemonStatus(b.pokemons[PokemonIndex.TOPRIGHT.getIndex()]);
		sb.append(ls).append(rs).append('\n');
		// row 3 "                                                  "
		sb.append('\n');
		// row 4 " L99 100% PAR                        BUR 100% L99 "
		ls = getPokemonStatus(b.pokemons[PokemonIndex.BOTLEFT.getIndex()]);
		rs = getPokemonStatus(b.pokemons[PokemonIndex.BOTRIGHT.getIndex()]);
		sb.append(ls).append(rs).append('\n');
		// row 5 " 001 BULB PSY/POI                    WAT SQUI 004 "
		ls = getPokemonDescri(b.pokemons[PokemonIndex.BOTLEFT.getIndex()]);
		rs = getPokemonDescri(b.pokemons[PokemonIndex.BOTRIGHT.getIndex()]);
		System.out.println(sb.append(ls).append(rs));
	}
	
	private static void printDialog() {
		StringBuilder sb = new StringBuilder();
		char[] sep = new char[HALF_WIDTH << 1];
		Arrays.fill(sep, '-');
		sb.append(sep).append('\n');
		System.out.println(sb.append('\n').append('\n').append(sep));
	}
	
	private static Object[] Reverse(Object[] o) {
		for(int i = 0; i < o.length / 2; i++)
		{
		    Object temp = o[i];
		    o[i] = o[o.length - i - 1];
		    o[o.length - i - 1] = temp;
		}
		return o;
	}
}
