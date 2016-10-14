import static org.junit.Assert.*;

import org.junit.Test;

public class GameManagerTest {

	@Test
	public void testComputerInput() {		
		GameManager userInterface = new GameManager();
		assertTrue(userInterface.computerInput() >= 0);
		assertTrue(userInterface.computerInput() <= 9);
	}
	
	@Test
	public void testMovementMenu() {
		//GameManager.printMovementMenu();
		
	}
	
	//Test if instance of Galaxy was made after extract class
	@Test
	public void testGameManagerConstrutor(){
		GameManager userInterface = new GameManager();
		assertTrue(userInterface.getMilkyWay() instanceof Galaxy);
	}
	
	
}
