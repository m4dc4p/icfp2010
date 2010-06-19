package icfp2010;
import icfp2010.circuits.CircuitBuilder;

import java.util.LinkedList;
import java.util.List;


public class CircuitSimulator {

	private Wire masterInput;
	private Wire masterOutput;
	
	private List<Gate> gates;
	private List<Wire> wires;
	
	public CircuitSimulator(CircuitBuilder builder) {
		masterInput = new Wire();
		masterOutput = new Wire();
		builder.buildCircuit(masterInput, masterOutput);
		gates = builder.getGatesInTopologicalOrdering();
		wires = builder.getWires();
	}
	
	public List<Trit> run(List<Trit> input) {
		LinkedList<Trit> result = new LinkedList<Trit>();
		resetWires();
		
		for(Trit t : input) {
			masterInput.put(t);
			for(Gate g : gates) {
				g.compute();
			}
			result.add(masterOutput.get());
			cycleWires();
		}
		
		return result;
	}

	private void resetWires() {
		for(Wire w : wires) {
			w.reset();
		}
	}
	
	private void cycleWires() {
		for(Wire w : wires) {
			w.cycle();
		}
	}
}
