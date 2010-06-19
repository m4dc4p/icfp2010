package icfp2010;

public enum Trit {
	ZERO(0,'0'),
	ONE(1,'1'),
	TWO(2,'2');
	
	private static Trit[] indexedTrits = new Trit[] { ZERO, ONE, TWO };
	
	private int intVal;
	private char charVal;

	private Trit(int intVal, char charVal) {
		this.intVal = intVal;
		this.charVal = charVal;
	}
	
	public static Trit fromInt(int x) {
		while(x < 0) {
			x += 3;
		}
		x %= 3;
		return indexedTrits[x];
	}
	
	public static Trit fromChar(char c) {
		if(c == '0') {
			return Trit.ZERO;
		} else if(c == '1') {
			return Trit.ONE;
		} else if(c == '2') {
			return Trit.TWO;
		}
		
		throw new RuntimeException("bad trit");
	}
	
	@Override
	public String toString() {
		return Character.toString(charVal);
	}
	
	public int asInt() {
		return intVal;
	}
	
	public Trit add(Trit t) {
		return fromInt(intVal + t.intVal);
	}
	
	public Trit sub(Trit t) {
		return fromInt(intVal - t.intVal);
	}

	public Trit mult(Trit t) {
		return fromInt(intVal * t.intVal);
	}
}
