package com.reece.addressbook.exceptions;

/**
 * Throw ContactNotFoundException if contact name not found in the system
 */
public class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
