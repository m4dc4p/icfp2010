package icfp2010.circuits;

import icfp2010.Gate;
import icfp2010.Wire;
import icfp2010.gateFunctions.TritFunction;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCircuitBuilder implements CircuitBuilder {

	private List<Gate> gates;
	private List<Wire> wires;
	private final TritFunction leftFunction;
	private final TritFunction rightFunction;
	
	public AbstractCircuitBuilder(TritFunction leftFunction, TritFunction rightFunction) {
		gates = new LinkedList<Gate>();
		wires = new LinkedList<Wire>();
		this.leftFunction = leftFunction;
		this.rightFunction = rightFunction;
	}
	
	public final void buildCircuit(Wire masterInput, Wire masterOutput) {
		wires.add(masterInput);
		wires.add(masterOutput);
		
		buildCircuitImpl(masterInput, masterOutput);
	}
	
	protected abstract void buildCircuitImpl(Wire masterInput, Wire masterOutput);
	
	protected void addGate(Gate g) {
		gates.add(g);
	}
	
	protected void addWire(Wire w) {
		wires.add(w);
	}
	
	public final List<Wire> getWires() {
		return wires;
	}
	
	@Override
	public List<Gate> getGatesInTopologicalOrdering() {
		return gates;
	}
	
	public TritFunction getLeftFunction() {
		return leftFunction;
	}
	
	public TritFunction getRightFunction() {
		return rightFunction;
	}
}
