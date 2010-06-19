package icfp2010;
import icfp2010.circuits.Spec1Builder;
import icfp2010.circuits.Spec2Builder;
import icfp2010.circuits.Spec3Builder;
import icfp2010.circuits.Spec4Builder;
import icfp2010.gateFunctions.TritTableFunction;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class TableBruteForcer {

	private static final long MAX_FUNCTIONS = (long)(Math.pow(3, 9)) - 1;
	private static final int INTEREST_THRESHOLD = 4;
	
	public static void main(String[] args) {
		
		System.out.println("input: ");
		TritUtils.print(Constants.MASTER_INPUT);
		
		List<List<Trit>> expectedOutputs = getExpectedOutputs();
		
		int run = 0;
		for(int i=0; i < MAX_FUNCTIONS; i++) {
			TritTableFunction leftFunction = generateTable(MAX_FUNCTIONS - i);
			for(int j=0; j < MAX_FUNCTIONS; j++) {
				TritTableFunction rightFunction = generateTable(MAX_FUNCTIONS - j);
				
				List<CircuitSimulator> simulators = createSimulators(leftFunction, rightFunction);
				List<List<Trit>> results = new ArrayList<List<Trit>>(simulators.size());
				
				for(CircuitSimulator sim : simulators) {
					results.add(sim.run(Constants.MASTER_INPUT));
				}
				
				int numMatches = compareResults(expectedOutputs, results);
				if(numMatches >= INTEREST_THRESHOLD) {
					System.out.println("" + numMatches + " matches found for function tables:");
					leftFunction.print("Left");
					rightFunction.print("Right");
				}
				
				run++;
				if(run % 1000000 == 0) {
					double percentComplete = ((double)run) * 100 / (MAX_FUNCTIONS * MAX_FUNCTIONS);
					System.out.format("Run: %d, %.2f%% complete\n", run, percentComplete);
				}
			}
		}
	}
	
	private static int compareResults(List<List<Trit>> expectedOutputs, List<List<Trit>> results) {
		Iterator<List<Trit>> resultIter = results.iterator();
		Iterator<List<Trit>> expectedIter = expectedOutputs.iterator();
		
		int numMatches = 0;
		int index = 1;
		while(resultIter.hasNext()) {
			List<Trit> result = resultIter.next();
			List<Trit> expected = expectedIter.next();
			
			if(TritUtils.compare(result, expected)) {
				numMatches++;
			}
			
			index++;
		}
		
		return numMatches;
	}

	private static List<CircuitSimulator> createSimulators(TritTableFunction leftFunction, TritTableFunction rightFunction) {
		ArrayList<CircuitSimulator> simulators = new ArrayList<CircuitSimulator>();
		simulators.add(new CircuitSimulator(new Spec1Builder(leftFunction, rightFunction)));
		simulators.add(new CircuitSimulator(new Spec2Builder(leftFunction, rightFunction)));
		simulators.add(new CircuitSimulator(new Spec3Builder(leftFunction, rightFunction)));
		simulators.add(new CircuitSimulator(new Spec4Builder(leftFunction, rightFunction)));
		
		return simulators;
	}
	
	private static List<List<Trit>> getExpectedOutputs() {
		ArrayList<List<Trit>> expectedOutputs = new ArrayList<List<Trit>>();
		expectedOutputs.add(Spec1Builder.KNOWN_OUTPUT);
		expectedOutputs.add(Spec2Builder.KNOWN_OUTPUT);
		expectedOutputs.add(Spec3Builder.KNOWN_OUTPUT);
		expectedOutputs.add(Spec4Builder.KNOWN_OUTPUT);
		
		return expectedOutputs;
	}

	public static TritTableFunction generateTable(long i) {
		List<Trit> input = generateInput(i, 9);
		Trit[][] trits = new Trit[3][3];
		
		trits[0][0] = input.get(0);
		trits[0][1] = input.get(1);
		trits[0][2] = input.get(2);
		
		trits[1][0] = input.get(3);
		trits[1][1] = input.get(4);
		trits[1][2] = input.get(5);
		
		trits[2][0] = input.get(6);
		trits[2][1] = input.get(7);
		trits[2][2] = input.get(8);
		
		return new TritTableFunction(trits);
	}

	private static List<Trit> generateInput(long value, int length) {
		String str = pad(length, '0', Long.toString(value, 3));
		return tritsFromString(str);
	}
	
	private static String pad(int length, char padString, String input) {
		StringBuilder builder = new StringBuilder();
		for(int i=0; i < length - input.length(); i++) {
			builder.append(padString);
		}
		
		builder.append(input);
		return builder.toString();
	}

	private static List<Trit> tritsFromString(String str) {
		LinkedList<Trit> trits = new LinkedList<Trit>();
		for(int i=0; i < str.length(); i++) {
			trits.add(Trit.fromChar(str.charAt(i)));
		}
		
		return trits;
	}
	
	public static List<Trit> read(Reader s) throws IOException {
		LinkedList<Trit> trits = new LinkedList<Trit>();
		int c;
		while((c = s.read()) != -1) {
			trits.add(Trit.fromChar((char)c));
		}
		return trits;
	}
}
