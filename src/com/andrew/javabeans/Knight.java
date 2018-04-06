package com.andrew.javabeans;/* Andrew J Wood
   COP3252 Java

   The com.andrew.javabeans.Knight class provides two constructors to creat a knight and holds all of its
   attributes.

   The attributes consist of:
   -Name
   -Weapon Type
   -HitPoints (Initialized to 100, basically a health value)
   -Armor Type

   The methods are:
   Fight(com.andrew.javabeans.Knight) - Fights the knight indicated as an argument.  Uses the weapon assigned to the fighting knight
   toString() - Outputs all the attributes
   setName(String name) - sets the Name
   setWeaponType(*enum weapon) - sets the Weapon Type
   setHitPoints(int hitPoints)
   getHitPoints()
   setArmorType(*enum armor)

   Note that the armor type is selected at random as this was not specified in the program requirements.
 */

import java.security.SecureRandom;

public class Knight implements Fightable
{
    //create enum class that enumerates the weapon type
    //implicitly static and final by rule of enums
    public enum WeaponType
    {
        //Modify damage by strength of weapons
        LONG_SWORD("Long Sword",1.0),
        BATTLE_AXE("Battle Axe",1.2),
        SPEAR("Spear",0.9),
        WAR_HAMMER("War Hammer",1.8);

        private final double damageModifier;
        private final String prettyName;

        //constructor
        WeaponType(String prettyName, double damage)
        {
            this.prettyName = prettyName;
            this.damageModifier = damage;
        }

        private double getDamageModifier()
        {
            return this.damageModifier;
        }

        private String getPrettyName()
        {
            return this.prettyName;
        }
    }

    //create ArmorType class that enumerates the armor type
    //implicitly static and final by rule of enums
    public enum ArmorType
    {
        METAL("Metal",1.0),
        LEATHER("Leather",0.7),
        TITANIUM("Titanium",1.6),
        UNOBTANIUM("Unobtanium",2.2);

        private final String prettyName;
        private final double defenseModifier;

        //constructor
        ArmorType(String prettyName, double defense)
        {
            this.prettyName = prettyName;
            this.defenseModifier = defense;
        }

        private double getDefenseModifier()
        {
            return this.defenseModifier;
        }

        private String getPrettyName()
        {
            return this.prettyName;
        }
    }

    //static array with names to be used as defaults if none is specified
    private static final String[] defaultNames = {"Sir Andrew","Sir Arthur","Sir Blake","Sir Cody","Madam Jane","Madam Joseline",
            "Sir Phillip","Madam Elizabeth","Sir Tyson","Sir Javs","Madam Dorthy"};

    //com.andrew.javabeans.Knight instance Variables
    private String name;
    private WeaponType weapon;
    private ArmorType armor;
    private int hitPoints;
    private final int HIT_POINTS_ADDL = 20;
    private final int HIT_POINTS_MIN = 75;

    //default constructor when no arguments are passed
    public Knight ()
    {
        SecureRandom random = new SecureRandom();

        //select a random number that corresponds to weapon type
        int weaponSelect = random.nextInt((WeaponType.values()).length);
        this.weapon = WeaponType.values()[weaponSelect];

        //select a random number that corresponds to armor type
        int armorSelect = random.nextInt((WeaponType.values()).length);
        this.armor = ArmorType.values()[armorSelect];

        int nameSelect = random.nextInt(defaultNames.length);
        this.name = defaultNames[nameSelect];

        this.hitPoints = HIT_POINTS_MIN + random.nextInt(HIT_POINTS_ADDL);
    }

    //constructor to be used when arguments are specified
    public Knight(WeaponType weapon, ArmorType armor, String name)
    {
        SecureRandom random = new SecureRandom();

        this.weapon = weapon;
        this.armor = armor;
        this.name = name;

        //user cannot choose the initial HitPoints as per specification
        this.hitPoints = HIT_POINTS_MIN + random.nextInt(HIT_POINTS_ADDL);
    }

    //returns the defense modifier (wrapped within the armor type)
    public double getDefenseModifier()
    {
        return this.armor.getDefenseModifier();
    }

    //get the health value
    public int getHealth()
    {
        return this.getHitPoints();
    }

    //returns the knight's weapon type
    public WeaponType getWeaponType() {
        return this.weapon;
    }

    //sets the knights weapon type
    public void setWeaponType(WeaponType weapon)
    {
        //assigns this knight's weapon to reference of passed weapon
        this.weapon = weapon;
    }

    //returns the knight's armor type
    public ArmorType getArmorType()
    {
        return this.armor;
    }

    //sets the knight's armor type
    public void setArmorType(ArmorType armor)
    {
        this.armor = armor;
    }

    //gets the knight's name
    public String getName()
    {
        return this.name;
    }

    //sets the knight's name
    public void setName(String name)
    {
        this.name = name;
    }

    //gets the knight's hitpoints
    public int getHitPoints()
    {
        return hitPoints;
    }

    //sets the knight's hitpoints
    public void setHitPoints(int hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    //Outputs the knight's statistics
    @Override
    public String toString()
    {
        String s = "Name: " + this.getName() + "\n";
        s       += "Health: " + this.getHitPoints() + "\n";
        s       += "Weapon Type: " + this.getWeaponType().getPrettyName() + "\n";
        s       += "Armor Type: " + this.getArmorType().getPrettyName() + "\n";
        return s;
    }

    //Subtacts damage greatfully from the com.andrew.javabeans.Knight's hitpoints
    public void takeDamage(int damage)
    {
        int compareHealth = this.hitPoints - damage;
        if ((compareHealth) > 0)
            this.hitPoints -= damage;
        else
            throw new InvalidDamageException();
    }

    //Fights the enemy specified by the argument
    //Returns an integer specifying how much damage the night caused.
    //This value may be used by the client program in any manner.
    public int fight(Fightable fightable)
    {
        //constant specifying maximum damage amount
        final int DAMAGE_RANGE = 20;
        SecureRandom random = new SecureRandom();

        //select a base amount of damage to deal ranging from 0 to DAMAGE_RANGE - 1.  0 indicates a "miss".
        int hitPointsReduce = random.nextInt(DAMAGE_RANGE);

        //amplify or reduce the damage based on the armor possessed.
        double tempModify = hitPointsReduce * this.weapon.damageModifier;
        hitPointsReduce = (int)tempModify;

        //amplify or reduce the damage based on the enemy defenses
        tempModify = hitPointsReduce / fightable.getDefenseModifier();
        hitPointsReduce = (int)tempModify;

        return hitPointsReduce; //returns the damage value ultimately allowable for the enemy.
    }
} //end class com.andrew.javabeans.Knight
