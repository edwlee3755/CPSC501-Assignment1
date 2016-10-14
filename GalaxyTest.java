import static org.junit.Assert.*;

import org.junit.Test;

public class GalaxyTest 
{
	/*
	@Test
	public void testComputerInput() {
		assertTrue(Galaxy.computerInput() >= 0);
		assertTrue(Galaxy.computerInput() <= 9);
	}
	*/
	
	
	//Check if checkAdjacent correctly identifies if human and computer spaceship are adjacent
	@Test
	public void testCheckAdjacent() {

		GameManager userInterface = new GameManager();
		boolean expected = true;
		userInterface.milkyWay.humanPlayer.setRow(3);
		userInterface.milkyWay.humanPlayer.setColumn(3);
		userInterface.milkyWay.computerPlayer.setRow(2);
		userInterface.milkyWay.computerPlayer.setColumn(2);
		assertEquals(expected, userInterface.milkyWay.checkAdjacent() );
		
		userInterface.milkyWay.humanPlayer.setRow(1);
		userInterface.milkyWay.humanPlayer.setColumn(3);
		userInterface.milkyWay.computerPlayer.setRow(2);
		userInterface.milkyWay.computerPlayer.setColumn(2);
		assertEquals(expected, userInterface.milkyWay.checkAdjacent() );

		//Check not adjacent
		boolean expected2 = false;
		userInterface.milkyWay.humanPlayer.setRow(1);
		userInterface.milkyWay.humanPlayer.setColumn(3);
		userInterface.milkyWay.computerPlayer.setRow(3);
		userInterface.milkyWay.computerPlayer.setColumn(2);
		assertEquals(expected2, userInterface.milkyWay.checkAdjacent() );
		

		userInterface.milkyWay.humanPlayer.setRow(2);
		userInterface.milkyWay.humanPlayer.setColumn(1);
		userInterface.milkyWay.computerPlayer.setRow(2);
		userInterface.milkyWay.computerPlayer.setColumn(3);
		assertEquals(expected2, userInterface.milkyWay.checkAdjacent() );

	}
}
