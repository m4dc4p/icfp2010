package icfp2010;
import icfp2010.circuits.CircuitBuilder;
import icfp2010.circuits.parser.CircuitParserHarness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.err.println("Required parameters: <trit_input_file> <circuit_descriptor>");
			return;
		}
		
		String inputFileName = args[0];
		String circuitFileName = args[1];
		try {
			FileReader reader = new FileReader(inputFileName);
			FileReader circuitReader = new FileReader(circuitFileName);
			
			List<Trit> input = TritUtils.read(reader);
			CircuitParserHarness parser = new CircuitParserHarness(circuitReader);
			CircuitSimulator simulator = new CircuitSimulator(parser);
			
			List<Trit> results = simulator.run(input);
			TritUtils.print(results);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println("Failed to read input file:" + e.getMessage());
		}
	}
}
