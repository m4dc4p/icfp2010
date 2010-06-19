package icfp2010.gateFunctions;

import icfp2010.Trit;

public class TritMultAddTwoFunction implements TritFunction {

	@Override
	public Trit calc(Trit a, Trit b) {
		return a.mult(b).add(Trit.TWO);
	}
}
