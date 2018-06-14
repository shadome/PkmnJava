import java.util.Arrays;

import model.Battlefield;
import model.HPStatistic;
import model.Pokemon;
import model.PokemonIndex;
import model.PrimaryStatusCondition;
import model.Type;

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
		p.setTeam(true);
		p.setNumber(1);
		p.setName("haunter");
		p.setType1(Type.GHOST);
		p.setType2(Type.POISON);
		p.setLevel(42);
		HPStatistic hp = new HPStatistic(); hp.setCombatValue(150);
		p.setHp(hp);
		p.setRemainingHP(122);
		p.setPrimaryStatusCondition(PrimaryStatusCondition.SLEEP);
		b.getPokemons()[PokemonIndex.TOPLEFT.getIndex()] = p;
		b.getPokemons()[PokemonIndex.BOTLEFT.getIndex()] = p;
		p = new Pokemon();
		p.setTeam(false);
		p.setNumber(111);
		p.setName("abra");
		p.setType1(Type.PSYCHIC);
		p.setType2(Type.NONE);
		p.setLevel(9);
		hp = new HPStatistic(); hp.setCombatValue(115);
		p.setHp(hp);
		p.setRemainingHP(8);
		p.setPrimaryStatusCondition(PrimaryStatusCondition.BURN);
		b.getPokemons()[PokemonIndex.TOPRIGHT.getIndex()] = p;
		b.getPokemons()[PokemonIndex.BOTRIGHT.getIndex()] = p;
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
				p.getNumber(), 
				p.getName().toUpperCase(), 
				p.getType1().getUC3(), 
				(Type.NONE.equals(p.getType2())) ? new String() : "/", 
				(Type.NONE.equals(p.getType2())) ? new String() : p.getType2().getUC3() 
		};
		return p.isTeam() ? 
				String.format(HALF_ROW_LEFT_STUB, String.format(" %03d %5.5s %3.3s%1.1s%3.3s", params)) :
				String.format(HALF_ROW_RIGHT_STUB, String.format("%3.3s%1.1s%3.3s %5.5s %03d ", Reverse(params)));
	}
	
	private static String getPokemonStatus(Pokemon p) {
		if (p == null) {
			return String.format(HALF_ROW_LEFT_STUB, new String());
		}
		Object[] params = new Object[] { 
				p.getLevel(), 
				(int)((p.getRemainingHP() * 100.0f) / p.getHp().getCombatValue()), 
				p.getPrimaryStatusCondition().getUC3()
		};
		return p.isTeam() ? 
				String.format(HALF_ROW_LEFT_STUB, String.format(" L%03d %3d%% %3.3s", params)) :
				String.format(HALF_ROW_RIGHT_STUB, String.format("%-3.3s %3d%% L%03d ", Reverse(params)));
	}
	
	private static void printScreen(Battlefield b) {
		StringBuilder sb = new StringBuilder();
		String ls;
		String rs;
		// row 1 " 001 BULB PSY/POI                    WAT SQUI 004 "
		ls = getPokemonDescri(b.getPokemons()[PokemonIndex.TOPLEFT.getIndex()]);
		rs = getPokemonDescri(b.getPokemons()[PokemonIndex.TOPRIGHT.getIndex()]);
		sb.append(ls).append(rs).append('\n');
		// row 2 " L99 100% PAR                            100% L99 "
		ls = getPokemonStatus(b.getPokemons()[PokemonIndex.TOPLEFT.getIndex()]);
		rs = getPokemonStatus(b.getPokemons()[PokemonIndex.TOPRIGHT.getIndex()]);
		sb.append(ls).append(rs).append('\n');
		// row 3 "                                                  "
		sb.append('\n');
		// row 4 " L99 100% PAR                        BUR 100% L99 "
		ls = getPokemonStatus(b.getPokemons()[PokemonIndex.BOTLEFT.getIndex()]);
		rs = getPokemonStatus(b.getPokemons()[PokemonIndex.BOTRIGHT.getIndex()]);
		sb.append(ls).append(rs).append('\n');
		// row 5 " 001 BULB PSY/POI                    WAT SQUI 004 "
		ls = getPokemonDescri(b.getPokemons()[PokemonIndex.BOTLEFT.getIndex()]);
		rs = getPokemonDescri(b.getPokemons()[PokemonIndex.BOTRIGHT.getIndex()]);
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
