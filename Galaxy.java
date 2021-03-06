import java.util.Random; 
import java.util.Scanner;

public class Galaxy 
{
    public static final int SIZE = 4;
    private StarShip [][] grid;
    public StarShip humanPlayer;
    public StarShip computerPlayer;
    
    // Decide who's turn it currently is and if ships are adjacent.
    boolean humanTurn = true;
    boolean adjacentValue = false;

    /*
      Player's ship always in [0][0] the terran sector. The 
      computer ship location randomly generated.
    */
    public Galaxy ()
    {
    	boolean squareOccupied = false;
		grid = new StarShip [SIZE][SIZE];
		Random generator = new Random ();
		Scanner in = new Scanner(System.in);
		int r;
		int c;
		int hull=300;

//		System.out.print("Computer ship hull value: ");
//		hull = in.nextInt();
		computerPlayer = new StarShip(hull);
		computerPlayer.setAppearance(StarShip.DEFAULT_APPEARANCE);

		// Initialize all default locations to null.
		for (r = 0; r < SIZE; r++)
		{
		    for (c = 0; c < SIZE; c++)
		    {
				grid[r][c] = null;
		    }
		}
		// Place the human-controlled ship at sector (0,0).
		humanPlayer = new StarShip('H');
		grid[0][0] = humanPlayer;
	
		// Place the computer-controlled ship randomly 	
        do
		{
	    // Call to method randomly generate a set of 
        // (row, column coordinates somewhere within the 
        // galaxy).
	    generateCoordinates(computerPlayer);
	    if ((computerPlayer.getRow() == 0) && 
            (computerPlayer.getColumn() == 0))
			squareOccupied = true;
	    else
		 	squareOccupied = false;
	}   while (squareOccupied == true); 
			grid[computerPlayer.getRow()][computerPlayer.getColumn()] = computerPlayer;
    }

    
    // Display method to display the galaxy (no grid)
    public void display ()
    {
	int r;
	int c;

	System.out.println("");
	System.out.println("  0 1 2 3");
	System.out.println("  - - - -");
	for (r = 0; r < SIZE; r++)
	{
		System.out.print(r);
	    for (c = 0; c < SIZE; c++)
	    {
	    	System.out.print("|");
			if (grid[r][c] != null)
			{
		    	System.out.print(grid[r][c].getAppearance());
			}
			else
			{
		    		System.out.print(" ");
			}
		}
	    System.out.println("|");
	    System.out.println("  - - - -");
	}
	System.out.println("");
    System.out.println("Ship statistics");
    System.out.print("Player ship hull: " + humanPlayer.getHullValue());
    System.out.println("  Computer ship hull: " + computerPlayer.getHullValue());	
    }

   
     //Method to randomly generate coordinates for a ship 
    public void generateCoordinates(StarShip ship)
    {
		Random generator = new Random ();
		ship.setRow(generator.nextInt(SIZE));
		ship.setColumn(generator.nextInt(SIZE));
    }
    
