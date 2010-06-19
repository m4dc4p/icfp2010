package icfp2010.circuits.parser;

import icfp2010.BackWire;
import icfp2010.Constants;
import icfp2010.Gate;
import icfp2010.Wire;
import icfp2010.circuits.AbstractCircuitBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

public class CircuitParserHarness extends AbstractCircuitBuilder {

	private CommonTree parseTree;
	private Map<String,Wire> wireMap = new HashMap<String,Wire>();
	private Wire masterInput;
	private Wire masterOutput;

	public CircuitParserHarness(Reader reader) throws IOException {
		super(Constants.LEFT_FUNCTION, Constants.RIGHT_FUNCTION);
		ANTLRReaderStream antlrReader = new ANTLRReaderStream(reader);
		CircuitLexer lexer = new CircuitLexer(antlrReader);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CircuitParser parser = new CircuitParser(tokens);
		
		try {
			CircuitParser.circuit_return parsed = parser.circuit();
			parseTree = parsed.tree;
		} catch(RecognitionException e) {
			IOException failure = new IOException("Failed to parse circuit");
			failure.initCause(e);
			throw failure;
		}
	}
	
	@Override
	public void buildCircuitImpl(Wire masterInput, Wire masterOutput) {
		this.masterInput = masterInput;
		this.masterOutput = masterOutput;
		CommonTree worldInput = (CommonTree) parseTree.getChild(0);
		CommonTree gates = (CommonTree) parseTree.getChild(1);
		CommonTree worldOutput = (CommonTree) parseTree.getChild(2);
		
		parseGates(gates);
		
	}
	
	private void parseGates(CommonTree gatesRoot) {
		for(int i=0; i < gatesRoot.getChildCount(); i++) {
			CommonTree gateNode = (CommonTree) gatesRoot.getChild(i);
			
			CommonTree leftInputPosition = (CommonTree) gateNode.getChild(0);
			CommonTree rightInputPosition = (CommonTree) gateNode.getChild(1);
			// skip delimiter
			CommonTree leftOutputPosition = (CommonTree) gateNode.getChild(2);
			CommonTree rightOutputPosition = (CommonTree) gateNode.getChild(3);
			
			Wire leftIn = getInputWire(i, true, leftInputPosition);
			Wire rightInputWire = getInputWire(i, false, rightInputPosition);
			
			Wire leftOut = getOutputWire(i, true, leftOutputPosition);
			Wire rightOut = getOutputWire(i, false, rightOutputPosition);
			
			Gate g = new Gate(leftIn, rightInputWire, leftOut, rightOut, getLeftFunction(), getRightFunction());
			addGate(g);
		}
		
		for(Entry<String,Wire> entry : wireMap.entrySet()) {
			addWire(entry.getValue());
		}
	}

	private Wire getInputWire(int gateNum, boolean inputSide, CommonTree otherEnd) {
		if(isWorldPosition(otherEnd)) {
			// check this corresponds to spec of world input
			return masterInput;
		}
		
		int otherGateNum = Integer.parseInt(otherEnd.getChild(0).getText());
		boolean outputSide = directionFromString(otherEnd.getChild(1).getText());
		
		return getWire(otherGateNum, outputSide, gateNum, inputSide);
		
	}
	
	private Wire getOutputWire(int gateNum, boolean left, CommonTree otherEnd) {
		if(isWorldPosition(otherEnd)) {
			// check this corresponds to spec of world input
			return masterOutput;
		}
		
		int otherGateNum = Integer.parseInt(otherEnd.getChild(0).getText());
		boolean inputSide = directionFromString(otherEnd.getChild(1).getText());
		
		return getWire(gateNum, left, otherGateNum, inputSide);
	}
	
	private Wire getWire(int sourceGateNum, boolean sourceSide, int sinkGateNum, boolean sinkSide) {
		String wireName = getWireName(sourceGateNum, sourceSide, sinkGateNum, sinkSide);
		
		if(!wireMap.containsKey(wireName)) {
			if(sourceGateNum < sinkGateNum) {
				// forward propagation, no delay
				wireMap.put(wireName, new Wire());
			} else {
				// backward propagation, delay = 1
				wireMap.put(wireName, new BackWire());
			}
		}
		
		return wireMap.get(wireName);
	}

	private boolean isWorldPosition(CommonTree position) {
		// world position is just 'X' as a single child node
		return (position.getToken().getType() == CircuitLexer.WORLD_POSITION);
	}

	
	
	private String getWireName(int sourceGateNum, boolean sourceLeft, int sinkGateNum, boolean sinkLeft) {
		StringBuilder builder = new StringBuilder();
		builder.append(sourceGateNum);
		builder.append(direction(sourceLeft));
		builder.append(sinkGateNum);
		builder.append(direction(sinkLeft));
		
		return builder.toString();
	}

	private char direction(boolean left) {
		if(left) {
			return 'L';
		} else {
			return 'R';
		}
	}
	
	private boolean directionFromString(String directionStr) {
		if("L".equals(directionStr)) {
			return true;
		} else if("R".equals(directionStr)) {
			return false;
		}
		
		throw new RuntimeException("expected L or R, got " + directionStr);
	}

	/**
	 * Pulls the gate node out of a gate_tail node
	 */
	private CommonTree extractGateNode(CommonTree gatesNode, int index) {
		CommonTree node = (CommonTree) gatesNode.getChild(index);
		
		if(index == 0) {
			return node;
		} else {
			return (CommonTree) node.getChild(1);
		}
	}
}
