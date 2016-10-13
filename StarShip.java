import java.util.*;

public class StarShip
{
    public static final int MAX_HULL = 400;
    public static final char DEFAULT_APPEARANCE = 'C';
    private char appearance;
    private int hullValue;
    private int row;
    private int column;
    
    public StarShip ()
    {
	   appearance = DEFAULT_APPEARANCE;
	   hullValue = MAX_HULL;
    }

    public StarShip (int hull)
    {
	   appearance = DEFAULT_APPEARANCE;
	   hullValue = hull;
    }

    public StarShip (char newAppearance)
    {
	this();
	appearance = newAppearance;
    }


    public char getAppearance ()
    {
	return appearance;
    }

    public int getColumn()
    {
	return(column);
    }

    public int getRow()
    {
	return(row);
    }

    public int getHullValue()
    {
	return(hullValue);
    }   

    public void setAppearance(char newAppearance)
    {
	appearance = newAppearance;
    }

    public void setColumn(int newColumn)
    {
	column = newColumn;
    }

    public void setRow(int newRow)
    {
	row = newRow;
    }

    public int determineDamage()
    {
        int damageDealt;
        Random rand = new Random();
        damageDealt = rand.nextInt(100) + 1;
        return damageDealt;
    }

    public void regenerate()
    {
        hullValue = hullValue + 40;
        if (hullValue > MAX_HULL)
        {
            hullValue = 400;
        }
    }

    public void reduceHull(int damageTaken)
    {
        hullValue = hullValue - damageTaken;
        if (hullValue < 0)
        {
            hullValue = 0;
        }
    }
}