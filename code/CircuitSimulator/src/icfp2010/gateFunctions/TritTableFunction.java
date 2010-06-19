package icfp2010.gateFunctions;

import icfp2010.Trit;

public class TritTableFunction implements TritFunction {

	private Trit[][] trits;

	public TritTableFunction(Trit[][] vals) {
		this.trits = vals;
	}
	
	public TritTableFunction(int[][] vals) {
		Trit[][] trits = new Trit[3][3];
		for(int i=0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				trits[i][j] = Trit.fromInt(vals[i][j]);
			}
		}
		this.trits = trits;
	}
	
	@Override
	public Trit calc(Trit a, Trit b) {
		return trits[a.asInt()][b.asInt()];
	}

	public void print(String side) {
		System.out.println(side + " function = ");
		for(int i=0; i < trits.length; i++) {
			for(int j=0; j < trits[i].length; j++) {
				System.out.print(trits[i][j]);
			}
			System.out.println();
		}
	}

}
