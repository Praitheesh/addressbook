package com.reece.addressbook.exceptions;

/**
 * Addressbook name is unique, throw AddressBookExistsException when user try to create duplicate addressbook
 */
public class AddressBookExistsException extends  Exception {
    public AddressBookExistsException(String message) {
        super(message);
    }
}
