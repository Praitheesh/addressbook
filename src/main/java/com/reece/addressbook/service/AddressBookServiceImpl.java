package com.reece.addressbook.service;

import com.reece.addressbook.exceptions.AddressBookExistsException;
import com.reece.addressbook.exceptions.AddressbookNotFoundException;
import com.reece.addressbook.exceptions.ContactExistsException;
import com.reece.addressbook.exceptions.ContactNotFoundException;
import com.reece.addressbook.model.AddressBook;
import com.reece.addressbook.model.Contact;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class AddressBookServiceImpl implements AddressBookService {

    private static final Logger LOGGER = Logger.getLogger(AddressBookServiceImpl.class.getName());

    Map<String, AddressBook> addressBookStore;
    Set<Contact> contactsList ;

    public AddressBookServiceImpl(Map addressBookStore, Set<Contact> contactsList) {
        this.addressBookStore = addressBookStore;
        this.contactsList = contactsList;
    }

    @Override
    public AddressBook createAddressBook(AddressBook addressBook) throws AddressBookExistsException {
        if(addressBookStore.containsKey(addressBook.getName())){
            throw new AddressBookExistsException(String.format("Addressbook %s already exists", addressBook.getName()));
        }
        addressBookStore.put(addressBook.getName(),addressBook);
        return addressBook;
    }

    @Override
    public Contact createContact(String addressBookName , Contact contact) throws ContactExistsException {
        if(contactsList.contains(contact)) {
            throw new ContactExistsException(String.format("Contact %s already exist" , contact.getName()));
        }

        if(addressBookStore.containsKey(addressBookName)) {
            addressBookStore.get(addressBookName).getContacts().add(contact);
        } else {
            AddressBook newAddressBook = new AddressBook(addressBookName);
            newAddressBook.getContacts().add(contact);
            addressBookStore.put(addressBookName,newAddressBook);
        }

        contactsList.add(contact);

        return contact;
    }

    @Override
    public Contact addToAddressBook(String addressBookName, String contactName) throws ContactNotFoundException, AddressbookNotFoundException {
        if(!contactsList.contains(new Contact(contactName))) {
            throw new ContactNotFoundException(String.format("Contact %s not found in the system", contactName));
        }

        if(!addressBookStore.containsKey(addressBookName)) {
            throw new AddressbookNotFoundException(String.format("Addressbook %s does not exist in the system ",addressBookName));
        }
        Contact contact = null;
        for ( Contact c : contactsList) {
            if(c.getName().equalsIgnoreCase(contactName)) {
                contact = c;
                break;
            }
        }

        addressBookStore.get(addressBookName).getContacts().add(contact);

        return contact;
    }

    @Override
    public Set<AddressBook> printAllAddressBook() {
        return new HashSet<>(addressBookStore.values());
    }

    @Override
    public void removeContacts(final Contact contact) {
        contactsList.remove(contact);
        addressBookStore.forEach((k,v) -> {
            if(v.getContacts().contains(contact)){
                v.getContacts().remove(contact);
            }
        });
    }

    @Override
    public Set<Contact> printAllContacts(String addressBook) throws AddressbookNotFoundException {
        if(addressBookStore.containsKey(addressBook))
        {
            Set<Contact> contacts = addressBookStore.get(addressBook).getContacts();
            contacts.forEach( c-> LOGGER.info(String.format("PrintAllContacts in Addressbook %s : Contact name : %s", addressBook, c.getName())));
            return contacts;
        } else {
            throw new AddressbookNotFoundException(String.format("Addressbook %s does not exist", addressBook));
        }
    }

    @Override
    public Set<Contact> printAllContacts() {
        contactsList.forEach(c-> LOGGER.info(String.format("PrintAllContacts : Contact name : %s", c.getName())));
        return contactsList;
    }
}
