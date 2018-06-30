package com.reece.addressbook.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Model class to contact  , name is unique value
 */
public class Contact {

    private String name;
    private Set<String> phoneNumbers;

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, String phoneNumbers) {
        this.name = name;
        getPhoneNumbers().add(phoneNumbers);
    }

    public Contact(String name, Set<String> phoneNumbers) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPhoneNumbers() {
        if(phoneNumbers == null ){
            phoneNumbers = new HashSet<>();
        }
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * Add new phone number to contact
     * @param phoneNumber
     * @return
     */
    public Contact addPhoneNumber(String phoneNumber) {
        getPhoneNumbers().add(phoneNumber);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return name.equalsIgnoreCase(contact.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
