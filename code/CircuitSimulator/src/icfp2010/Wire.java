package icfp2010;

public class Wire {

	protected Trit value = Trit.ZERO;
	
	public Trit get() {
		return value;
	}
	
	public void put(Trit value) {
		this.value = value;
	}
	
	public void cycle() {
		// do nothing for forward wires
	}

	public void reset() {
		this.value = Trit.ZERO;
	}
	
}
