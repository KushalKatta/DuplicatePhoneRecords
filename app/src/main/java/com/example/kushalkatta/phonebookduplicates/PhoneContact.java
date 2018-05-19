package com.example.kushalkatta.phonebookduplicates;

import java.util.ArrayList;

public class PhoneContact {
    String contactName;
    ArrayList<String> contacts;

    public PhoneContact(){
        contacts = new ArrayList<String>();
    }

    public PhoneContact(String contactName, ArrayList<String> contacts) {

        this.contactName = contactName;
        this.contacts = contacts;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void addContact(String contactNo)
    {
        contacts.add(contactNo);
    }
    public String getContactNo()
    {
        return contacts.toString();
    }
    public ArrayList<String> getContacts()
    {
        return contacts;
    }
}
