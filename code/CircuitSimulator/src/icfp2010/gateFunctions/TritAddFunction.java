package icfp2010.gateFunctions;

import icfp2010.Trit;

public class TritAddFunction implements TritFunction {

	@Override
	public Trit calc(Trit a, Trit b) {
		return a.add(b);
	}

}
