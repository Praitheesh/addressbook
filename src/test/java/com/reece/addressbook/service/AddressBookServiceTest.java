package com.reece.addressbook.service;

import com.reece.addressbook.AddressBookApp;
import com.reece.addressbook.exceptions.AddressBookExistsException;
import com.reece.addressbook.exceptions.ContactExistsException;
import com.reece.addressbook.model.AddressBook;
import com.reece.addressbook.model.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class AddressBookServiceTest {

    AddressBookService addressBookService;


    String reeceServiceCenter = "ReeceServiceCenter";
    String reeceHRGroup = "ReeceHRGroup";

    @Before
    public void setUp() throws Exception {

        AddressBookApp addressBookApp = new AddressBookApp();
        addressBookService = addressBookApp.getAddressBookService();
    }

    /**
     * Test case for Acceptance Criteria1 : Address book will hold name and phone numbers of contact entries
     * @throws Exception
     */
    @Test
    public void printAllContactsForAddressBookTest() throws Exception {
        AddressBook reeceServiceCenterAB = new AddressBook(reeceServiceCenter);

        //Create contact with multiple phone number
        Set<String> johnPhonesNumbers = new HashSet<>();
        johnPhonesNumbers.add("+614736762873");
        johnPhonesNumbers.add("+613530062873");
        Contact john = new Contact("JOHN" , johnPhonesNumbers);

        //Create addressbook and add contact
        addressBookService.createAddressBook(reeceServiceCenterAB);
        addressBookService.createContact(reeceServiceCenter,john);

        //Print contact
        Assert.assertEquals("JOHN" , addressBookService.printAllContacts(reeceServiceCenter).iterator().next().getName());
        Assert.assertEquals(2 , addressBookService.printAllContacts(reeceServiceCenter).iterator().next().getPhoneNumbers().size());
    }

    /**
     * Test case for Acceptance Criteria2 : Users should be able to add new contact entries
     * @throws Exception
     */
    @Test
    public void createContactTest() throws Exception {
        //Create addressbook ReeceServiceCenter and Add contact JOHN
        String addressBookName = "ReeceServiceCenter";
        Contact john = new Contact("JOHN" , "+61467876754");
        addressBookService.createContact(addressBookName , john);
        Assert.assertTrue(addressBookService.printAllContacts().iterator().next().equals(john));
        Assert.assertTrue(addressBookService.printAllContacts(addressBookName).contains(john));
    }

    /**
     * Test case for Acceptance Criteria3 :Users should be able to remove existing contact entries
     */
    @Test
    public void removeContactsTest() throws Exception {
        String addressBookName = "ReeceServiceCenter";
        Contact john = new Contact("JOHN" , "+61467876754");
        //Create contact JOHN into ReeceServiceCenter addressbook
        addressBookService.createContact(addressBookName , john);

        //Verify JOHN is in contact list
        Assert.assertTrue(addressBookService.printAllContacts().iterator().next().equals(john));

        //Remove JOHN
        addressBookService.removeContacts(john);

        //Verify JOHN is not in contact list
        Assert.assertFalse(addressBookService.printAllContacts().contains(john));

    }

    /**
     * Test case for Acceptance Criteria3 :Users should be able to remove existing contact entries.
     * Add User into multiple address book and remove
     */
    @Test
    public void removeContactsInMultipleAddressBookTest() throws Exception {
        //Create two addressbook
        addressBookService.createAddressBook(new AddressBook(reeceServiceCenter));
        addressBookService.createAddressBook(new AddressBook(reeceHRGroup));

        //Create contact JOHN
        Contact john = new Contact("JOHN" , "+61467876754");

        //Add JOHN into both addressbook
        addressBookService.createContact(reeceServiceCenter , john);
        addressBookService.addToAddressBook(reeceHRGroup, "JOHN");

        //Verify JOHN is in contact list
        Assert.assertTrue(addressBookService.printAllContacts().iterator().next().equals(john));

        //Remove JOHN
        addressBookService.removeContacts(john);

        //Verify both addressbook not contain JOHN
        Assert.assertEquals(addressBookService.printAllContacts(reeceServiceCenter).size(),0);
        Assert.assertEquals(addressBookService.printAllContacts(reeceHRGroup).size(),0);


        //Verify JOHN is not in contact list
        Assert.assertFalse(addressBookService.printAllContacts().contains(john));
    }

    /**
     * Test case for Acceptance Criteria4 : Users should be able to print all contacts in an address book
     * @throws Exception
     */
    @Test
    public void printAllContactsInAnAddressBookTest() throws Exception {
        //Create addressbook reeceServiceCenter and three contact
        AddressBook reeceServiceCenterAB = new AddressBook(reeceServiceCenter);

        Contact john = new Contact("JOHN" , "+61467876754");
        Contact ammy = new Contact("AMMY" , "+61467873734");
        Contact jothy = new Contact("JOTHY" , "+61462376754");

        addressBookService.createAddressBook(reeceServiceCenterAB);

        addressBookService.createContact(reeceServiceCenter,john);
        addressBookService.createContact(reeceServiceCenter,ammy);
        addressBookService.createContact(reeceServiceCenter,jothy);

        //number of Contacts in ReeceServiceCenter is 3
        Assert.assertEquals(3, addressBookService.printAllContacts(reeceServiceCenter).size());
    }

    /**
     * Test case for Acceptance Criteria5 : Users should be able to maintain multiple address books
     * @throws Exception
     */
    @Test
    public void createMultipleAddressBookTest() throws Exception {
        addressBookService.createAddressBook(new AddressBook(reeceServiceCenter));
        addressBookService.createAddressBook(new AddressBook(reeceHRGroup));
        Contact john = new Contact("JOHN" , "+61467876754");
        Contact ammy = new Contact("AMMY" , "+61467873734");
        Contact jothy = new Contact("JOTHY" , "+61462376754");

        addressBookService.createContact(reeceServiceCenter,john);
        addressBookService.createContact(reeceHRGroup,ammy);
        //Add Jothy into ReeceServiceCenter addressbook
        addressBookService.createContact(reeceHRGroup,jothy);

        //Add Jothy to ReeceHRGroup address book also
        addressBookService.addToAddressBook(reeceServiceCenter,jothy.getName());

        Assert.assertEquals(2 , addressBookService.printAllContacts(reeceServiceCenter).size());
        Assert.assertEquals(2 , addressBookService.printAllContacts(reeceHRGroup).size());
    }

    /**
     * Test case for Acceptance Criteria6 : Users should be able to print a unique set of all contacts across multiple address books
     * @throws Exception
     */
    @Test
    public void printAllContactsTest() throws Exception {
        AddressBook reeceServiceCenterAB = new AddressBook(reeceServiceCenter);
        AddressBook reeceHRGroupAB = new AddressBook(reeceHRGroup);

        Contact john = new Contact("JOHN" , "+61467876754");
        Contact ammy = new Contact("AMMY" , "+61467873734");
        Contact jothy = new Contact("JOTHY" , "+61462376754");
        Contact nick = new Contact("NICK" , "+61467456734");
        Contact tom = new Contact("TOM" , "+61467865754");
        Contact mark = new Contact("MARK" , "+61462376734");


        addressBookService.createAddressBook(reeceHRGroupAB);
        addressBookService.createAddressBook(reeceServiceCenterAB);

        addressBookService.createContact(reeceServiceCenter,john);
        addressBookService.createContact(reeceServiceCenter,ammy);
        addressBookService.createContact(reeceServiceCenter,jothy);
        addressBookService.createContact(reeceHRGroup,nick);
        addressBookService.createContact(reeceHRGroup,tom);
        addressBookService.createContact(reeceHRGroup,mark);
        addressBookService.addToAddressBook(reeceServiceCenter,"NICK");
        addressBookService.addToAddressBook(reeceServiceCenter,"TOM");

        //number of Contacts in ReeceServiceCenter is 5
        Assert.assertEquals(5, addressBookService.printAllContacts(reeceServiceCenter).size());
        //number of contacts in ReeceHRGroup is 3
        Assert.assertEquals(3, addressBookService.printAllContacts(reeceHRGroup).size());
        // Two contacts are common in both addressbook
        Assert.assertEquals(6,addressBookService.printAllContacts().size());

    }

    /**
     * Create valid address books
     * @throws Exception
     */
    @Test
    public void createAddressBookPassTest() throws Exception {
        addressBookService.createAddressBook(new AddressBook(reeceServiceCenter));
        addressBookService.createAddressBook(new AddressBook(reeceHRGroup));

        Assert.assertEquals(addressBookService.printAllAddressBook().size(), 2);
    }

    /**
     * Test to add duplicate address book name
     * @throws Exception
     */
    @Test(expected = AddressBookExistsException.class)
    public void createDuplicateAddressBookTest() throws Exception {
        AddressBook reeceHRGroup1 = new AddressBook("ReeceHRGroup");
        AddressBook reeceHRGroup2 = new AddressBook("ReeceHRGroup");
        addressBookService.createAddressBook(new AddressBook(reeceServiceCenter));
        addressBookService.createAddressBook(reeceHRGroup1);
        addressBookService.createAddressBook(reeceHRGroup2);
    }

    @Test(expected = ContactExistsException.class)
    public void createContactDuplicateTest() throws Exception {
        String addressBookName = "ReeceServiceCenter";
        Contact john = new Contact("JOHN" , "+61467876754");
        Contact john2 = new Contact("JOHN" , "+61467876734");
        addressBookService.createContact(addressBookName , john);
        addressBookService.createContact(addressBookName,john2);
    }
}