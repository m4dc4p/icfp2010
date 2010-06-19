package icfp2010;
import junit.framework.TestCase;


public class TritTest extends TestCase {

	public void testAdd() {
		assertEquals(Trit.ZERO, Trit.ZERO.add(Trit.ZERO));
		assertEquals(Trit.ONE, Trit.ZERO.add(Trit.ONE));
		assertEquals(Trit.TWO, Trit.ZERO.add(Trit.TWO));
		
		assertEquals(Trit.ONE, Trit.ONE.add(Trit.ZERO));
		assertEquals(Trit.TWO, Trit.ONE.add(Trit.ONE));
		assertEquals(Trit.ZERO, Trit.ONE.add(Trit.TWO));
		
		assertEquals(Trit.TWO, Trit.TWO.add(Trit.ZERO));
		assertEquals(Trit.ZERO, Trit.TWO.add(Trit.ONE));
		assertEquals(Trit.ONE, Trit.TWO.add(Trit.TWO));
	}
	
	public void testSub() {
		assertEquals(Trit.ZERO, Trit.ZERO.sub(Trit.ZERO));
		assertEquals(Trit.TWO, Trit.ZERO.sub(Trit.ONE));
		assertEquals(Trit.ONE, Trit.ZERO.sub(Trit.TWO));
		
		assertEquals(Trit.ONE, Trit.ONE.sub(Trit.ZERO));
		assertEquals(Trit.ZERO, Trit.ONE.sub(Trit.ONE));
		assertEquals(Trit.TWO, Trit.ONE.sub(Trit.TWO));
		
		assertEquals(Trit.TWO, Trit.TWO.sub(Trit.ZERO));
		assertEquals(Trit.ONE, Trit.TWO.sub(Trit.ONE));
		assertEquals(Trit.ZERO, Trit.TWO.sub(Trit.TWO));
	}
	
	public void testMult() {
		assertEquals(Trit.ZERO, Trit.ZERO.mult(Trit.ZERO));
		assertEquals(Trit.ZERO, Trit.ZERO.mult(Trit.ONE));
		assertEquals(Trit.ZERO, Trit.ZERO.mult(Trit.TWO));
		
		assertEquals(Trit.ZERO, Trit.ONE.mult(Trit.ZERO));
		assertEquals(Trit.ONE, Trit.ONE.mult(Trit.ONE));
		assertEquals(Trit.TWO, Trit.ONE.mult(Trit.TWO));
		
		assertEquals(Trit.ZERO, Trit.TWO.mult(Trit.ZERO));
		assertEquals(Trit.TWO, Trit.TWO.mult(Trit.ONE));
		assertEquals(Trit.ONE, Trit.TWO.mult(Trit.TWO));
	}
	
}
