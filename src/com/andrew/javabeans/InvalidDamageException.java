package com.andrew.javabeans;

public class InvalidDamageException extends IllegalArgumentException
{
    InvalidDamageException()
    {
        super("The damage was too great!");
    }

    InvalidDamageException(String message)
    {
        super(message);
    }

    InvalidDamageException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

    InvalidDamageException(Throwable throwable)
    {
        super(throwable);
    }
}