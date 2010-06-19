package icfp2010.circuits;

import java.util.List;

import icfp2010.BackWire;
import icfp2010.Constants;
import icfp2010.Gate;
import icfp2010.Trit;
import icfp2010.TritUtils;
import icfp2010.Wire;
import icfp2010.gateFunctions.TritFunction;

public class Spec4Builder extends AbstractCircuitBuilder {

	public static final List<Trit> KNOWN_OUTPUT = TritUtils.tritsFromString("01210221200001210");
	
	public Spec4Builder(TritFunction leftFunction, TritFunction rightFunction) {
		super(leftFunction, rightFunction);
	}
	
	public Spec4Builder() {
		super(Constants.LEFT_FUNCTION, Constants.RIGHT_FUNCTION);
	}
	
	@Override
	public void buildCircuitImpl(Wire masterInput, Wire masterOutput) {
		BackWire loop = new BackWire();
		Gate mainGate = new Gate(loop, masterInput, masterOutput, loop, getLeftFunction(), getRightFunction());
		
		addGate(mainGate);
		addWire(loop);
	}

}
