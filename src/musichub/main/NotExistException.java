package main;

import java.lang.Exception;

/**
* This exception indicate that an object idoesn't exist when it is researched.
*/
public class NotExistException extends Exception {
	
    public NotExistException(String message)
    {
        System.out.println(message);
    }
}