    // Method to move the StarShip accordingly.
    public void moveShip(int selection, boolean humanTurn)
    {
    	int rowChange = 0;
    	int columnChange = 0;
        boolean boundValidate;
        Scanner in = new Scanner(System.in);
        Random ai = new Random();
    	switch(selection)
    	{
    		case 9:
    			// Move ship NE.;
    		    rowChange = -1;
    			columnChange = 1;
    			break;
    		case 8:
    			// Move ship N.;
    		    rowChange = -1; 			
    			break;
    		case 7:
    			// Move ship NW.;
    		    rowChange = -1;
    			columnChange = -1;
    			break;
    		case 6:
    			// Move ship E.;
    			columnChange = 1;  
    			break;
    		case 5:
    			// Do nothing.;
    			System.out.println("");
    			System.out.println("You have chosen not to move.");
    			System.out.println("");
    			break;
    		case 4:
    			// Move ship W.;
    			columnChange = -1;
    			break;
    		case 3:
    			// Move ship SE.;
    		    rowChange = 1;
    			columnChange = 1;
    			break;
    		case 2:
    			// Move ship S.;
    		    rowChange = 1;
    			break;
    		case 1:
    			// Move ship SW.;
    		    rowChange = 1;
    			columnChange = -1;
    			break;	
    	}
		if (humanTurn == true)
		{
		    boundValidate = checkBounds(humanTurn, rowChange, columnChange);
		    if (boundValidate == true)
		    {
				grid[humanPlayer.getRow()][humanPlayer.getColumn()] = null;
				humanPlayer.setRow(humanPlayer.getRow() + rowChange);
				humanPlayer.setColumn(humanPlayer.getColumn() + columnChange);
				grid[humanPlayer.getRow()][humanPlayer.getColumn()] = humanPlayer;
			}
			else
			{
			    System.out.print("Movement out of bounds. Enter a new selection for movement: ");
			    selection = in.nextInt();
			    moveShip(selection, humanTurn);
			}
		}
		else
		{
			if (GameManager.debugModeOn == true)
			{
				System.out.println("Debug Mode currently on.");
				System.out.println("Moving Computer's ship...");
				System.out.println("Old Row: " + computerPlayer.getRow() + " Old Column: " + computerPlayer.getColumn());
				System.out.println("New Row: " + (computerPlayer.getRow() + rowChange) + " New Column: " + (computerPlayer.getColumn() + columnChange));
			}
		    boundValidate = checkBounds(humanTurn, rowChange, columnChange);
		    if (boundValidate == true)
		    {
				grid[computerPlayer.getRow()][computerPlayer.getColumn()] = null;
				computerPlayer.setRow(computerPlayer.getRow() + rowChange);
				computerPlayer.setColumn(computerPlayer.getColumn() + columnChange);
				grid[computerPlayer.getRow()][computerPlayer.getColumn()] = computerPlayer;
			}
			else
			{
			    selection = ai.nextInt(9) + 1;
			    moveShip(selection, humanTurn);
			}
		}
    }
    
    // Method to check if ship movement is valid.
    public boolean checkBounds(boolean humanTurn, int rowChange, int columnChange)
    {
        boolean boundValidate = false;
        if (humanTurn == true)
        {
            // Check conditions for row and column change.
            if (((humanPlayer.getRow() + rowChange) >= 0) && ((humanPlayer.getRow() + rowChange) < SIZE))
            {
                if (((humanPlayer.getColumn() + columnChange) >= 0) && ((humanPlayer.getColumn() + columnChange) < SIZE))
                {
                    boundValidate = true;
                }
            }
            else {
                boundValidate = false;
            }
	        if (((humanPlayer.getRow() + rowChange) == computerPlayer.getRow()) && ((humanPlayer.getColumn() + columnChange) == computerPlayer.getColumn()))
	        {
	    		System.out.println("You cannot move into a space that is already occupied!");
	        	boundValidate = false;    
	        }
	    }
        else
        {
            // Check conditions for row and column change.
            if (((computerPlayer.getRow() + rowChange) >= 0) && ((computerPlayer.getRow() + rowChange) < SIZE))
            {
                if (((computerPlayer.getColumn() + columnChange) >= 0) && ((computerPlayer.getColumn() + columnChange) < SIZE))
                {
                    boundValidate = true;
                }
            }
            else {
                boundValidate = false;
            }
            if ((humanPlayer.getRow() == (computerPlayer.getRow() + rowChange)) && (humanPlayer.getColumn() == (computerPlayer.getColumn() + columnChange)))
	        {
	        	boundValidate = false;    
	        }
        }
        return (boundValidate);
    }

