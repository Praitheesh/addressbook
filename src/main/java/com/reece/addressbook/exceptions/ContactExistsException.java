package com.reece.addressbook.exceptions;

/**
 * Contact name is unique, throw ContactExistsException when user try to create duplicate contact
 */
public class ContactExistsException extends Exception {
    public ContactExistsException(String message) {
        super(message);
    }
}
