package com.reece.addressbook;

import com.reece.addressbook.model.Contact;
import com.reece.addressbook.service.AddressBookService;
import com.reece.addressbook.service.AddressBookServiceImpl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AddressBookApp {

    Map<String, String> addressBookStore = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
    Set<Contact> contactsList = new HashSet<>();
    AddressBookService addressBookService = new AddressBookServiceImpl(addressBookStore,contactsList);

    public AddressBookService getAddressBookService() {
        return addressBookService;
    }
}
