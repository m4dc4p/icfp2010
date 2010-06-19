package icfp2010;
import static org.junit.Assert.assertTrue;
import icfp2010.circuits.Spec1Builder;
import icfp2010.circuits.Spec2Builder;
import icfp2010.circuits.Spec3Builder;
import icfp2010.circuits.Spec4Builder;

import java.util.List;

import org.junit.Test;


public class CircuitTest {

	private static final List<Trit> INPUT = TritUtils.tritsFromString("01202101210201202");
	
	@Test
	public void testSingleGateCircuits() {
		
		CircuitSimulator sim1 = new CircuitSimulator(new Spec1Builder());
		CircuitSimulator sim2 = new CircuitSimulator(new Spec2Builder());
		CircuitSimulator sim3 = new CircuitSimulator(new Spec3Builder());
		CircuitSimulator sim4 = new CircuitSimulator(new Spec4Builder());
		
		assertTrue(TritUtils.compare(Spec1Builder.KNOWN_OUTPUT, sim1.run(INPUT)));
		assertTrue(TritUtils.compare(Spec2Builder.KNOWN_OUTPUT, sim2.run(INPUT)));
		assertTrue(TritUtils.compare(Spec3Builder.KNOWN_OUTPUT, sim3.run(INPUT)));
		assertTrue(TritUtils.compare(Spec4Builder.KNOWN_OUTPUT, sim4.run(INPUT)));
	}
}
