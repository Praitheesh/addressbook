package com.reece.addressbook.exceptions;

/**
 * If the system unable to find given addressbook name then throw this Exception
 */
public class AddressbookNotFoundException extends Exception {
    public AddressbookNotFoundException(String message) {
        super(message);
    }
}
