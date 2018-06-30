package com.reece.addressbook.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Model class for Addressbook , Address book name is unique.
 */
public class AddressBook {

    private String name;
    private Set<Contact> contacts;

    public AddressBook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contact> getContacts() {
        if(contacts == null ){
            contacts = new HashSet<>();
        }
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressBook that = (AddressBook) o;

        return name != null ? name.equalsIgnoreCase(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
