package com.reece.addressbook.service;

import com.reece.addressbook.exceptions.AddressBookExistsException;
import com.reece.addressbook.exceptions.AddressbookNotFoundException;
import com.reece.addressbook.exceptions.ContactExistsException;
import com.reece.addressbook.exceptions.ContactNotFoundException;
import com.reece.addressbook.model.AddressBook;
import com.reece.addressbook.model.Contact;

import java.util.Set;

/**
 * AddressBookService contains api to manage addressbook and contacts
 */
public interface AddressBookService {

    /**
     * Create new address book, throw AddressBookExistsException if already exist
     * @param addressBook
     * @return
     * @throws AddressBookExistsException
     */
    AddressBook createAddressBook(AddressBook addressBook) throws AddressBookExistsException;

    /**
     * Remove contact and link from addressbook
     * @param contact
     */
    void removeContacts(Contact contact);

    /**
     * Print all contacts from given addressbook
     * @param addressBookName
     * @return
     */
    Set<Contact> printAllContacts(String addressBookName) throws AddressbookNotFoundException;

    /**
     * Print all contacts from all addressbooks
     * @return
     */
    Set<Contact> printAllContacts();

    /**
     * Create contact and link to given addressbook. If given addressbook not exist then create new.
     * @param addressBookName
     * @param contact
     * @return
     * @throws ContactExistsException
     */
    Contact createContact(String addressBookName , Contact contact) throws ContactExistsException;

    /**
     * Link the contact to given addressbook
     * @param addressBookName
     * @param contactName
     * @return
     */
    Contact addToAddressBook(String addressBookName , String contactName) throws ContactNotFoundException, AddressbookNotFoundException;

    /**
     * Print all addressbooks
     * @return
     */
    Set<AddressBook> printAllAddressBook();
}
