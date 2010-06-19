package icfp2010.circuits;

import icfp2010.Gate;
import icfp2010.Wire;

import java.util.List;


public interface CircuitBuilder {

	void buildCircuit(Wire masterInput, Wire masterOutput);
	List<Gate> getGatesInTopologicalOrdering();
	List<Wire> getWires();

	
}
