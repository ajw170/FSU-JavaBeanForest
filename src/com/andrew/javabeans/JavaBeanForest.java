package com.andrew.javabeans;/* Andrew J Wood
   COP3252 - Java
   com.andrew.javabeans.JavaBeanForest.java

   Java Bean Forest simulates a walk through the forest filled with enemies.  The specific
   enemy objects derive from an abstract base class com.andrew.javabeans.Enemy.

   As the com.andrew.javabeans.Knight progresses through the forest, he/she both attacks the enemies and is attacked.

   The user is able to specify how many enemies the knight will encounter.  In this particular
   implementation, the user may encounter any enemy type randomly.

   The results are displayed at the end.  If the knight still has HitPoints, the game is won.
 */


import java.util.InputMismatchException;
import java.util.Scanner;


public class JavaBeanForest
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to Java Bean Forest!");
	    System.out.println("You will navigate your knight through an enemy encampment in the forest!");
	    System.out.print("\n");
	    System.out.println("Your knight's attributes will be randomly generated upon start.");
	    System.out.println("This means your knight may be stronger or weaker than expected!\n");

	    int numEnemies = 0;
	    Scanner input = new Scanner(System.in);
	    boolean continueLoop = true;

	    //error handler - note this is rather contrived to conform to assignment requirements
        do {
            continueLoop = true;
            try {
                System.out.print("Enter how many enemies your knight will face: ");
                numEnemies = input.nextInt();
                if (numEnemies <= 0) {
                    continueLoop = false;
                    throw new InvalidEnemyCountException();
                }
            }
            //the user could input something other than an int or could input an int less than 0...not good!
            catch (InvalidEnemyCountException | InputMismatchException exception) {
                System.out.println("Exception: " + exception);
                System.out.println("Please try again.");
                input.next();
                continueLoop = false;
            }
        } while (!continueLoop);

        //create an array of enemies - they are Fightables
        Enemy [] enemiesArray = new Enemy[numEnemies];
        for (int i = 0; i < numEnemies; ++i)
        {
            //utilizes factory method to generate a random enemy
            enemiesArray[i] = Enemy.getRandomEnemy();
        }

        //Now begin the battle with the enemies!
        Knight playerKnight = new Knight(); //randomly creates a night with random attributes - a com.andrew.javabeans.Fightable
        System.out.println("Here are the characteristics of your valiant com.andrew.javabeans.Knight:");
        System.out.println(playerKnight);
        System.out.print("\n");
        System.out.println("Here goes the night through the forest...");

        boolean wonStatus = true; //default win

        //The knight will fight the enemies.  The fight continues until either the com.andrew.javabeans.Knight or the
        //enemy's health reaches 0.
        for (int i = 0; i < numEnemies; ++i)
        {
            int result = encounterEnemy(playerKnight,enemiesArray[i]);
            if (result == -1) //player lost
            {
                System.out.println("\nSadly, you were defeated by the evil " + enemiesArray[i].shortName() +
                        "! Game Over.");
                wonStatus = false;
                break;
            }
            System.out.println("\nYou defeated the evil " + enemiesArray[i].shortName() +"!");
        }

        //check win status, display accordingly
        if (wonStatus)
        {
            System.out.println("The valiant night successfully navigated through the forest and defeated " +
                    numEnemies + " enemies along the way!");
        }
        else
            {
            System.out.print("The knight was not valiant today.  This is a sad day for the kingdom.");
        }


    } //end main

    //encounters the enemy and assigns appropriate damage values
    public static int encounterEnemy(Knight knight, Enemy enemy)
    {
        Scanner scanner = new Scanner(System.in);
        //fight until either the knight or the enemy is defeated!
        System.out.println("\nOh no! You encountered an evil " + enemy.shortName() + "!");
        System.out.println("Their health value is " + enemy.getHealth() + "!");
        System.out.print("Press enter to continue ...");
        scanner.nextLine(); //discard
        System.out.print("\n");

        while (knight.getHealth() > 0 && enemy.getHealth() > 0) //neither of the opponents are defeated
        {
            int damageAmount = knight.fight(enemy);
            try {
                enemy.takeDamage(damageAmount);
            }
            catch (InvalidDamageException exception)
            {
                System.out.println("Exception: " + exception);
                System.out.println("Your devastating attach on the enemy was too great!");
                enemy.setHealth(0); //amount of damage passed was too much, so set health to 0.
            }
            System.out.println("After your attack, the enemy's health is now " + enemy.getHealth() + "!");
            if (enemy.getHealth() <= 0)
                break; //leave the loop, the enemy was defeated.
            damageAmount = enemy.fight(knight);
            try {
                knight.takeDamage(damageAmount);
            }
            catch (InvalidDamageException exception)
            {
                System.out.println("Exception: " + exception);
                System.out.println("The enemy's devastating attack on you was too great!");
                knight.setHitPoints(0); //amount of damage passed was too much, so set health to 0.
            }
            System.out.println("After the enemy's attack, your health is now " + knight.getHealth() + "!");
        }
        //either the knight's or the enemy's health has reached 0.
        if (knight.getHealth() > 0)
        {
            return 0; //the enemy was defeated
        }
        else
        {
            return -1; //the knight lost
        }
    }
}
