package icfp2010.circuits;

import java.util.List;

import icfp2010.BackWire;
import icfp2010.Constants;
import icfp2010.Gate;
import icfp2010.Trit;
import icfp2010.TritUtils;
import icfp2010.Wire;
import icfp2010.gateFunctions.TritFunction;

public class Spec3Builder extends AbstractCircuitBuilder {

	public static final List<Trit> KNOWN_OUTPUT = TritUtils.tritsFromString("22120221022022120");
	
	public Spec3Builder(TritFunction leftFunction, TritFunction rightFunction) {
		super(leftFunction, rightFunction);
	}
	
	public Spec3Builder() {
		super(Constants.LEFT_FUNCTION, Constants.RIGHT_FUNCTION);
	}
	
	@Override
	public void buildCircuitImpl(Wire masterInput, Wire masterOutput) {
		BackWire loop = new BackWire();
		Gate mainGate = new Gate(masterInput, loop, loop, masterOutput, getLeftFunction(), getRightFunction());
		
		addGate(mainGate);
		addWire(loop);
	}

}
