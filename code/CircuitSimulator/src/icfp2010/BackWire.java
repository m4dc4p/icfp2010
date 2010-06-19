package icfp2010;

/**
 * Back propagation wire. Values put into the wire can only be read
 * after cycle has been called, to represent a back-propagation
 * delay of 1.
 */
public class BackWire extends Wire {

	private Trit nextValue;
	
	@Override
	public void put(Trit newValue) {
		this.nextValue = newValue;
	}
	
	@Override
	public void cycle() {
		this.value = nextValue;
	}
	
	
}
