import static org.junit.Assert.*;

import org.junit.Test;

public class GalaxyTest 
{
	@Test
	public void testComputerInput() {
		assertTrue(Galaxy.computerInput() >= 0);
		assertTrue(Galaxy.computerInput() <= 9);
	}

}
