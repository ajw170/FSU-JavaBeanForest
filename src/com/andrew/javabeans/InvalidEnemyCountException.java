package com.andrew.javabeans;/* Andrew J Wood
   COP3252 Java

   A custom Exception class "InvalidEnemyException" that checks to see if the number of enemies input
   is not greater than 0.  In the event the input is wrong, an exception is thrown.
 */

//
public class InvalidEnemyCountException extends IllegalArgumentException
{
    InvalidEnemyCountException()
    {
        super("Number of com.andrew.javabeans.Enemy inputs is invalid");
    }

    InvalidEnemyCountException(String message)
    {
        super(message);
    }

    InvalidEnemyCountException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

    InvalidEnemyCountException(Throwable throwable)
    {
        super(throwable);
    }
}
