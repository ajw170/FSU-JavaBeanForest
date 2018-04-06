package com.andrew.javabeans;/* Andrew J Wood
   COP3252 - Java
   com.andrew.javabeans.Enemy.java

   This is the enemy interface that defines the Fight method.  Note that an interface is used in lieu
   of an abstract super class so that both the knight Class (written previously) and the Enemies can
   interact with the common fight() method.


 */
import java.security.SecureRandom;

public abstract class Enemy implements Fightable
{
    //the com.andrew.javabeans.Enemy's health field.  This applies to both the knight and the enemies.
    private int health;

    //Default constructor with no arguments; sets the com.andrew.javabeans.Enemy's health to initial value of 30.
    Enemy()
    {
        this.health = 30;
    }

    //returns the com.andrew.javabeans.Enemy health value
    public int getHealth()
    {
        return this.health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    //Fights the knight based on the enemy's capabilities and the knight's defenses, returns a damage value.
    //The value returned can be used however the client program would like.
    public abstract int fight (Fightable fightable);

    public abstract double getDefenseModifier();

    public abstract String shortName();

    @Override
    public abstract String toString();

    //Assigns damage to the enemy based on the enemies defenses
    //subtracts the damange from the enemy's current damage
    public void takeDamage(int damage)
    {
        int compareHealth = this.health - damage;
        if ((compareHealth) > 0)
            this.health -= damage;
        else
            throw new InvalidDamageException();
    }

    //Factory method that returns a random com.andrew.javabeans.Enemy object, note that this would need to be modified in the event
    //additional enemy types are added
    //Ideally you'd have an "enemy factory" method separate from the com.andrew.javabeans.Enemy class but I did this to conform to the
    //assignment specifications
    public static Enemy getRandomEnemy()
    {
        SecureRandom random = new SecureRandom();
        int selection = random.nextInt(3); //returns a value from 0-2

        Enemy returnEnemy;

        //returns enemy reference based on random value
        switch(selection)
        {
            case 0:
                returnEnemy = new Ogre();
                break;
            case 1:
                returnEnemy = new Leprechaun();
                break;
            case 2:
                returnEnemy = new WallyWalMart();
                break;
            default:  //program should never get here
                returnEnemy = new Ogre(); //ogre is returned by default
                break;
        }

        return returnEnemy;
    }

}
