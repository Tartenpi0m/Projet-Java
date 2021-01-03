package src.musichub.main;


import java.lang.Exception;

/**
* This exception indicate that an object can't be created because it already exist.
*/
public class AlreadyExistException extends Exception {
	
    public AlreadyExistException(String message)
    {
        System.out.println(message);
    }
}
