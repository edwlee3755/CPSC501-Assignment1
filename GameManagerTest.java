import static org.junit.Assert.*;

import org.junit.Test;

public class GameManagerTest {

	@Test
	public void testComputerInput() {
		assertTrue(GameManager.computerInput() >= 0);
		assertTrue(GameManager.computerInput() <= 9);
	}
	
	@Test
	public void testPrint1() {
		//GameManager.print1();
		
	}

}
