package icfp2010.circuits;

import icfp2010.BackWire;
import icfp2010.Constants;
import icfp2010.Gate;
import icfp2010.Trit;
import icfp2010.TritUtils;
import icfp2010.Wire;
import icfp2010.gateFunctions.TritFunction;

import java.util.List;

public class Spec2Builder extends AbstractCircuitBuilder {

	public static final List<Trit> KNOWN_OUTPUT = TritUtils.tritsFromString("22022022022022022");
	
	public Spec2Builder(TritFunction leftFunction, TritFunction rightFunction) {
		super(leftFunction, rightFunction);
	}
	
	public Spec2Builder() {
		super(Constants.LEFT_FUNCTION, Constants.RIGHT_FUNCTION);
	}
	
	@Override
	public void buildCircuitImpl(Wire masterInput, Wire masterOutput) {
		BackWire loop = new BackWire();
		Gate mainGate = new Gate(loop, masterInput, loop, masterOutput, getLeftFunction(), getRightFunction());
		
		addGate(mainGate);
		addWire(loop);
	}

}
