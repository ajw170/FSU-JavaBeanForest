package com.andrew.javabeans;/* Andrew J. Wood
   COP3252 - Java

   The com.andrew.javabeans.Fightable interface defines any object (good or bad) that can participate in a fight or duel.

   It is implemented by com.andrew.javabeans.Knight and com.andrew.javabeans.Enemy.
 */

public interface Fightable
{
    int getHealth();

    int fight(Fightable fightable);

    void takeDamage(int damage);

    double getDefenseModifier();
}