    public boolean checkAdjacent()
    {
    	boolean adjacentValue = false;
        // Check if computer is at position (9) in relation to the human.
        if (((humanPlayer.getRow()) == (computerPlayer.getRow() + 1)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() - 1)))
        {
            adjacentValue = true;
        }
        // Check if computer is at position (8) in relation to the human.
        if (((humanPlayer.getRow()) == (computerPlayer.getRow() + 1)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() + 0)))
        {
            adjacentValue = true;
        }
        // Check if computer is at position (7) in relation to the human.
        if (((humanPlayer.getRow()) == (computerPlayer.getRow() + 1)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() + 1)))
        {
            adjacentValue = true;
        }
        // Check if computer is at position (6) in relation to the human.
        if (((humanPlayer.getRow()) == (computerPlayer.getRow() + 0)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() - 1)))
        {
            adjacentValue = true;
        }
        // Check if computer is at position (4) in relation to the human.
        if (((humanPlayer.getRow()) == (computerPlayer.getRow() + 0)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() + 1)))
        {
            adjacentValue = true;
        }
        // Check if computer is at position (3) in relation to the human.
        if (((humanPlayer.getRow()) == (computerPlayer.getRow() - 1)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() - 1)))
        {
            adjacentValue = true;
        }
        // Check if computer is at position (2) in relation to the human.
        if (((humanPlayer.getRow()) == (computerPlayer.getRow() - 1)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() + 0)))
        {
            adjacentValue = true;
        }
        // Check if computer is at position (1) in relation to the human.
        if (((humanPlayer.getRow()) == (computerPlayer.getRow() - 1)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() + 1)))
        {
            adjacentValue = true;
        }

        return (adjacentValue);
    }
    
    // Method for ships to attack and lower the null values accordingly.
    public void attackShip(int selection)
    {
    	int humanDamage;
    	int computerDamage;
    	int rowCalibrate = 0;
    	int columnCalibrate = 0;    	
    	switch(selection)
    	{
    		case 9:
    			rowCalibrate = 1;
    			columnCalibrate = -1;
    			break;
    		case 8:
    			rowCalibrate = 1;
    			break;	    
    		case 7:
    			rowCalibrate = 1;
    			columnCalibrate = 1;
    			break;	
    		case 6:
    			columnCalibrate = -1;
    			break;	
    		case 5:
    			System.out.println("You have chosen to not attack."); 				
    			break;	
    		case 4:
    			columnCalibrate = 1;
    			break;	
    		case 3:
    			rowCalibrate = -1;
    			columnCalibrate = -1;
    			break;	
    		case 2:
    			rowCalibrate = -1;
    			break;	
    		case 1:
    			rowCalibrate = -1;
    			columnCalibrate = 1;
    			break;							
    	}
		if (((humanPlayer.getRow()) == (computerPlayer.getRow() + rowCalibrate)) && ((humanPlayer.getColumn()) == (computerPlayer.getColumn() + columnCalibrate)))
		{
            humanDamage = humanPlayer.determineDamage();
            computerDamage = computerPlayer.determineDamage();
            humanPlayer.reduceStarShipHealth(computerDamage);
            computerPlayer.reduceStarShipHealth(humanDamage);
            if (GameManager.cheatModeOn == true) {
            	computerDamage = 0;
            }
            System.out.println("");
            System.out.println("Computer attack on player:   opponent attacks you for " + computerDamage + " damage.");
            System.out.println("Player attack on computer ship:   you attack opponent for " + humanDamage + " damage.");
            System.out.println("");  				
		}    	
    }

    // Method to determine end game conditions and if regeneration will activate.
    public void checkEndGame()
    {
    	if (humanPlayer.getHullValue() == 0) {
    		GameManager.gameLost = true;
    	}
    	else {
    		GameManager.gameLost = false;
    	}
    	if (computerPlayer.getHullValue() == 0) {
    		GameManager.gameWon = true;
    	}
    	else {		
    		GameManager.gameWon = false;
    	}
    	if ((GameManager.gameWon == true) && (GameManager.gameLost == true))
    	{
    		GameManager.gameTie = true;
    	}
    	if ((GameManager.gameWon != true) && (GameManager.gameLost != true))
    	{
    		System.out.println("The ships will now regenerate.");
    		humanPlayer.regenerateStarShipHealth();
    		computerPlayer.regenerateStarShipHealth();
    	}
    }   
}