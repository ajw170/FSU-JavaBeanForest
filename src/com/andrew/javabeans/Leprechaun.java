package com.andrew.javabeans;/* Class com.andrew.javabeans.Leprechaun

   Extends the com.andrew.javabeans.Enemy class.
 */

import java.security.SecureRandom;

public class Leprechaun extends Enemy
{
    //A com.andrew.javabeans.Leprechaun has robust power
    private final double powerModifier = 1.2;

    //A com.andrew.javabeans.Leprechaun has solid defenses
    private final double defenseModifier = 1.5;

    public double getDefenseModifier()
    {
        return defenseModifier;
    }

    @Override
    public int fight(Fightable fightable)
    {
        //constant specifying maximum damage amount
        final int DAMAGE_RANGE = 22;
        SecureRandom random = new SecureRandom();

        //select a base amount of damage to deal ranging from 0 to DAMAGE_RANGE - 1.  0 indicates a "miss".
        int damage = random.nextInt(DAMAGE_RANGE);

        //amplify or reduce the damage based on the armor possessed.
        double tempModify = damage * powerModifier;
        damage = (int)tempModify;

        //amplify or reduce the damage based on the enemy defenses
        tempModify = damage / fightable.getDefenseModifier();
        damage = (int)tempModify;

        return damage; //returns the damage value ultimately allowable for the enemy.
    }

    public String toString()
    {
        String s = "com.andrew.javabeans.Leprechaun:\n";
        s       += "Health: " + this.getHealth() + "\n";
        return s;
    }

    public String shortName()
    {
        return "com.andrew.javabeans.Leprechaun";
    }
}
