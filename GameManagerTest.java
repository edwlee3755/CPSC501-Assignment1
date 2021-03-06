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
		GameManager.printMovementMenu();
		
	}
	
	@Test
	public void testAttackMenu() {
		GameManager.printAttackMenu();
	}
	
	//Test if instance of Galaxy was made after extract class
	@Test
	public void testGameManagerConstrutor(){
		GameManager userInterface = new GameManager();
		assertTrue(userInterface.getMilkyWay() instanceof Galaxy);
	}
	
	// Checks if correct print when flags are set. Should print "Wow! it is a tie!"
	@Test
	public void testGameOver() {
		GameManager userInterface = new GameManager();
		userInterface.gameWon = true;
		userInterface.gameLost = true;
		userInterface.gameOver();
		
	}
}
