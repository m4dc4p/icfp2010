package icfp2010;

import icfp2010.gateFunctions.TritFunction;

public class Gate {

	public Wire leftInput;
	public Wire rightInput;

	public Wire leftOutput;
	public Wire rightOutput;
	private final TritFunction leftF;
	private final TritFunction rightF;
	
	public Gate(Wire leftInput, Wire rightInput, Wire leftOutput, Wire rightOutput, TritFunction leftF, TritFunction rightF) {
		this.leftInput = leftInput;
		this.rightInput = rightInput;
		this.leftOutput = leftOutput;
		this.rightOutput = rightOutput;
		this.leftF = leftF;
		this.rightF = rightF;
	}

	public void compute() {
		Trit in1 = leftInput.get();
		Trit in2 = rightInput.get();
		
		Trit out1 = computeLeft(in1, in2);
		Trit out2 = computeRight(in1, in2);
		
		leftOutput.put(out1);
		rightOutput.put(out2);
	}

	private Trit computeLeft(Trit in1, Trit in2) {
		return leftF.calc(in1, in2);
	}
	
	private Trit computeRight(Trit in1, Trit in2) {
		return rightF.calc(in1, in2);
	}
}
