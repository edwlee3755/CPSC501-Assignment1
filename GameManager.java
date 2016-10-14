import java.util.Scanner;
import java.util.Random;

public class GameManager
{
    public Galaxy milkyWay;
    // Decide who's turn it currently is and if ships are adjacent.
    boolean humanTurn = true;
    boolean adjacentValue = false;

    public GameManager()
    {
        milkyWay = new Galaxy();
    }

    public void startProcessingCommands()
    {
        boolean computerTurnAccessible = false;
        while ((GameStatus.gameLost != true) && (GameStatus.gameWon != true))    
        {   
            int selection; 
            // Condition for computer's turn.
            if (computerTurnAccessible == true)
            {
                humanTurn = false;
                selection = computerInput();
                milkyWay.moveShip(selection, humanTurn);
            }
            humanTurn = true;
            System.out.println("");
            if (GameStatus.cheatModeOn == true) {
                System.out.println("Cheat mode activated.");
            }
            if (GameStatus.debugModeOn == true) {
                System.out.println("Debug mode activated.");
                System.out.println("Game Status");
                System.out.println("Game won: " + GameStatus.gameWon);
                System.out.println("Game lost: " + GameStatus.gameLost);
            }
            milkyWay.display();
            printMovementMenu();
            selection = askInput();
            milkyWay.moveShip(selection, humanTurn);
            milkyWay.display();
            adjacentValue = milkyWay.checkAdjacent();
            if (adjacentValue == true)
            {
                printAttackMenu();
                selection = askInput();
                milkyWay.attackShip(selection);
            }
            milkyWay.checkEndGame();
            computerTurnAccessible = true;
        }
        // Run end-game method.
        gameOver();
    }

    // Method to display movement menu.
    public static void printMovementMenu()
    {
        System.out.println("");
        System.out.println("MOVEMENT MENU: OPTIONS");
        System.out.println("The numbers on the keypad represent the direction");
        System.out.println("of movement.");
        System.out.println("7 8 9");
        System.out.println("4   6");
        System.out.println("1 2 3");
        System.out.println("(5) pass on the movement phase");
        System.out.println("To quit the game enter the value zero");
        System.out.print("Enter your menu selection: "); 
    }

    // Method to display attack menu.
    public static void printAttackMenu()
    {
        System.out.println("");
        System.out.println("ATTACK MENU: OPTIONS");
        System.out.println("The numbers on the keypad represent the direction");
        System.out.println("of attack.");
        System.out.println("7 8 9");
        System.out.println("4   6");
        System.out.println("1 2 3");
        System.out.println("(5) pass on the attack phase");
        System.out.println("To quit the game enter the value zero");
        System.out.print("Enter your menu selection: ");         
    }
    
    // Method that asks for user input.
    public int askInput()
    {
        int selection;
        String selectionCheat;         
        Scanner in = new Scanner(System.in);    
        selection = in.nextInt();
        while (selection > 9)
        {
            System.out.println("Selection entered is invalid.");
            System.out.print("Enter your menu selection: ");
            selection = in.nextInt();
        }
        if (selection == 0) { 
            System.exit(0);
        }
        else if (selection < 0)
        {
            System.out.println("Cheat menu");
            if (GameStatus.cheatModeOn != true) {
                System.out.println("(c)heat mode on");
            }
            else {
                System.out.println("(c)heat mode off");
            }
            if (GameStatus.debugModeOn != true) {
                System.out.println("(d)ebug mode on");
            }
            else {
                System.out.println("(d)ebug mode off");
            }
            System.out.print("Enter your cheat selection: ");
            selectionCheat = in.next();

            if (selectionCheat.equals("c"))
            {
                if (GameStatus.cheatModeOn != true)
                {
                    GameStatus.cheatModeOn = true;
                    System.out.println("Cheat mode activated: Ship is now invulnerable to further attacks.");
                }

                else 
                {
                    GameStatus.cheatModeOn = false;
                    System.out.println("Cheat mode de-activated: Ship can now be damaged.");
                }
            }
            else if (selectionCheat.equals("d"))
            {
                if (GameStatus.debugModeOn != true)
                {
                    GameStatus.debugModeOn = true;
                    System.out.println("Debug mode activated.");
                }

                else
                {
                    GameStatus.debugModeOn = false;
                    System.out.println("Debug mode de-activated.");
                }
            }

            else
            {
                System.out.println("Selection entered is invalid.");
            }
        }
        return(selection);
    }

    // Method that generates computer input.
    public int computerInput() 
    {   
        int selection;
        Random aiRandomInput = new Random();
        selection = aiRandomInput.nextInt(9) + 1;
        return selection;
    }  


    // Method to run when the game ends.
    public void gameOver()
    {
        if ((GameStatus.gameWon == true) && (GameStatus.gameLost == true))
        {
            System.out.println("========================================");
            System.out.println("           Wow! It is a tie!");
            System.out.println("========================================");
        }
        else if (GameStatus.gameWon == true)
        {
            System.out.println("========================================");
            System.out.println("  Congradulations! You won the game!");
            System.out.println("========================================");
        }

        else if (GameStatus.gameLost == true)
        {
            System.out.println("========================================");
            System.out.println("     Your ship is gone. Try again!");
            System.out.println("========================================");            
        }
        

    }      
    
    public Galaxy getMilkyWay(){
    	return this.milkyWay; 
    }  
}