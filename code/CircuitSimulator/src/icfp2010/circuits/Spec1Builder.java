package icfp2010.circuits;

import icfp2010.BackWire;
import icfp2010.Constants;
import icfp2010.Gate;
import icfp2010.Trit;
import icfp2010.TritUtils;
import icfp2010.Wire;
import icfp2010.gateFunctions.TritFunction;

import java.util.List;

public class Spec1Builder extends AbstractCircuitBuilder {

	public static final List<Trit> KNOWN_OUTPUT = TritUtils.tritsFromString("02120112100002120");
	
	public Spec1Builder(TritFunction leftFunction, TritFunction rightFunction) {
		super(leftFunction, rightFunction);
	}
	
	public Spec1Builder() {
		super(Constants.LEFT_FUNCTION, Constants.RIGHT_FUNCTION);
	}


	@Override
	public void buildCircuitImpl(Wire masterInput, Wire masterOutput) {
		BackWire loop = new BackWire();
		Gate mainGate = new Gate(masterInput, loop, masterOutput, loop, getLeftFunction(), getRightFunction());
		
		addGate(mainGate);
		addWire(loop);
	}

}
