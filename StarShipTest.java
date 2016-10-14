import static org.junit.Assert.*;

import org.junit.Test;

public class StarShipTest {

	@Test
	public void testRegenerateStarShipHealth() {
		
		StarShip player = new StarShip(300);
		player.regenerateStarShipHealth();
		assertEquals(340, player.getHullValue());
		
		
		//Check condition where hull regeneration cannot cause total health to exceed 400
		StarShip player2 = new StarShip(390);
		player2.regenerateStarShipHealth();
		assertEquals(400, player2.getHullValue());

	}
	

	@Test 
	public void testReduceStarShipHealth() {
		
		StarShip player = new StarShip(300);
		player.reduceStarShipHealth(100);
		assertEquals(200, player.getHullValue());
		
		StarShip player2 = new StarShip(200);
		player2.reduceStarShipHealth(155);
		assertEquals(45, player2.getHullValue());
		
		//Check condition where hull damage cannot cause total health to fall below 0
		StarShip player3 = new StarShip(200);
		player3.reduceStarShipHealth(255);
		assertEquals(0, player3.getHullValue());
	}
}
