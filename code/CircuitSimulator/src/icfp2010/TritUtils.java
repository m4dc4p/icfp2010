package icfp2010;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class TritUtils {
	
	public static final void print(List<Trit> output) {
		for(Trit t : output) {
			System.out.print(t);
		}
		
		System.out.println();
	}

	public static final List<Trit> generateInput(long value) {
		String str = padString(17, '0', Long.toString(value, 3));
		return tritsFromString(str);
	}
	
	
	public static final String padString(int length, char padLength, String input) {
		StringBuilder builder = new StringBuilder();
		for(int i=0; i < 17 - input.length(); i++) {
			builder.append(padLength);
		}
		
		builder.append(input);
		return builder.toString();
	}

	public static final List<Trit> tritsFromString(String str) {
		LinkedList<Trit> trits = new LinkedList<Trit>();
		for(int i=0; i < str.length(); i++) {
			trits.add(Trit.fromChar(str.charAt(i)));
		}
		
		return trits;
	}
	
	public static final List<Trit> read(Reader s) throws IOException {
		LinkedList<Trit> trits = new LinkedList<Trit>();
		int c;
		while((c = s.read()) != -1) {
			trits.add(Trit.fromChar((char)c));
		}
		return trits;
	}
	
	public static final boolean compare(List<Trit> a, List<Trit> b) {
		Iterator<Trit> oi = a.iterator();
		Iterator<Trit> ei = b.iterator();
		
		while(oi.hasNext()) {
			Trit at = oi.next();
			Trit et = ei.next();
			if(at != et) {
				return false;
			}
		}
		
		return true;
	}
}
